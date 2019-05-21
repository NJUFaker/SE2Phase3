package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    //下面是添加的
    @Autowired
    private CouponService couponService;
    @Autowired
    private MovieServiceForBl movieServiceForBl;
    @Autowired
    private CouponServiceForBl couponServiceForBl;
    @Autowired
    private ActivityServiceForBl activityServiceForBl;
    @Autowired
    private VipServiceForBl vipServiceForBl;
    @Autowired
    private VIPService vipService;
    /**
     * 每一个schedule绑定一系列票，从而确定哪些座位被锁定
     * @param scheduleId
     * @return
     */
    private int[][] getLockedSeats(int scheduleId){
        List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
        ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
        Hall hall=hallService.getHallById(schedule.getHallId());
        int[][] seats=new int[hall.getRow()][hall.getColumn()];
        //每一个ticket都对应一个座位被锁定。
        tickets.stream().forEach(ticket -> {
            seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
        });
        return  seats;
    }


    /**
     * TODO:锁座【增加票但状态为未付款】
     * 订单状态：
     * 0：未完成 1：已完成 2:已失效
     * @param ticketForm
     * @return
     */
    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {

        List<SeatForm> seats=ticketForm.getSeats();
        List<TicketVO> ticketVOS=new ArrayList<>();
        int count=0;
        for (int i = 0; i < seats.size(); i++) {
            int[][] lockedSeats=getLockedSeats(ticketForm.getScheduleId());
            if (lockedSeats[seats.get(i).getRowIndex()][seats.get(i).getColumnIndex()]==1){
                return ResponseVO.buildFailure("座位已经被锁定");
            }
            Ticket ticket=new Ticket();
            ticket.setUserId(ticketForm.getUserId());
            ticket.setScheduleId(ticketForm.getScheduleId());
            ticket.setColumnIndex(seats.get(i).getColumnIndex());
            ticket.setRowIndex(seats.get(i).getRowIndex());
            ticket.setState(0);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            ticket.setTime(timestamp);
            TicketVO ticketVO=ticket.getVO();
            ticketMapper.insertTicket(ticket);
            ticketVOS.add(ticketVO);
            count++;
        }

        try {
            TicketWithCouponVO ticketWithCouponVO=new TicketWithCouponVO();
            ticketWithCouponVO.setTicketVOList(ticketVOS);
            ticketWithCouponVO.setTotal(count*scheduleService.getScheduleItemById(ticketForm.getScheduleId()).getFare());
            ticketWithCouponVO.setCoupons(couponServiceForBl.selectCouponByUserAndAmount(ticketForm));
            ticketWithCouponVO.setActivities(activityServiceForBl.selectActivities());
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    /**
     * TODO:取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param ticketId
     * @return
     */
    @Override
    public ResponseVO cancelTicket(List<Integer> ticketId) {
        try {
            for (int i = 0; i < ticketId.size(); i++) {
                Ticket ticket=ticketMapper.selectTicketById(ticketId.get(i));
                if (ticket==null||ticket.getState()==2){
                    return  ResponseVO.buildFailure("座位未被锁定");
                }
                else {
                    ticketMapper.deleteTicket(ticketId.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        return ResponseVO.buildSuccess();
    }

    /**
     * 完成校验优惠券（初步定为仅仅删除使用过的优惠券）和赠送优惠券
     * @param ticketId
     * @param couponId
     * @return
     */
    private String checkAndGiveCoupon(List<Integer> ticketId, int couponId){
        String content="";
        Ticket ticket=ticketMapper.selectTicketById(ticketId.get(0));
        if (couponId==0) {
            ;
        }
        else {
            //删除优惠券
            couponServiceForBl.deleteCoupon(couponId,ticket.getUserId());
        }
        int scheduleId=ticket.getScheduleId();
        ScheduleItem scheduleItem=scheduleService.getScheduleItemById(scheduleId);
        Movie movie=movieServiceForBl.getMovieById(scheduleItem.getMovieId());
        List<Activity> activities=activityServiceForBl.selectActivities();
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getMovieList()==null){
                couponService.issueCoupon(activities.get(i).getCoupon().getId(),ticket.getUserId());
                content="用户获得优惠券";
            }
            else if (containMovie(activities.get(i).getMovieList(),movie)){
                couponService.issueCoupon(activities.get(i).getCoupon().getId(),ticket.getUserId());
                content="用户获得优惠券";
            }
            else {
                content="用户未获得优惠券";
            }
        }
        return content;

    }

    private boolean containMovie(List<Movie> movies,Movie movie){
        ArrayList<Integer> ids=new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            ids.add(movies.get(i).getId());
        }
        return ids.contains(movie.getId());
    }


    /**
     * TODO:完成购票【不使用会员卡】流程包括校验优惠券(初步定为仅仅删除优惠券）和根据优惠活动赠送优惠券
     *
     * 在sales包添加了一个接口。CouponServiceImpl实现了它。（仿照schedule）
     *
     * @param ticketId
     * @param couponId
     * @return
     */
    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> ticketId, int couponId) {
        try {
            String content=checkAndGiveCoupon(ticketId,couponId);
            for (int i = 0; i < ticketId.size(); i++) {
                ticketMapper.updateTicketState(ticketId.get(i),1);
            }
            return ResponseVO.buildSuccess(content);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }



    /**
     * TODO:完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param ticketId
     * @param couponId
     * @return
     */
    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> ticketId, int couponId) {
        try{
            Ticket ticket=ticketMapper.selectTicketById(ticketId.get(0));
            VIPCard vipCard=(VIPCard)vipService.getCardByUserId(ticket.getUserId()).getContent();
            ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticket.getScheduleId());
            double sum;
            if (couponId==0){
                sum=ticketId.size()*scheduleItem.getFare();
            }
            else {
                Coupon coupon=couponServiceForBl.getCouponById(couponId);
                sum=ticketId.size()*scheduleItem.getFare()-coupon.getDiscountAmount();
            }
            boolean isEnough=vipServiceForBl.payByVipCard(vipCard.getId(),sum);
            if (isEnough){
                checkAndGiveCoupon(ticketId,couponId);
                for (int i = 0; i < ticketId.size(); i++) {
                    ticketMapper.updateTicketState(ticketId.get(i),1);
                }
                return ResponseVO.buildSuccess();
            }
            else {
                return ResponseVO.buildFailure("会员卡余额不足");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }


    }

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            int[][] seats=getLockedSeats(scheduleId);
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(scheduleService.getScheduleItemById(scheduleId));
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    /**
     * TODO:获得用户买过的票
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseVO getTicketByUser(int userId) {
        List<Ticket> tickets=ticketMapper.selectTicketByUser(userId);
        if (tickets==null){
            return ResponseVO.buildFailure("失败");
        }
        else {
            List<TicketVO> ticketVOS=new ArrayList<>();
            for (int i = 0; i < tickets.size(); i++) {
                ticketVOS.add(tickets.get(i).getVO());
            }
            return ResponseVO.buildSuccess(ticketVOS);
        }

    }









}

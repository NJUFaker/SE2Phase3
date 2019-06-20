package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Coupon;
import com.example.cinema.vo.TicketForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyp
 * @date 2019/5/13 17:16 PM
 */
public interface CouponServiceForBl {
    /**
     *
     * @param couponId
     * @return
     */
     Coupon getCouponById(Integer couponId);
     void deleteCoupon(int couponId,int userId);

    /**
     * 得到该用户在票的总价为total的情况下可以使用的优惠券
     * @param total 票的总价
     * @param userId
     * @return
     */
     List<Coupon> selectCouponByUserAndAmount(double total,int userId);
}

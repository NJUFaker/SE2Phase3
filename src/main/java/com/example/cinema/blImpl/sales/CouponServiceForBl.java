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
     List<Coupon> selectCouponByUserAndAmount(double total,int userId);
}

package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Coupon;

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
}

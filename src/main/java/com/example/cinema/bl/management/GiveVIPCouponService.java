package com.example.cinema.bl.management;

import com.example.cinema.po.Coupon;

import com.example.cinema.vo.ResponseVO;

import java.util.List;
/**
 * @author lyp
 * created on 2019.05.26
 */
public interface GiveVIPCouponService {
    /**
     * 根据消费金额给会员优惠券
     * @param consume 消费金额
     * @param couponIds 优惠券id
     * @return
     */
    ResponseVO giveVIPCoupons(double consume,List<Integer> couponIds);

    /**
     * 查询已发布的所有优惠券
     * @return 返回couponForm的list
     */
    ResponseVO searchCoupons();
}

package com.example.cinema.controller.management;

import com.example.cinema.bl.management.GiveVIPCouponService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lyp
 * created on 2019.05.26
 */
@RestController()
@RequestMapping("/giveVIP")
public class GiveVIPCouponController {
    @Autowired
    GiveVIPCouponService giveVIPCouponService;

    /**
     * 根据消费金额给会员优惠券
     * @param consume 消费金额
     * @param couponIds 优惠券id
     * @return
     */
    @PostMapping("/give")
    public ResponseVO giveCoupons(@RequestParam double consume, @RequestParam List<Integer> couponIds){
        return giveVIPCouponService.giveVIPCoupons(consume,couponIds);
    }
    @GetMapping("/get")
    public ResponseVO searchCoupons(){
        return giveVIPCouponService.searchCoupons();
    }

}

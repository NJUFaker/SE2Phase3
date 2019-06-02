package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.RefundTicketStrategyService;
import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lyp
 * created on 2019.05.26
 */
public class RefundTicketStrategyController {
    private RefundTicketStrategyService refundTicketStrategyService;

    @RequestMapping(value = "refund/add",method = RequestMethod.POST)
    public ResponseVO publishRefundTicketStragety(RefundTicketStrategyForm refundTicketStrategyForm){
        return refundTicketStrategyService.publishRefundTicketStrategy(refundTicketStrategyForm);
    }
    @RequestMapping(value = "refund/update",method = RequestMethod.POST)
    public ResponseVO updateRefundTicketStragety(RefundTicketStrategyForm refundTicketStrategyForm){
        return refundTicketStrategyService.updateRefundTicketStrategy(refundTicketStrategyForm);
    }
}

package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.RefundTicketStrategyService;
import com.example.cinema.data.sales.RefundTicketMapper;
import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundTicketStrategyServiceImpl implements RefundStrategyForBl, RefundTicketStrategyService {
    @Autowired
    private RefundTicketMapper refundTicketMapper;

    @Override
    public ResponseVO publishRefundTicketStrategy(RefundTicketStrategyForm refundTicketStrategyForm){
        return null;
    }


    @Override
    public ResponseVO updateRefundTicketStrategy(RefundTicketStrategyForm refundTicketStrategyForm){
        return null;
    }

    @Override
    public List<RefundTicketStrategyForm> getRefundStrategy(){
        return null;
    }

    @Override
    public double getBestRefundStrategy(){
        return 0;
    }

}

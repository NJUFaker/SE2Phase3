package com.example.cinema.data.sales;

import com.example.cinema.po.RefundTicketStrategy;

import java.util.List;

public interface RefundTicketMapper {
    /**
     *
     * @return 返回所有的退票策略
     */
    List<RefundTicketStrategy> getAllStragegies();

    RefundTicketStrategy getStrategyById(int refundTicketId);

    /**
     * @return 返回生成的退票策略的id
     */
    int insertRefundStrategy(RefundTicketStrategy refundTicketStrategy);

    void deleteRefundStrategy(int refundTicketId);

    void updateRefundStrategy(RefundTicketStrategy refundTicketStrategy);
}

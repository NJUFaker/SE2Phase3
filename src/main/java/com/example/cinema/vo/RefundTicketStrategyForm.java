package com.example.cinema.vo;
/**
 * @author lyp
 * created on 2019.05.26
 */
public class RefundTicketStrategyForm {
    private int id;
    private float refundPercentage;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRefundPercentage(float refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    public float getRefundPercentage() {
        return refundPercentage;
    }
}

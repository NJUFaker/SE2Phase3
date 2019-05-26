package com.example.cinema.vo;

import java.sql.Timestamp;

public class ChargeRecordVO {
    /**
     * 充值金额
     */
    private double amount;
    /**
     * 充值时间
     */
    private Timestamp timestamp;
    /**
     * 会员卡充值优惠的描述
     */
    private String VIPActivity;
    /**
     *被充值优惠赠送的金额
     */
    private int givenAmount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getVIPActivity() {
        return VIPActivity;
    }

    public void setVIPActivity(String VIPActivity) {
        this.VIPActivity = VIPActivity;
    }

    public int getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(int givenAmount) {
        this.givenAmount = givenAmount;
    }
}

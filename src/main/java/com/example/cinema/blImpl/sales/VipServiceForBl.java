package com.example.cinema.blImpl.sales;

public interface VipServiceForBl {
    /**
     *
     * @param id
     * @param fare
     * @return 返回vip卡的余额是否够
     */
    boolean payByVipCard(int id,double fare);
    void updateVipBalance(int VipId,double balance);
}

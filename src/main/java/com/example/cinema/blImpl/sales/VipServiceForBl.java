package com.example.cinema.blImpl.sales;

public interface VipServiceForBl {
    /**
     *
     * @param id
     * @param fare
     * @return 返回vip卡的余额是否够,如果够则更新vip的余额
     */
    boolean payByVipCard(int id,double fare);

}

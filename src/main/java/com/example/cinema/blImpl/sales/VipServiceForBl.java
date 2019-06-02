package com.example.cinema.blImpl.sales;

public interface VipServiceForBl {
    /**
     *
     * @param id
     * @param fare
     * @return 返回vip卡的余额是否够,如果够则更新vip的余额
     */
    boolean payByVipCard(int id,double fare);

    /**
     * 更新会员的累计消费额度
     * @param vipcardID
     * @param consume
     */
    void updataVipConsume(int vipcardID,double consume);
}

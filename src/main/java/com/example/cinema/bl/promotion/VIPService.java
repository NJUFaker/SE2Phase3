package com.example.cinema.bl.promotion;

import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;



/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {

    ResponseVO addVIPCard(int userId);

    ResponseVO getCardById(int id);

    ResponseVO getVIPInfo();

    /**
     * 给会员卡充值
     * @param vipCardForm
     * @return
     */
    ResponseVO charge(VIPCardForm vipCardForm);

    ResponseVO getCardByUserId(int userId);


}

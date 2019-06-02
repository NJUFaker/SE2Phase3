package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.VIPCard;

import java.util.List;

public interface VIPServiceForPromotionBl {
    List<VIPCard> selectVipByConsume(double consume);
}

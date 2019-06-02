package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.VIPActivityForm;

import java.util.List;

public interface VIPActivityMapper {

    List<VIPActivity> getAllVIPActivities();


    VIPActivity getVIPActivityById(int id);

    int insertActivity(VIPActivityForm vipActivityForm);

    void updateActivity(VIPActivityForm vipActivityForm);

    void deleteActivity(int id);
}

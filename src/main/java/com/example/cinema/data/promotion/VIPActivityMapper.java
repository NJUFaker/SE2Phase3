package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.VIPActivityForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VIPActivityMapper {

    List<VIPActivity> getAllVIPActivities();


    VIPActivity getVIPActivityById(int id);

    int insertActivity(VIPActivity vipActivity);

    void updateActivity(VIPActivity vipActivity);

    void deleteActivity(int id);
}

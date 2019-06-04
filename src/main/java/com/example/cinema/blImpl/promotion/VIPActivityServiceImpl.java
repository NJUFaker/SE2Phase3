package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.data.promotion.VIPActivityMapper;
import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VIPActivityServiceImpl implements VIPActivityService {
    VIPActivityMapper vipActivityMapper;

    @Override
    public ResponseVO publishVIPActivity(VIPActivityForm vipActivityForm) {
        try {
            return ResponseVO.buildSuccess(vipActivityMapper.insertActivity(vipActivityForm));
        } catch (Exception e) { return ResponseVO.buildFailure("Module Failed");}
    }
    @Override
    public ResponseVO getVIPActivities(){
        try{
            List<VIPActivity> activities=vipActivityMapper.getAllVIPActivities();
            List<VIPActivityForm> a=new ArrayList<>();
            for(int i=0;i<activities.size();i++){
                a.add(new VIPActivityForm(activities.get(i)));
            }
            return ResponseVO.buildSuccess(a);
        }catch (Exception e){return ResponseVO.buildFailure("Module Failed");}
    }
    @Override
    public ResponseVO updateVIPActivity(VIPActivityForm vipActivityForm){
        try {
            VIPActivity activity=vipActivityMapper.getVIPActivityById(vipActivityForm.getId());
            if(activity==null) throw new Exception();
            vipActivityMapper.updateActivity(vipActivityForm);
            return ResponseVO.buildSuccess();
        } catch (Exception e) { return ResponseVO.buildFailure("Module Failed");}

    }

}

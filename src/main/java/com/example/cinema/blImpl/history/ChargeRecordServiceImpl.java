package com.example.cinema.blImpl.history;

import com.example.cinema.bl.history.ChargeRecordService;
import com.example.cinema.data.history.ChargeRecordMapper;
import com.example.cinema.po.ChargeRecordPO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeRecordServiceImpl implements ChargeRecordService {
    @Autowired
    private ChargeRecordMapper chargeRecordMapper;
    @Override
    public ResponseVO searchChargeRecordByUserId(Integer userId){
        try {
            List<ChargeRecordPO> chargeRecordPOS=chargeRecordMapper.getChargeRecords(userId);
            if (chargeRecordPOS==null){
                return ResponseVO.buildFailure("没有充值记录");
            }
            if (chargeRecordPOS.size()==0){
                return ResponseVO.buildFailure("没有充值记录");
            }
            else {
                return ResponseVO.buildSuccess(chargeRecordPOS);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}

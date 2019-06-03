package com.example.cinema.data.history;

import com.example.cinema.po.ChargeRecordPO;
import com.example.cinema.vo.ChargeRecordUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyp
 * created on 2019.05.28
 */
@Mapper
public interface ChargeRecordMapper {
    List<ChargeRecordPO> getChargeRecords(@Param("userID")int userID);
    int addChargeRecord(ChargeRecordUserVO chargeRecordUserVO);
}

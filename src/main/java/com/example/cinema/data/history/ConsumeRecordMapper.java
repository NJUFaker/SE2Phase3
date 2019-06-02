package com.example.cinema.data.history;



import com.example.cinema.po.ConsumeRecordPO;
import com.example.cinema.vo.ConsumeRecordUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyp
 * created on 2019.05.28
 */
@Mapper
public interface ConsumeRecordMapper {
    List<ConsumeRecordPO> getConsumeRecords(@Param("userID")int userID);
    int addConsumeRecord(ConsumeRecordUserVO consumeRecordUserVO);
}

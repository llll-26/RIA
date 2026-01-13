package com.ruoyi.student.mapper;

import com.ruoyi.student.domain.BizUserPoint;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface BizUserPointMapper {
    BizUserPoint selectByUserId(Long userId);

    int insert(BizUserPoint point);

    int update(BizUserPoint point); // 可选，用于后续更新积分
}

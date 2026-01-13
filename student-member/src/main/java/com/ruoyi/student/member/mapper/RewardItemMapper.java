package com.ruoyi.student.member.mapper;

import com.ruoyi.student.member.domain.RewardItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RewardItemMapper {
    List<RewardItem> selectList(RewardItem item);

    RewardItem selectById(Integer id);

    List<RewardItem> selectAvailableItems();
    int updateStock(@Param("id") Integer id, @Param("quantity") Integer quantity);

}

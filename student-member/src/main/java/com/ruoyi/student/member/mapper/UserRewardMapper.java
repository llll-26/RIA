package com.ruoyi.student.member.mapper;

import com.ruoyi.student.member.domain.UserReward;
import com.ruoyi.student.member.domain.vo.RedeemedRewardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRewardMapper {
    int insert(UserReward record);
    /**
     * 查询用户已兑换的奖品列表（关联奖品信息）
     */
    List<RedeemedRewardVO> selectRedeemedRewardsByUserId(@Param("userId") Long userId);
}

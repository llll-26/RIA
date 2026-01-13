package com.ruoyi.student.member.service;

import com.ruoyi.student.member.domain.UserReward;
import com.ruoyi.student.member.domain.vo.RedeemedRewardVO;

import java.util.List;
import java.util.Map;

public interface IUserRewardService {
    void createExchangeRecord(UserReward record);
    Map<String, Object> exchangeReward(Long userId, Integer itemId, Integer quantity);
    /**
     * 获取用户已兑换的奖品列表
     */
    List<RedeemedRewardVO> getRedeemedRewards(Long userId);
}

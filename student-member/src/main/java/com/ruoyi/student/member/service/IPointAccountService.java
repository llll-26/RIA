package com.ruoyi.student.member.service;

import org.springframework.stereotype.Service;

@Service
public interface IPointAccountService {
    /**
     * 扣除积分（通过增加 used_points）
     * @param userId 用户ID
     * @param points 要扣除的积分（正整数）
     * @return true 成功，false 积分不足
     */
    boolean deductPoints(Long userId, Integer points);


    void addTotalPoints(Long userId, Integer points);
    /**
     * 计算用户实际应得的总积分（仅从技能订单中获取）
     */
    Integer calculateActualTotalPoints(Long userId);

    /**
     * 校准用户总积分（如果与实际不符则更新）
     */
    void calibrateTotalPoints(Long userId);
}

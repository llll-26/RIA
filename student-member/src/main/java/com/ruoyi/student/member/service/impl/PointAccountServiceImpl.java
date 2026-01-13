package com.ruoyi.student.member.service.impl;


import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.UserPoint;
import com.ruoyi.student.member.mapper.EnrollmentMapper;
import com.ruoyi.student.member.mapper.SkillOrderMapper;
import com.ruoyi.student.member.mapper.UserPointMapper;

import com.ruoyi.student.member.service.IPointAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

//管理用户积分变动
//提供“扣除积分”能力
@Service
public class PointAccountServiceImpl implements IPointAccountService {

    @Autowired
    private UserPointMapper userPointMapper;
@Autowired
private SkillOrderMapper skillOrderMapper;
    @Autowired
    private EnrollmentMapper EnrollmentMapper;
    @Override
    public boolean deductPoints(Long userId, Integer points) {
        if (points == null || points <= 0) {
            throw new IllegalArgumentException("积分必须大于0");
        }
        int rows = userPointMapper.addUsedPoints(userId, points);
        return rows > 0;
    }

    @Override
    @Transactional
    public void addTotalPoints(Long userId, Integer points) {
        System.out.println(">>> 【积分发放】用户: " + userId + ", 积分: " + points);
        if (points == null || points <= 0) return;
// 直接调用原子操作
        userPointMapper.upsertAddTotalPoints(userId, points);

        log.info("【积分发放】成功 - userId: {}, points: {}", userId, points);
    }

    @Override
    public Integer calculateActualTotalPoints(Long userId) {
        int total = 0;

        // 服务积分
        List<SkillOrder> providedOrders = skillOrderMapper.selectMyProvidedCompletedOrders(userId);
        for (SkillOrder order : providedOrders) {
            if (order.getProviderPoints() != null) {
                total += order.getProviderPoints();
            }
        }

        //学习积分
        List<SkillOrder> requestedOrders = skillOrderMapper.selectMyRequestedCompletedOrders(userId);
        for (SkillOrder order : requestedOrders) {
            if (order.getLearnerPoints() != null) {
                total += order.getLearnerPoints();
            }
        }

        Integer activityPoints = EnrollmentMapper.selectCompletedActivityPoints(userId);
        if (activityPoints != null) {
            total += activityPoints;
        }

        return total;
    }

    @Override
    @Transactional
    public void calibrateTotalPoints(Long userId) {
        Integer actualTotal = calculateActualTotalPoints(userId);
        UserPoint current = userPointMapper.selectByUserId(userId);

        if (current == null) {
            // 初始化账户
            UserPoint newAccount = new UserPoint();
            newAccount.setUserId(userId);
            newAccount.setTotalPoints(actualTotal);
            newAccount.setUsedPoints(0);
            userPointMapper.insert(newAccount);
        } else {
            // 只有当不一致时才更新
            if (!Objects.equals(current.getTotalPoints(), actualTotal)) {
                userPointMapper.updateTotalPoints(userId, actualTotal);
            }
        }
    }

}

package com.ruoyi.student.member.service.impl;

import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.UserPoint;
import com.ruoyi.student.member.domain.vo.PointRecordVO;
import com.ruoyi.student.member.domain.vo.PointsOverviewVO;
import com.ruoyi.student.member.domain.vo.RankVO;
import com.ruoyi.student.member.mapper.EnrollmentMapper;
import com.ruoyi.student.member.mapper.SkillOrderMapper;
import com.ruoyi.student.member.mapper.UserPointMapper;
import com.ruoyi.student.member.service.IPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointsServiceImpl implements IPointsService {

    @Autowired
    private SkillOrderMapper skillOrderMapper;
    @Autowired
    private UserPointMapper userPointMapper;

    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Override
    public PointsOverviewVO getPointsOverview(Long userId) {
        //获取订单积分
        List<SkillOrder> asLearner = skillOrderMapper.selectMyRequestedCompletedOrders(userId);
        List<SkillOrder> asProvider = skillOrderMapper.selectMyProvidedCompletedOrders(userId);

        int study = 0, service = 0;
        List<PointRecordVO> records = new ArrayList<>();

        for (SkillOrder order : asLearner) {
            if (order.getLearnerPoints() != null && order.getLearnerPoints() > 0) {
                study += order.getLearnerPoints();
                records.add(toRecord(order, "study", order.getLearnerPoints()));
            }
        }
        for (SkillOrder order : asProvider) {
            if (order.getProviderPoints() != null && order.getProviderPoints() > 0) {
                service += order.getProviderPoints();
                records.add(toRecord(order, "service", order.getProviderPoints()));
            }
        }

        //获取活动积分
        int activity = enrollmentMapper.selectCompletedActivityPoints(userId);

        // 计算展示总分
        int displayTotal = study + service + activity;

        // 获取账户中的 used
        UserPoint account = userPointMapper.selectByUserId(userId);
        int used = Optional.ofNullable(account.getUsedPoints()).orElse(0);

        int available = Math.max(0, displayTotal - used);

        // 构造返回
        PointsOverviewVO vo = new PointsOverviewVO();
        vo.setTotal(displayTotal);     // 含活动
        vo.setStudy(study);
        vo.setService(service);
        vo.setActivity(activity);      // 建议加这个字段，方便前端
        vo.setUsed(used);
        vo.setAvailable(available);
        vo.setRecords(records);
        return vo;
    }

    private PointRecordVO toRecord(SkillOrder order, String type, Integer points) {
        PointRecordVO r = new PointRecordVO();
        r.setOrderId(order.getId());
        r.setSkillTitle(order.getSkillTitle());
        r.setAppointmentTime(order.getAppointmentTime());
        r.setPoints(points);
        r.setType(type);
        return r;
    }

    @Override
    public List<SkillOrder> getMyProvidedCompletedOrders(Long userId) {
        return skillOrderMapper.selectMyProvidedCompletedOrders(userId);
    }

    @Override
    public String generateProofForOrder(Long orderId) {
        return "";
    }

    @Override
    public List<RankVO> getTop10ByTotalPoints() {
        return userPointMapper.selectTop10WithUserInfo();
    }
}

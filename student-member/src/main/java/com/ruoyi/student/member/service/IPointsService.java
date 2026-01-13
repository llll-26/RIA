package com.ruoyi.student.member.service;


import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.vo.PointsOverviewVO;
import com.ruoyi.student.member.domain.vo.RankVO;

import java.util.List;


public interface IPointsService {
    PointsOverviewVO getPointsOverview(Long userId);

    List<SkillOrder> getMyProvidedCompletedOrders(Long userId);

    String generateProofForOrder(Long orderId);
    List<RankVO> getTop10ByTotalPoints();
}

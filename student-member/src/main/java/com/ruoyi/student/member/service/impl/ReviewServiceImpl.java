package com.ruoyi.student.member.service.impl;

import com.ruoyi.student.member.domain.ServiceReview;
import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.mapper.ReviewMapper;
import com.ruoyi.student.member.mapper.SkillOrderMapper;
import com.ruoyi.student.member.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private SkillOrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReview(Long currentUserId, ServiceReview review) {
        if (review == null || review.getOrderId() == null) {
            throw new IllegalArgumentException("订单ID不能为空");
        }
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("评分必须为1-5之间的整数");
        }

        Long orderId = review.getOrderId();

        //查询订单
        SkillOrder order = orderMapper.findById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        //校验订单状态是否已完成
        if (order.getStatus() != 4) {
            throw new RuntimeException("只能评论已完成的订单");
        }

        //检查是否已评论（可选：也可依赖数据库唯一索引）
        ServiceReview existing = reviewMapper.findByOrderIdAndReviewerId(orderId, currentUserId);
        if (existing != null) {
            throw new RuntimeException("已评论，不可重复提交");
        }

        //设置关联字段
        review.setReviewerId(currentUserId);
        review.setReviewedId(order.getProviderId());

        //插入评论
        reviewMapper.insert(review);
    }
}

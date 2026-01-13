package com.ruoyi.student.member.service;

import com.ruoyi.student.member.domain.ServiceReview;

public interface ReviewService {

    /**
     * 添加服务评价
     *
     * @param currentUserId 当前登录用户ID（必须是订单的 requester）
     * @param review        评论数据（需包含 orderId, rating, comment）
     */
    void addReview(Long currentUserId, ServiceReview review);
}

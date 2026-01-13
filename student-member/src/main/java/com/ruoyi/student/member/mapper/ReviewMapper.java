package com.ruoyi.student.member.mapper;


import com.ruoyi.student.member.domain.ServiceReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ReviewMapper {

    /**
     * 根据订单ID和评价人ID查询评论
     */
    ServiceReview findByOrderIdAndReviewerId(@Param("orderId") Long orderId, @Param("reviewerId") Long reviewerId);

    /**
     * 插入新评论
     */
    void insert(ServiceReview review);
}

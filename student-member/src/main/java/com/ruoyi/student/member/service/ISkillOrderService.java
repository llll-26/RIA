package com.ruoyi.student.member.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISkillOrderService {
    void createOrder(SkillOrder dto, Long requesterId);
    boolean hasActiveReservation(Long requesterId, Long skillId);

    List<SkillOrder> getMyRequestedOrders(Long requesterId); // 我预约的
    List<SkillOrder> getMyProvidedOrders(Long providerId);   // 别人预约我的

    OrderVO getMyOrderDetailAsRequester(Long orderId, Long requesterId);
    OrderVO getMyOrderDetailAsProvider(Long orderId, Long providerId);

    List<OrderVO> listByProviderId(Long userId);
    List<OrderVO> listByRequesterId(Long userId);

    void confirmOrder(Long id, Long providerId);

    void rejectOrder(Long id, Long providerId, String trimmedReason);

    void completeOrderByProvider(Long orderId, Long providerId, String locationDetail, String proofUrl);

    void confirmCompletionByRequester(Long orderId, Long requesterId);

    void completeOrder(Long id, Long providerId, String trim, String trim1);

    @Transactional(rollbackFor = Exception.class)
    void confirmCompletion(Long id, Long learnerId);

    List<OrderVO> getCompletedOrdersByUser(Long userId);


    List<SkillOrder> selectMyRequestedOrders(Long userId);

    List<SkillOrder> selectMyProvidedOrders(Long userId);


    Page<OrderVO> selectMyCompletedOrdersPage(Long userId);

    int countPendingOrdersByProvider(Long providerId);

    int countConfirmedUnreadByRequester(Long requesterId);

    SkillOrder getById(Long id);
    void updateById(SkillOrder order);
}

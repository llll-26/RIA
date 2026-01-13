package com.ruoyi.student.member.mapper;

;
import com.github.pagehelper.Page;
import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


@Mapper
public interface SkillOrderMapper {
    void insert(SkillOrder order);
    SkillOrder selectById(Long id);
    List<SkillOrder> selectByRequesterId(Long requesterId);
    List<SkillOrder> selectByProviderId(Long providerId);

    boolean existsByRequesterIdAndSkillIdAndStatusIn(
            @Param("requesterId") Long requesterId,
            @Param("skillId") Long skillId,
            @Param("statuses") List<Integer> statuses
    );
    List<OrderVO> selectMyOrdersWithDetails(Long requesterId);

    List<SkillOrder> listMyRequestedOrders(@Param("requesterId") Long requesterId);

    List<SkillOrder> listMyProvidedOrders(@Param("providerId") Long providerId);
    OrderVO selectMyOrderDetailAsRequester(@Param("orderId") Long orderId, @Param("requesterId") Long requesterId);
    OrderVO selectMyOrderDetailAsProvider(@Param("orderId") Long orderId, @Param("providerId") Long providerId);


    void updateById(SkillOrder order);

    List<SkillOrder> selectMyRequestedCompletedOrders(@Param("userId") Long userId);

    // 别人预约我的已完成订单（能看到 requester 给我的评论）
    List<SkillOrder> selectMyProvidedCompletedOrders(@Param("userId") Long userId);


    Page<OrderVO> selectMyCompletedOrdersPage(@Param("userId") Long userId);

    SkillOrder findById(Long orderId);

    int selectCountPendingByProvider(Long providerId);

    int selectCountConfirmedUnreadByRequester(Long requesterId);


}

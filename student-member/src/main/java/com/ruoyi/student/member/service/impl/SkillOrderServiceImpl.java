package com.ruoyi.student.member.service.impl;
import com.github.pagehelper.Page;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.student.member.domain.PointRule;
import com.ruoyi.student.member.domain.Skill;
import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.vo.OrderVO;
import com.ruoyi.student.member.mapper.PointRuleMapper;
import com.ruoyi.student.member.mapper.SkillMapper;
import com.ruoyi.student.member.mapper.SkillOrderMapper;
import com.ruoyi.student.member.service.IPointAccountService;
import com.ruoyi.student.member.service.ISkillOrderService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.PageUtils.startPage;


@Service
@Transactional
public class SkillOrderServiceImpl implements ISkillOrderService {

    private static final Map<Long, String> REJECT_REASONS = new ConcurrentHashMap<>();
    @Autowired
    private SkillOrderMapper orderMapper;
    @Autowired
    private SkillMapper skillMapper;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private PointRuleMapper pointRuleMapper;

    public void createOrder(SkillOrder dto, Long requesterId) {
        Skill skill = skillMapper.selectById(dto.getSkillId());
        if (skill == null || skill.getStatus() != 1) {
            throw new ServiceException("技能不存在或未通过审核");
        }
        if (skill.getUserId().equals(requesterId)) {
            throw new ServiceException("不能预约自己的技能");
        }

        LocalDateTime appointmentTime = dto.getAppointmentTime();

        SkillOrder order = new SkillOrder();
        order.setSkillId(dto.getSkillId());
        order.setRequesterId(requesterId);
        order.setProviderId(skill.getUserId());
        order.setAppointmentTime(appointmentTime);
        order.setLocationType((byte) 0);
        order.setStatus((int) 0); // 待确认
        order.setProofGenerated(false);
        order.setCreatedAt(LocalDateTime.now());

        orderMapper.insert(order);
    }
    public boolean hasActiveReservation(Long requesterId, Long skillId) {
        return orderMapper.existsByRequesterIdAndSkillIdAndStatusIn(
                requesterId,
                skillId,
                Arrays.asList(0, 1) // 待确认、已确认
        );
    }
    @Override
    public List<SkillOrder> getMyRequestedOrders(Long requesterId) {
        return orderMapper.listMyRequestedOrders(requesterId);
    }

    @Override
    public List<SkillOrder> getMyProvidedOrders(Long providerId) {
        return orderMapper.listMyProvidedOrders(providerId);
    }

    @Override
    public OrderVO getMyOrderDetailAsRequester(Long orderId, Long requesterId) {
        //先查订单基础信息
        OrderVO vo = orderMapper.selectMyOrderDetailAsRequester(orderId, requesterId);
        //如果订单不存在，直接返回 null
        if (vo == null) {
            return null;
        }
        //只有当订单状态为 "已确认"（status = 1）时，才去查并设置提供者的手机号
        if (vo.getStatus() != null && vo.getStatus() == 1) {
            SysUser provider = userService.selectUserById(vo.getProviderId());
            if (provider != null && provider.getPhonenumber() != null) {
                vo.setProviderPhone(provider.getPhonenumber());
            }
        }
        return vo;
    }

    @Override
    public OrderVO getMyOrderDetailAsProvider(Long orderId, Long providerId) {
        return orderMapper.selectMyOrderDetailAsProvider(orderId, providerId);
    }


    @Override
    public List<OrderVO> listByProviderId(Long providerId) {
        //先查原始订单
        List<SkillOrder> orders = orderMapper.selectByProviderId(providerId);

        return orders.stream().map(order -> {
            OrderVO vo = new OrderVO();
            BeanUtils.copyProperties(order, vo);

            //补充技能信息
            Skill skill = skillMapper.selectById(order.getSkillId());
            if (skill != null) {
                vo.setSkillTitle(skill.getTitle());
                vo.setDescription(skill.getDescription());
                vo.setCoverImg(skill.getCoverImg());
            }

            //补充预约者昵称
            SysUser requester = userService.selectUserById(order.getRequesterId());
            vo.setRequesterNickName(requester != null ? requester.getNickName() : "用户");

            //计算服务积分
            Integer points = calculatePointsForProvider(order.getSkillId());
            vo.setProviderPoints(points);

            return vo;
        }).collect(Collectors.toList());
    }

    private Integer calculatePointsForProvider(Long skillId) {
        if (skillId == null) {
            return 0;
        }
        Skill skill = skillMapper.selectById(skillId);
        if (skill == null || skill.getCategoryId() == null) {
            return 0;
        }
        PointRule rule = pointRuleMapper.selectByCategoryIdAndRoleType(
                Math.toIntExact(skill.getCategoryId()), 1
        );
        return rule != null ? rule.getPointValue() : 0;
    }
    private Integer calculatePointsForLearner(Long skillId) {
        if (skillId == null) {
            return 0;
        }
        Skill skill = skillMapper.selectById(skillId);
        if (skill == null || skill.getCategoryId() == null) {
            return 0; // 或记录 warn 日志
        }
        PointRule rule = pointRuleMapper.selectByCategoryIdAndRoleType(
                Math.toIntExact(skill.getCategoryId()), 2
        );
        return rule != null ? rule.getPointValue() : 0;
    }
    public List<OrderVO> listByRequesterId(Long requesterId) {

        // 获取订单列表
        List<SkillOrder> orders = orderMapper.selectByRequesterId(requesterId);

        return orders.stream().map(order -> {
            OrderVO vo = new OrderVO();


            BeanUtils.copyProperties(order, vo);
            // 获取技能信息
            Skill skill = skillMapper.selectById(order.getSkillId());
            if (skill != null) {
                vo.setSkillTitle(skill.getTitle());
                vo.setDescription(skill.getDescription()); // 假设技能有描述
                vo.setCoverImg(skill.getCoverImg()); // 假设技能有封面图片
            }

            // 获取提供者昵称
            SysUser provider = userService.selectUserById(order.getProviderId());
            vo.setProviderNickName(provider != null ? provider.getNickName() : "用户");

            // 计算学习积分
            Integer points = calculatePointsForLearner(order.getSkillId());
            vo.setLearnerPoints(points);

            // 如果有其他需要设置的信息，比如 locationDetail，可以在这里添加

            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public void confirmOrder(Long id, Long providerId) {
        SkillOrder order = orderMapper.selectById(id);
        if (order != null && order.getProviderId().equals(providerId)) {
            order.setStatus(1); // 假设 1 = 已确认（根据你数据库设计）
            orderMapper.updateById(order);
        }
    }

    @Override
    public void rejectOrder(Long id, Long providerId, String trimmedReason) {
        SkillOrder order = orderMapper.selectById(id);
        if (order != null && order.getProviderId().equals(providerId)) {
            order.setStatus(2); // 假设 2 = 已拒绝
            orderMapper.updateById(order);
        }
    }

    @Override
    public void completeOrderByProvider(Long orderId, Long providerId, String locationDetail, String proofUrl) {
        SkillOrder order = orderMapper.selectById(orderId);
        if (order == null || !order.getProviderId().equals(providerId)) {
            throw new ServiceException("订单不存在或无权限");
        }
        if (order.getStatus() != 1) {
            throw new ServiceException("只有已同意的订单才能完成");
        }

        // 更新地点和凭证
        order.setLocationDetail(locationDetail); // 覆盖或补充
        order.setProofUrl(proofUrl);
        order.setStatus(3); // 3 = 已完成
        orderMapper.updateById(order);
    }
    @Override
    public void confirmCompletionByRequester(Long orderId, Long requesterId) {
        SkillOrder order = orderMapper.selectById(orderId);
        if (order == null || !order.getRequesterId().equals(requesterId)) {
            throw new ServiceException("订单不存在或无权限");
        }
        if (order.getStatus() != 3) {
            throw new ServiceException("订单尚未完成，无法确认");
        }

        order.setStatus(4); // 4 = 已结束
        orderMapper.updateById(order);

    }

    @Override
    @Transactional
    public void completeOrder(Long orderId, Long providerId, String locationDetail, String proofUrl) {
        // 验证订单存在且属于该提供者
        SkillOrder order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new ServiceException("订单不存在");
        }
        if (!Objects.equals(order.getProviderId(), providerId)) {
            throw new ServiceException("无权操作此订单");
        }
        if (order.getStatus() == null ||
                !(order.getStatus() == 1 || order.getStatus() == 3)) {
            throw new ServiceException("只有已确认或已完成（待确认）的订单才能提交凭证");
        }

        //更新字段
        order.setLocationDetail(locationDetail);
        order.setProofUrl(proofUrl);
        order.setStatus(3);

        //保存
        orderMapper.updateById(order);
    }

    @Qualifier("pointAccountServiceImpl")
    @Autowired
    private IPointAccountService pointAccountService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirmCompletion(Long id, Long learnerId) {
        //查询订单（已包含积分、用户ID等）
        SkillOrder order = orderMapper.selectById(id);
        if (order == null) {
            throw new ServiceException("订单不存在");
        }
        if (!Objects.equals(order.getRequesterId(), learnerId)) {
            throw new ServiceException("无权限操作此订单");
        }

        //果已经是状态 4，直接返回
        if (Objects.equals(order.getStatus(), 4)) {
            return;
        }

        //校验状态必须是 3
        if (!Objects.equals(order.getStatus(), 3)) {
            throw new ServiceException("只有提供者已提交凭证的订单才能被确认");
        }

        //更新状态为 4
        order.setStatus(4);
        orderMapper.updateById(order);

        //发放积分
        Integer learnerPoints = order.getLearnerPoints();
        Integer providerPoints = order.getProviderPoints();

        if (learnerPoints != null && learnerPoints > 0) {
            pointAccountService.addTotalPoints(order.getRequesterId(), learnerPoints);
        }
        if (providerPoints != null && providerPoints > 0) {
            pointAccountService.addTotalPoints(order.getProviderId(), providerPoints);
        }
    }

    @Override
    public List<OrderVO> getCompletedOrdersByUser(Long userId) {
        // 查我发起的
        List<SkillOrder> requested = orderMapper.selectMyRequestedCompletedOrders(userId);
        // 查我接的
        List<SkillOrder> provided = orderMapper.selectMyProvidedCompletedOrders(userId);

        // 合并
        List<SkillOrder> all = new ArrayList<>();
        all.addAll(requested);
        all.addAll(provided);

        return all.stream().map(order -> {
            OrderVO vo = new OrderVO();
            BeanUtils.copyProperties(order, vo);

            Skill skill = skillMapper.selectById(order.getSkillId());
            if (skill != null) {
                vo.setSkillTitle(skill.getTitle());
                vo.setDescription(skill.getDescription());
                vo.setCoverImg(skill.getCoverImg());
                String method = (skill.getMethod() != null && skill.getMethod() == 0) ? "线上" : "线下";
                vo.setTeachingMethod(method);
            }

            SysUser user = userService.selectUserById(
                    order.getRequesterId().equals(userId) ? order.getProviderId() : order.getRequesterId()
            );
            String nickName = user != null ? user.getNickName() : "用户";
            // 区分显示
            if (order.getRequesterId().equals(userId)) {
                vo.setProviderNickName(nickName); // 我是 requester
            } else {
                vo.setRequesterNickName(nickName); // 我是 provider
            }

            Integer points = calculatePointsForLearner(order.getSkillId());
            vo.setLearnerPoints(points);
            vo.setProviderPoints(points);

            return vo;
        }).collect(Collectors.toList());
    }

    @Autowired
    private SkillOrderMapper skillOrderMapper;
    @Override
    public List<SkillOrder> selectMyRequestedOrders(Long userId) {
        return skillOrderMapper.selectByRequesterId(userId);
    }

    @Override
    public List<SkillOrder> selectMyProvidedOrders(Long userId) {
        return skillOrderMapper.selectByProviderId(userId);
    }

    @Override
    public Page<OrderVO> selectMyCompletedOrdersPage(Long userId) {
        return skillOrderMapper.selectMyCompletedOrdersPage(userId);
    }

    @Override
    public int countPendingOrdersByProvider(Long providerId) {
        // 假设状态字段为 status，"PENDING" 表示待确认
        // 请根据你数据库的实际字段调整条件
        return skillOrderMapper.selectCountPendingByProvider(providerId);
    }

    @Override
    public int countConfirmedUnreadByRequester(Long requesterId) {
        return skillOrderMapper.selectCountConfirmedUnreadByRequester(requesterId);
    }

    @Override
    public SkillOrder getById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void updateById(SkillOrder order) {
        orderMapper.updateById(order);
    }
}

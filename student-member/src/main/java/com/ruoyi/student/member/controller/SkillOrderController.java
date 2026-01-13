package com.ruoyi.student.member.controller;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.vo.OrderVO;

import com.ruoyi.student.member.mapper.SkillOrderMapper;
import com.ruoyi.student.member.service.ISkillOrderService;
import org.apache.ibatis.annotations.Result;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.utils.PageUtils.startPage;


@RestController
@RequestMapping("/skill-orders")
public class SkillOrderController {

    @Autowired
    private ISkillOrderService skillOrderService;
    @Autowired
    private SkillOrderMapper skillOrderMapper;

    @PostMapping
    public AjaxResult reserve(@RequestBody @Valid SkillOrder dto) {
        Long requesterId = SecurityUtils.getUserId();

        if (skillOrderService.hasActiveReservation(requesterId, dto.getSkillId())) {
            return AjaxResult.error("您已预约过该技能，请勿重复提交");
        }

        skillOrderService.createOrder(dto, requesterId);
        return AjaxResult.success("预约请求已发送，请等待对方确认");
    }
    @GetMapping("/my-orders")
    public AjaxResult listMyOrders() {
        startPage();
        Long userId = SecurityUtils.getUserId();
        Page<SkillOrder> page = (Page<SkillOrder>) skillOrderService.selectMyRequestedOrders(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("list", page.getResult());
        result.put("total", page.getTotal());

        return AjaxResult.success(result);
    }
    @GetMapping("/my-provided-orders")
    public AjaxResult getMyProvided() {
        startPage();
        Long userId = SecurityUtils.getUserId();
        Page<SkillOrder> page = (Page<SkillOrder>) skillOrderService.selectMyProvidedOrders(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("list", page.getResult());
        result.put("total", page.getTotal());

        return AjaxResult.success(result);
    }
    private Long getCurrentUserId(Authentication auth) {
        return Long.parseLong(auth.getName());
    }
    @GetMapping("/my-requested-order/{id}")
    public AjaxResult getMyRequestedOrderDetail(@PathVariable Long id) {
        Long currentUserId = SecurityUtils.getUserId();
        OrderVO order = skillOrderService.getMyOrderDetailAsRequester(id, currentUserId);
        if (order == null) {
            return AjaxResult.error(404, "订单不存在或无权查看");
        }
        return AjaxResult.success(order);
    }

    @GetMapping("/my-provided-order/{id}")
    public AjaxResult getMyProvidedOrderDetail(@PathVariable Long id) {
        Long currentUserId = SecurityUtils.getUserId();
        OrderVO order = skillOrderService.getMyOrderDetailAsProvider(id, currentUserId);
        if (order == null) {
            return AjaxResult.error(404, "订单不存在或无权查看");
        }
        return AjaxResult.success(order);
    }
    /**
     * 提供者同意订单
     */
    @PostMapping("/order/{id}/confirm")
    public AjaxResult confirmOrder(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return AjaxResult.error("订单ID无效");
        }
        Long providerId = SecurityUtils.getUserId();
        skillOrderService.confirmOrder(id, providerId);
        return AjaxResult.success("订单已确认");
    }

    /**
     * 提供者拒绝订单（直接接收字符串理由）
     */
    @PostMapping("/order/{id}/reject")
    public AjaxResult rejectOrder(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return AjaxResult.error("订单ID无效");
        }

        Long providerId = SecurityUtils.getUserId();
        skillOrderService.rejectOrder(id, providerId, null); // 理由传 null 或空字符串
        return AjaxResult.success("订单已拒绝");
    }
    /**
     * 提供者提交完成凭证
     */
    @PostMapping("/order/{id}/complete")
    public AjaxResult completeOrder(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {

        if (id == null || id <= 0) {
            return AjaxResult.error("订单ID无效");
        }

        String locationDetail = payload.get("locationDetail");
        String proofUrl = payload.get("proofUrl");

        if (locationDetail == null || locationDetail.trim().isEmpty()) {
            return AjaxResult.error("服务地点不能为空");
        }
        if (proofUrl == null || proofUrl.trim().isEmpty()) {
            return AjaxResult.error("完成凭证不能为空");
        }

        Long providerId = SecurityUtils.getUserId();
        skillOrderService.completeOrder(id, providerId, locationDetail.trim(), proofUrl.trim());
        return AjaxResult.success("凭证提交成功");

    }
    //学习者确认完成
    @PostMapping("/order/{id}/confirm-completion")
    public AjaxResult confirmCompletion(@PathVariable Long id) {
        Long learnerId = SecurityUtils.getUserId();
        skillOrderService.confirmCompletion(id, learnerId);
        return AjaxResult.success("订单已完成");
    }
    @GetMapping("/completed")
    public AjaxResult getCompletedOrders(@RequestParam(defaultValue = "1") int pageNum,
                                         @RequestParam(defaultValue = "2") int pageSize) {
        Long userId = SecurityUtils.getUserId();

        // 启动分页（使用前端传来的 pageNum 和 pageSize）
        Page<OrderVO> page = PageHelper.startPage(pageNum, pageSize);

        // 执行分页查询
        List<OrderVO> list = skillOrderService.selectMyCompletedOrdersPage(userId);

        // 构造分页结果
        PageInfo<OrderVO> pageInfo = new PageInfo<>(list);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", pageInfo.getTotal()); //使用分页插件计算的总数

        return AjaxResult.success(result);
    }
    /**
     * 获取当前用户作为技能提供者收到的未确认预约数量
     */
    @GetMapping("/unread-count")
    public AjaxResult getUnreadAppointmentCount() {
        Long providerId = SecurityUtils.getUserId();
        int count = skillOrderService.countPendingOrdersByProvider(providerId);
        return AjaxResult.success(count);
    }
    /**
     * 获取当前用户作为请求者，已被接受但未查看的预约数量
     */
    @GetMapping("/confirmed-unread-count")
    public AjaxResult getConfirmedUnreadCount() {
        Long requesterId = SecurityUtils.getUserId();
        int count = skillOrderService.countConfirmedUnreadByRequester(requesterId);
        return AjaxResult.success(count);
    }
    /**
     * 请求者查看已同意订单后，标记为已读（消除红点）
     */
    @PostMapping("/order/{id}/mark-read")
    public AjaxResult markOrderAsRead(@PathVariable Long id) {
        Long requesterId = SecurityUtils.getUserId();

        SkillOrder order = skillOrderService.getById(id);
        if (order == null || !requesterId.equals(order.getRequesterId())) {
            return AjaxResult.error("订单不存在或无权操作");
        }
        if (order.getStatus() != 1) {
            return AjaxResult.error("仅可标记状态为“已同意”的订单");
        }
        if (Boolean.TRUE.equals(order.getRead())) {
            return AjaxResult.success(); // 已读，无需操作
        }
        // 标记为已读
        order.setRead(true);
        skillOrderService.updateById(order);

        return AjaxResult.success();
    }
}


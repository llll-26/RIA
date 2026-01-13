package com.ruoyi.student.member.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.member.domain.RewardItem;
import com.ruoyi.student.member.domain.vo.RedeemedRewardVO;
import com.ruoyi.student.member.service.IPointAccountService;
import com.ruoyi.student.member.service.IUserRewardService;
import com.ruoyi.student.member.service.RewardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 积分奖品兑换控制器
 */
@RestController
@RequestMapping("/student/reward")
public class RewardController {

    @Autowired
    private RewardItemService rewardItemService;
  @ Autowired
  private IUserRewardService userRewardService;
    @Autowired
    private IPointAccountService pointService;
    /**
     * 获取可兑换奖品列表（仅 is_active = 1）
     */
    @GetMapping("/items")
    public AjaxResult getAvailableItems() {
        List<RewardItem> items = rewardItemService.listAvailableItems();
        return AjaxResult.success(items);
    }

    /**
     * 兑换奖品
     */
    @PostMapping("/exchange")
    public AjaxResult exchange(@RequestBody ExchangeRequest request) {
        Long userId = SecurityUtils.getUserId();
        try {
            Map<String, Object> result = userRewardService.exchangeReward(userId, request.getItemId(), request.getQuantity());
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
    @GetMapping("/redeemed")
    public AjaxResult getRedeemedRewards() {
        Long userId = SecurityUtils.getUserId();
        List<RedeemedRewardVO> redeemedList = userRewardService.getRedeemedRewards(userId);
        return AjaxResult.success(redeemedList);
    }

    // 请求参数类（内部类）
    public static class ExchangeRequest {
        private Integer itemId;
        private Integer quantity;


        public Integer getItemId() { return itemId; }
        public void setItemId(Integer itemId) { this.itemId = itemId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
}

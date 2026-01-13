package com.ruoyi.student.member.service;

import com.ruoyi.student.member.domain.RewardItem;

import java.util.List;

public interface RewardItemService {
    List<RewardItem> list(RewardItem item);

    List<RewardItem> listAvailableItems();

    RewardItem selectById(Integer itemId);

    void updateStock(Integer id, Integer quantity);
}

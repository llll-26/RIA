package com.ruoyi.student.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.student.member.domain.RewardItem;
import com.ruoyi.student.member.mapper.RewardItemMapper;
import com.ruoyi.student.member.service.RewardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//管理奖品信息（查询、库存等）
@Service
public class RewardItemServiceImpl implements RewardItemService {

    @Autowired
    private RewardItemMapper rewardItemMapper;

    @Override
    public List<RewardItem> list(RewardItem item) {
        return rewardItemMapper.selectList(item);
    }
    @Override
    public List<RewardItem> listAvailableItems() {
        // 构造查询条件：is_active = 1
        QueryWrapper<RewardItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_active", 1);

        // 执行查询
        return rewardItemMapper.selectAvailableItems();
    }

    @Override
    public RewardItem selectById(Integer itemId) {
        return rewardItemMapper.selectById(itemId);
    }

    @Override
    public void updateStock(Integer id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return;
        }
        int rows = rewardItemMapper.updateStock(id, quantity);
        if (rows == 0) {
            throw new RuntimeException("库存不足或奖品不可扣减");
        }
    }
}

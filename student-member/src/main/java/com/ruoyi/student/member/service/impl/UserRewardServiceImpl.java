package com.ruoyi.student.member.service.impl;
import com.ruoyi.student.member.domain.RewardItem;
import com.ruoyi.student.member.domain.UserReward;
import com.ruoyi.student.member.domain.vo.RedeemedRewardVO;
import com.ruoyi.student.member.mapper.UserRewardMapper;
import com.ruoyi.student.member.service.IPointAccountService;
import com.ruoyi.student.member.service.IUserRewardService;
import com.ruoyi.student.member.service.RewardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//完整实现“兑换奖品”业务流程
@Service
public class UserRewardServiceImpl implements IUserRewardService {

    @Autowired
    private UserRewardMapper userRewardMapper;
    @Qualifier("pointAccountServiceImpl")
    @Autowired
    private IPointAccountService pointAccountService;
    @Autowired
    private RewardItemService rewardItemService;
    @Override
    public void createExchangeRecord(UserReward record) {
        userRewardMapper.insert(record);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> exchangeReward(Long userId, Integer itemId, Integer quantity) {
        //查询奖品
        RewardItem item = rewardItemService.selectById(itemId);
        if (item == null || item.getIsActive() != 1) {
            throw new RuntimeException("奖品不存在或已下架");
        }

        //计算所需总积分
        int totalPoints = item.getPointsRequired() * quantity;
        if (totalPoints <= 0) {
            throw new RuntimeException("积分配置无效");
        }

        //扣除积分
        if (!pointAccountService.deductPoints(userId, totalPoints)) {
            throw new RuntimeException("积分不足，无法兑换");
        }

        //创建兑换记录（状态设为 1=已发放）
        UserReward record = new UserReward();
        record.setUserId(userId);
        record.setRewardId(item.getId());
        record.setPointsUsed(totalPoints);
        record.setCode(generateCode());
        record.setStatus(1);
        record.setQuantity(quantity);
        record.setCreatedAt(new Date(System.currentTimeMillis()));
        userRewardMapper.insert(record);

        //只对有限库存扣减，并且由数据库保证原子性
        if (item.getStock() != -1) {
            rewardItemService.updateStock(item.getId(), quantity);
        }

        return Map.of(
                "orderId", record.getId(),
                "code", record.getCode(),
                "pointsUsed", totalPoints,
                "itemName", item.getName()
        );
    }

    private String generateCode() {
        return "REWARD_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
    @Override
   public List<RedeemedRewardVO> getRedeemedRewards(Long userId) {
               return userRewardMapper.selectRedeemedRewardsByUserId(userId);
           }
}

package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizUserRewardMapper;
import com.ruoyi.student.domain.BizUserReward;
import com.ruoyi.student.service.IBizUserRewardService;

/**
 * 用户兑换记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-27
 */
@Service
public class BizUserRewardServiceImpl implements IBizUserRewardService 
{
    @Autowired
    private BizUserRewardMapper bizUserRewardMapper;

    /**
     * 查询用户兑换记录
     * 
     * @param id 用户兑换记录主键
     * @return 用户兑换记录
     */
    @Override
    public BizUserReward selectBizUserRewardById(Long id)
    {
        return bizUserRewardMapper.selectBizUserRewardById(id);
    }

    /**
     * 查询用户兑换记录列表
     * 
     * @param bizUserReward 用户兑换记录
     * @return 用户兑换记录
     */
    @Override
    public List<BizUserReward> selectBizUserRewardList(BizUserReward bizUserReward)
    {
        return bizUserRewardMapper.selectBizUserRewardList(bizUserReward);
    }

    /**
     * 新增用户兑换记录
     * 
     * @param bizUserReward 用户兑换记录
     * @return 结果
     */
    @Override
    public int insertBizUserReward(BizUserReward bizUserReward)
    {
        return bizUserRewardMapper.insertBizUserReward(bizUserReward);
    }

    /**
     * 修改用户兑换记录
     * 
     * @param bizUserReward 用户兑换记录
     * @return 结果
     */
    @Override
    public int updateBizUserReward(BizUserReward bizUserReward)
    {
        return bizUserRewardMapper.updateBizUserReward(bizUserReward);
    }

    /**
     * 批量删除用户兑换记录
     * 
     * @param ids 需要删除的用户兑换记录主键
     * @return 结果
     */
    @Override
    public int deleteBizUserRewardByIds(Long[] ids)
    {
        return bizUserRewardMapper.deleteBizUserRewardByIds(ids);
    }

    /**
     * 删除用户兑换记录信息
     * 
     * @param id 用户兑换记录主键
     * @return 结果
     */
    @Override
    public int deleteBizUserRewardById(Long id)
    {
        return bizUserRewardMapper.deleteBizUserRewardById(id);
    }
}

package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizUserReward;

/**
 * 用户兑换记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-27
 */
public interface BizUserRewardMapper 
{
    /**
     * 查询用户兑换记录
     * 
     * @param id 用户兑换记录主键
     * @return 用户兑换记录
     */
    public BizUserReward selectBizUserRewardById(Long id);

    /**
     * 查询用户兑换记录列表
     * 
     * @param bizUserReward 用户兑换记录
     * @return 用户兑换记录集合
     */
    public List<BizUserReward> selectBizUserRewardList(BizUserReward bizUserReward);

    /**
     * 新增用户兑换记录
     * 
     * @param bizUserReward 用户兑换记录
     * @return 结果
     */
    public int insertBizUserReward(BizUserReward bizUserReward);

    /**
     * 修改用户兑换记录
     * 
     * @param bizUserReward 用户兑换记录
     * @return 结果
     */
    public int updateBizUserReward(BizUserReward bizUserReward);

    /**
     * 删除用户兑换记录
     * 
     * @param id 用户兑换记录主键
     * @return 结果
     */
    public int deleteBizUserRewardById(Long id);

    /**
     * 批量删除用户兑换记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserRewardByIds(Long[] ids);
}

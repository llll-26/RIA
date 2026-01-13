package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizRewardItemMapper;
import com.ruoyi.student.domain.BizRewardItem;
import com.ruoyi.student.service.IBizRewardItemService;

/**
 * 兑换奖品项Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-27
 */
@Service
public class BizRewardItemServiceImpl implements IBizRewardItemService
{
    @Autowired
    private BizRewardItemMapper bizRewardItemMapper;

    /**
     * 查询兑换奖品项
     *
     * @param id 兑换奖品项主键
     * @return 兑换奖品项
     */
    @Override
    public BizRewardItem selectBizRewardItemById(Long id)
    {
        return bizRewardItemMapper.selectBizRewardItemById(id);
    }

    /**
     * 查询兑换奖品项列表
     *
     * @param bizRewardItem 兑换奖品项
     * @return 兑换奖品项
     */
    @Override
    public List<BizRewardItem> selectBizRewardItemList(BizRewardItem bizRewardItem)
    {
        return bizRewardItemMapper.selectBizRewardItemList(bizRewardItem);
    }

    /**
     * 新增兑换奖品项
     *
     * @param bizRewardItem 兑换奖品项
     * @return 结果
     */
    @Override
    public int insertBizRewardItem(BizRewardItem bizRewardItem)
    {
        return bizRewardItemMapper.insertBizRewardItem(bizRewardItem);
    }

    /**
     * 修改兑换奖品项
     *
     * @param bizRewardItem 兑换奖品项
     * @return 结果
     */
    @Override
    public int updateBizRewardItem(BizRewardItem bizRewardItem)
    {
        return bizRewardItemMapper.updateBizRewardItem(bizRewardItem);
    }

    /**
     * 批量删除兑换奖品项
     *
     * @param ids 需要删除的兑换奖品项主键
     * @return 结果
     */
    @Override
    public int deleteBizRewardItemByIds(Long[] ids)
    {
        return bizRewardItemMapper.deleteBizRewardItemByIds(ids);
    }

    /**
     * 删除兑换奖品项信息
     *
     * @param id 兑换奖品项主键
     * @return 结果
     */
    @Override
    public int deleteBizRewardItemById(Long id)
    {
        return bizRewardItemMapper.deleteBizRewardItemById(id);
    }

    @Override
    public int hideRewardItems(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return 0;
        }
        return bizRewardItemMapper.hideRewardItems(ids);
    }
}

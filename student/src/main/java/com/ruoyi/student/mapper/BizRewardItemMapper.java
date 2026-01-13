package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizRewardItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 兑换奖品项Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-27
 */
@Mapper
public interface BizRewardItemMapper
{
    /**
     * 查询兑换奖品项
     *
     * @param id 兑换奖品项主键
     * @return 兑换奖品项
     */
    public BizRewardItem selectBizRewardItemById(Long id);

    /**
     * 查询兑换奖品项列表
     *
     * @param bizRewardItem 兑换奖品项
     * @return 兑换奖品项集合
     */
    public List<BizRewardItem> selectBizRewardItemList(BizRewardItem bizRewardItem);

    /**
     * 新增兑换奖品项
     *
     * @param bizRewardItem 兑换奖品项
     * @return 结果
     */
    public int insertBizRewardItem(BizRewardItem bizRewardItem);

    /**
     * 修改兑换奖品项
     *
     * @param bizRewardItem 兑换奖品项
     * @return 结果
     */
    public int updateBizRewardItem(BizRewardItem bizRewardItem);

    /**
     * 删除兑换奖品项
     *
     * @param id 兑换奖品项主键
     * @return 结果
     */
    public int deleteBizRewardItemById(Long id);

    /**
     * 批量删除兑换奖品项
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizRewardItemByIds(Long[] ids);
    /**
     * 批量下架奖品（设置 is_active = 0）
     *
     * @param ids 奖品ID数组
     * @return 影响行数
     */
    int hideRewardItems(@Param("ids") Long[] ids);
}

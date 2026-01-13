package com.ruoyi.student.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.student.domain.BizSkillOrder;

/**
 * 技能订单Service接口
 *
 * @author ruoyi
 * @date 2025-12-27
 */
public interface IBizSkillOrderService
{
    /**
     * 查询技能订单
     *
     * @param id 技能订单主键
     * @return 技能订单
     */
    public BizSkillOrder selectBizSkillOrderById(Long id);

    /**
     * 查询技能订单列表
     *
     * @param bizSkillOrder 技能订单
     * @return 技能订单集合
     */
    public List<BizSkillOrder> selectBizSkillOrderList(BizSkillOrder bizSkillOrder);

    /**
     * 新增技能订单
     *
     * @param bizSkillOrder 技能订单
     * @return 结果
     */
    public int insertBizSkillOrder(BizSkillOrder bizSkillOrder);

    /**
     * 修改技能订单
     *
     * @param bizSkillOrder 技能订单
     * @return 结果
     */
    public int updateBizSkillOrder(BizSkillOrder bizSkillOrder);

    /**
     * 批量删除技能订单
     *
     * @param ids 需要删除的技能订单主键集合
     * @return 结果
     */
    public int deleteBizSkillOrderByIds(Long[] ids);

    /**
     * 删除技能订单信息
     *
     * @param id 技能订单主键
     * @return 结果
     */
    public int deleteBizSkillOrderById(Long id);

    List<Map<String, Object>> selectOrderStatusDistribution();

    Object selectOrderCount();
    List<Map<String, Object>> selectSkillOrderCountDistribution();
}

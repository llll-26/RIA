package com.ruoyi.student.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizSkillOrderMapper;
import com.ruoyi.student.domain.BizSkillOrder;
import com.ruoyi.student.service.IBizSkillOrderService;

/**
 * 技能订单Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-27
 */
@Service
public class BizSkillOrderServiceImpl implements IBizSkillOrderService
{
    @Autowired
    private BizSkillOrderMapper bizSkillOrderMapper;

    /**
     * 查询技能订单
     *
     * @param id 技能订单主键
     * @return 技能订单
     */
    @Override
    public BizSkillOrder selectBizSkillOrderById(Long id)
    {
        return bizSkillOrderMapper.selectBizSkillOrderById(id);
    }

    /**
     * 查询技能订单列表
     *
     * @param bizSkillOrder 技能订单
     * @return 技能订单
     */
    @Override
    public List<BizSkillOrder> selectBizSkillOrderList(BizSkillOrder bizSkillOrder)
    {
        return bizSkillOrderMapper.selectBizSkillOrderList(bizSkillOrder);
    }

    /**
     * 新增技能订单
     *
     * @param bizSkillOrder 技能订单
     * @return 结果
     */
    @Override
    public int insertBizSkillOrder(BizSkillOrder bizSkillOrder)
    {
        return bizSkillOrderMapper.insertBizSkillOrder(bizSkillOrder);
    }

    /**
     * 修改技能订单
     *
     * @param bizSkillOrder 技能订单
     * @return 结果
     */
    @Override
    public int updateBizSkillOrder(BizSkillOrder bizSkillOrder)
    {
        return bizSkillOrderMapper.updateBizSkillOrder(bizSkillOrder);
    }

    /**
     * 批量删除技能订单
     *
     * @param ids 需要删除的技能订单主键
     * @return 结果
     */
    @Override
    public int deleteBizSkillOrderByIds(Long[] ids)
    {
        return bizSkillOrderMapper.deleteBizSkillOrderByIds(ids);
    }

    /**
     * 删除技能订单信息
     *
     * @param id 技能订单主键
     * @return 结果
     */
    @Override
    public int deleteBizSkillOrderById(Long id)
    {
        return bizSkillOrderMapper.deleteBizSkillOrderById(id);
    }

    @Override
    public List<Map<String, Object>> selectOrderStatusDistribution() {
        return bizSkillOrderMapper.selectOrderStatusDistribution();
    }

    @Override
    public Long selectOrderCount() {
        return bizSkillOrderMapper.selectOrderCount();
    }

    @Override
    public List<Map<String, Object>> selectSkillOrderCountDistribution() {
        return bizSkillOrderMapper.selectSkillOrderCountDistribution();
    }
}

package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.student.mapper.BizSkillOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizSkillServiceMapper;
import com.ruoyi.student.domain.BizSkillService;
import com.ruoyi.student.service.IBizSkillServiceService;

/**
 * 技能服务Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class BizSkillServiceServiceImpl implements IBizSkillServiceService
{
    @Autowired
    private BizSkillServiceMapper bizSkillServiceMapper;

    /**
     * 查询技能服务
     *
     * @param id 技能服务主键
     * @return 技能服务
     */
    @Override
    public BizSkillService selectBizSkillServiceById(Long id)
    {
        return bizSkillServiceMapper.selectBizSkillServiceById(id);
    }

    /**
     * 查询技能服务列表
     *
     * @param bizSkillService 技能服务
     * @return 技能服务
     */
    @Override
    public List<BizSkillService> selectBizSkillServiceList(BizSkillService bizSkillService)
    {
        return bizSkillServiceMapper.selectBizSkillServiceList(bizSkillService);
    }

    /**
     * 新增技能服务
     *
     * @param bizSkillService 技能服务
     * @return 结果
     */
    @Override
    public int insertBizSkillService(BizSkillService bizSkillService)
    {
        return bizSkillServiceMapper.insertBizSkillService(bizSkillService);
    }

    /**
     * 批量删除技能服务
     *
     * @param ids 需要删除的技能服务主键
     * @return 结果
     */
    @Override
    public int deleteBizSkillServiceByIds(Long[] ids)
    {
        return bizSkillServiceMapper.deleteBizSkillServiceByIds(ids);
    }

    /**
     * 删除技能服务信息
     *
     * @param id 技能服务主键
     * @return 结果
     */
    @Override
    public int deleteBizSkillServiceById(Long id)
    {
        return bizSkillServiceMapper.deleteBizSkillServiceById(id);
    }

    @Override
    public int auditBizSkillService(BizSkillService skill) {
        return 0;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        BizSkillService entity = this.selectBizSkillServiceById(id);
        if (entity == null) {
            throw new ServiceException("技能服务不存在，ID：" + id);
        }
        // 改为调用新方法
        bizSkillServiceMapper.updateStatusById(id, status);
    }

    @Autowired
    private BizSkillServiceMapper skillMapper;

    @Override
    public Long selectSkillCount() {
        return skillMapper.selectSkillCount();
    }
    @Autowired
    private BizSkillOrderMapper orderMapper;

    @Override
    public Long selectOrderCount() {
        return orderMapper.selectOrderCount();
    }
}

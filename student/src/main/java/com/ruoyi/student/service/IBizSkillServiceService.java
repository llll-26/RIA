package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.BizSkillService;

/**
 * 技能服务Service接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IBizSkillServiceService
{
    /**
     * 查询技能服务
     *
     * @param id 技能服务主键
     * @return 技能服务
     */
    public BizSkillService selectBizSkillServiceById(Long id);

    /**
     * 查询技能服务列表
     *
     * @param bizSkillService 技能服务
     * @return 技能服务集合
     */
    public List<BizSkillService> selectBizSkillServiceList(BizSkillService bizSkillService);

    /**
     * 新增技能服务
     *
     * @param bizSkillService 技能服务
     * @return 结果
     */
    public int insertBizSkillService(BizSkillService bizSkillService);



    /**
     * 批量删除技能服务
     *
     * @param ids 需要删除的技能服务主键集合
     * @return 结果
     */
    public int deleteBizSkillServiceByIds(Long[] ids);

    /**
     * 删除技能服务信息
     *
     * @param id 技能服务主键
     * @return 结果
     */
    public int deleteBizSkillServiceById(Long id);

    int auditBizSkillService(BizSkillService skill);

    void updateStatus(Long id, Integer status);

    Long selectSkillCount();

    Long selectOrderCount();
}

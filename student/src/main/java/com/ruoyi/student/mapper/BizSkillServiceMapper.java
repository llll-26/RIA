package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizSkillService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 技能服务Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface BizSkillServiceMapper
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
     * 删除技能服务
     *
     * @param id 技能服务主键
     * @return 结果
     */
    public int deleteBizSkillServiceById(Long id);

    /**
     * 批量删除技能服务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizSkillServiceByIds(Long[] ids);
    /**
     * 仅更新技能服务的审核状态
     *
     * @param id     技能ID
     * @param status 状态值（0/1/2）
     * @return 影响行数
     */
    int updateStatusById(@Param("id") Long id, @Param("status") Integer status);

    Long selectSkillCount();
}

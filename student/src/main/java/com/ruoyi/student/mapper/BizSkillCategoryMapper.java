package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizSkillCategory;
import org.apache.ibatis.annotations.Mapper;


/**
 * 技能标准分类Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-12
 */
@Mapper
public interface BizSkillCategoryMapper
{
    /**
     * 查询技能标准分类
     *
     * @param id 技能标准分类主键
     * @return 技能标准分类
     */
    public BizSkillCategory selectBizSkillCategoryById(Long id);

    /**
     * 查询技能标准分类列表
     *
     * @param bizSkillCategory 技能标准分类
     * @return 技能标准分类集合
     */
    public List<BizSkillCategory> selectBizSkillCategoryList(BizSkillCategory bizSkillCategory);

    /**
     * 新增技能标准分类
     *
     * @param bizSkillCategory 技能标准分类
     * @return 结果
     */
    public int insertBizSkillCategory(BizSkillCategory bizSkillCategory);

    /**
     * 修改技能标准分类
     *
     * @param bizSkillCategory 技能标准分类
     * @return 结果
     */
    public int updateBizSkillCategory(BizSkillCategory bizSkillCategory);

    /**
     * 删除技能标准分类
     *
     * @param id 技能标准分类主键
     * @return 结果
     */
    public int deleteBizSkillCategoryById(Long id);

    /**
     * 批量删除技能标准分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizSkillCategoryByIds(Long[] ids);
}

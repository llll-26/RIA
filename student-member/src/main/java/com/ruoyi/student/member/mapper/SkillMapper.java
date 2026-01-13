package com.ruoyi.student.member.mapper;
import com.ruoyi.student.member.domain.Skill;
import com.ruoyi.student.member.domain.SkillCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SkillMapper {
    List<Skill> selectSkillList(Skill skill);
    int insertSkill(Skill skill);
    List<SkillCategory> selectCategories(); // 分类列表
    int updateSkill(Skill skill);
    List<Skill> selectByStatus(int i);

    Skill selectById(Long id);

    SkillCategory selectCategoryById(Long id);


    List<Skill> selectPublicSkillsWithKeyword(@Param("keyword") String keyword);

    void deleteById(Long skillId);
}

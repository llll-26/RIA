package com.ruoyi.student.member.service;

import com.ruoyi.student.member.domain.Skill;
import com.ruoyi.student.member.domain.SkillCategory;
import com.ruoyi.student.member.domain.vo.SkillVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISkillService {
    List<SkillCategory> getCategories();
    void publishSkill(Skill skill, Long currentUserId);
    List<SkillCategory> getSkillCategoriesWithPoints();

    List<Skill> getMyPublishedSkills(Long userId);


    List<SkillVo> listPublicSkillsForView(String keyword);

    SkillVo getSkillDetail(Long id);

    void deleteSkillById(Long id, Long currentUserId);

    Skill selectSkillById(Long id);

    void updateSkill(Skill skill, MultipartFile coverImg, Long currentUserId);
}

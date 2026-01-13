package com.ruoyi.student.member.service.impl;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.student.member.domain.PointRule;
import com.ruoyi.student.member.domain.Skill;
import com.ruoyi.student.member.domain.SkillCategory;
import com.ruoyi.student.member.domain.vo.SkillVo;
import com.ruoyi.student.member.mapper.PointRuleMapper;
import com.ruoyi.student.member.mapper.SkillMapper;
import com.ruoyi.student.member.service.ISkillService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

@Service
public class SkillServiceImpl implements ISkillService {

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private PointRuleMapper pointRuleMapper;
    @Override
    public List<SkillCategory> getCategories() {
        return skillMapper.selectCategories();
    }

    @Override
    @Transactional
    public void publishSkill(Skill skill, Long currentUserId) {
        if (StringUtils.isBlank(skill.getTitle()) || StringUtils.isBlank(skill.getDescription())) {
            throw new ServiceException("标题和描述不能为空");
        }
        if (skill.getCategoryId() == null && StringUtils.isBlank(skill.getCustomCategory())) {
            throw new ServiceException("请选择分类或填写自定义分类");
        }

        if (skill.getCategoryId() != null) {
            SkillCategory category = skillMapper.selectCategoryById(skill.getCategoryId());
            if (category == null || !Boolean.TRUE.equals(category.getActive())) {
                throw new ServiceException("所选技能分类不可用");
            }
            skill.setPointsPerHour(category.getPointsPerHour());
        } else {
            skill.setPointsPerHour(0);
        }

        skill.setUserId(currentUserId);
        skill.setStatus(0);
        skill.setCreateTime(new Date(System.currentTimeMillis()));
        skillMapper.insertSkill(skill);
    }

    @Override
    public List<SkillCategory> getSkillCategoriesWithPoints() {
        return skillMapper.selectCategories();
    }

    @Override
    public List<Skill> getMyPublishedSkills(Long userId) {
        Skill skill = new Skill();
        skill.setUserId(userId);
        return skillMapper.selectSkillList(skill);
    }

    @Override
    @Transactional
    public void updateSkill(Skill skill, MultipartFile coverImg, Long currentUserId) {
        // 设置用户 ID
        skill.setUserId(currentUserId);
        // 重置审核状态
        skill.setStatus(0);

        // 处理图片
        if (coverImg != null && !coverImg.isEmpty()) {
            try {
                String baseDir = RuoYiConfig.getProfile() + File.separator + "skill";
                String imgUrl = FileUploadUtils.upload(baseDir, coverImg);
                skill.setCoverImg(imgUrl);
            } catch (Exception e) {
                log.error("技能封面上传失败", e);
                throw new ServiceException("图片上传失败: " + e.getMessage());
            }
        }

        // 执行更新
        int rows = skillMapper.updateSkill(skill);
        if (rows == 0) {
            throw new ServiceException("技能不存在或无权修改");
        }
    }

    @Override
    public List<SkillVo> listPublicSkillsForView(String keyword) {
        List<Skill> skills = skillMapper.selectPublicSkillsWithKeyword(keyword);
        return skills.stream()
                .map(this::convertToSkillVo)
                .collect(Collectors.toList());
    }


    @Override
    public SkillVo getSkillDetail(Long id) {
        if (id == null) {
            return null;
        }

        // 1. 查询技能
        Skill skill = skillMapper.selectById(id);
        if (skill == null || skill.getStatus() != 1) {
            return null;
        }

        // 2. 复用转换逻辑
        return convertToSkillVo(skill);
    }

    public void deleteSkillById(Long skillId, Long userId) {
        Skill skill = skillMapper.selectById(skillId);
        if (skill == null) {
            throw new ServiceException("技能不存在");
        }
        if (!skill.getUserId().equals(userId)) {
            throw new ServiceException("无权删除他人技能");
        }
        skillMapper.deleteById(skillId);
    }

    @Override
    public Skill selectSkillById(Long id) {
        return skillMapper.selectById(id);
    }

    private SkillVo convertToSkillVo(Skill skill) {
        SkillVo vo = new SkillVo();
        vo.setId(skill.getId());
        vo.setTitle(skill.getTitle());
        vo.setDescription(skill.getDescription());
        vo.setCoverImg(skill.getCoverImg());
        vo.setAvailableTime(skill.getAvailableTime());
        vo.setUserId(skill.getUserId());

        Byte method = skill.getMethod();
        if (method != null) {
            vo.setTeachingMethod(method == 0 ? "线上" : "线下");
        } else {
            vo.setTeachingMethod("未设置");
        }

        //发布者昵称
        SysUser user = userService.selectUserById(skill.getUserId());
        vo.setNickName(user != null ? user.getNickName() : "用户昵称");

        //积分
        Integer pointsPerHour = 0;
        if (skill.getCategoryId() != null) {
            PointRule rule = pointRuleMapper.selectByCategoryIdAndRoleType(
                    skill.getCategoryId().intValue(), 2);
            if (rule != null && rule.getPointValue() != null) {
                pointsPerHour = rule.getPointValue();
            }
        }
        vo.setPointsPerHour(pointsPerHour);

        return vo;
    }
}

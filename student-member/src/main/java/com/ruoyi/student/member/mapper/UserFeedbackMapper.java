package com.ruoyi.student.member.mapper;
import com.ruoyi.student.member.domain.UserFeedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFeedbackMapper {
    int insert(UserFeedback feedback);
}

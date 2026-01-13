package com.ruoyi.student.member.mapper;

import com.ruoyi.student.member.domain.Enrollment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnrollmentMapper {
    int insert(Enrollment record);

    boolean existsByUserIdAndActivityId(@Param("userId") Long userId, @Param("activityId") Long activityId);

    List<Enrollment> selectByUserId(Long userId);

    List<Enrollment> selectCompletedByUserId(Long userId);

    Enrollment selectById(Long enrollmentId);

    void updateById(Enrollment enroll);

    Integer selectPointsRewardByActivityId(Long activityId);
    // EnrollmentMapper.java
    List<Enrollment> selectByUserIdWithPagination(@Param("userId") Long userId,
                                                  @Param("offset") int offset,
                                                  @Param("limit") int limit);
    int countByUserId(Long userId);

    int selectCompletedActivityPoints(Long userId);
}

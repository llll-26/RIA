package com.ruoyi.student.member.mapper;

import com.ruoyi.student.member.domain.UserPoint;
import com.ruoyi.student.member.domain.vo.PointRecordVO;
import com.ruoyi.student.member.domain.vo.RankVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPointMapper {
    /**
     * 获取用户积分信息
     */
    UserPoint selectByUserId(@Param("userId") Long userId);

    /**
     * 增加已使用积分（原子操作）
     * @return 影响行数（0 表示失败，可能因为 used_points 超过 total_points）
     */
    int addUsedPoints(@Param("userId") Long userId, @Param("points") Integer points);


    void insert(UserPoint account);

    void updateTotalPoints(@Param("userId") Long userId, @Param("totalPoints") Integer totalPoints);


    List<RankVO> selectTop10WithUserInfo();

    void upsertAddTotalPoints(@Param("userId") Long userId, @Param("points") Integer points);
}

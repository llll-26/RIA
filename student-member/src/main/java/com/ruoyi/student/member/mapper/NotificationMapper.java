package com.ruoyi.student.member.mapper;


import com.ruoyi.student.member.domain.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface NotificationMapper {
    List<Notification> selectByUserId(@Param("userId") Long userId);
    int updateIsRead(@Param("id") Long id, @Param("isRead") Integer isRead);
}

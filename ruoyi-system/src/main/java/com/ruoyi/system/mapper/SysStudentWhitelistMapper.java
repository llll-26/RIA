package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysStudentWhitelist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysStudentWhitelistMapper {
    // 不再依赖 selectById（那是查 id 的）
    SysStudentWhitelist selectByStudentId(@Param("studentId") String studentId);
    /**
     * 插入一条记录
     */
    int insertWhitelist(SysStudentWhitelist whitelist);

    /**
     * 根据学号更新学院和专业
     */
    int updateByStudentId(SysStudentWhitelist whitelist);

    boolean existsByStudentId(String studentId);
    List<SysStudentWhitelist> selectList();
}

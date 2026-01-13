package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysStudentWhitelist;

import java.util.List;

public interface ISysStudentWhitelistService {
    SysStudentWhitelist selectWhitelistById(String studentId);
    /**
     * 批量导入白名单（根据 student_id 判断是否存在）
     */
    void batchInsertOrUpdate(List<SysStudentWhitelist> list);
    boolean existsByStudentId(String studentId);

    List<SysStudentWhitelist> selectList(Object o);
}

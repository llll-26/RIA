package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysStudentWhitelist;
import com.ruoyi.system.mapper.SysStudentWhitelistMapper;
import com.ruoyi.system.service.ISysStudentWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysStudentWhitelistServiceImpl implements ISysStudentWhitelistService {

    @Autowired
    private SysStudentWhitelistMapper whitelistMapper;

    @Override
    public SysStudentWhitelist selectWhitelistById(String studentId) {
        return whitelistMapper.selectByStudentId(studentId);
    }

    @Override
    public boolean existsByStudentId(String studentId) {
        return whitelistMapper.existsByStudentId(studentId);
    }

    @Override
    public List<SysStudentWhitelist> selectList(Object o) {
        return whitelistMapper.selectList();
    }
    /**
     * 批量插入或更新（按 student_id 判断）
     * - 存在 → 更新 dept_id, major
     * - 不存在 → 插入新记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertOrUpdate(List<SysStudentWhitelist> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (SysStudentWhitelist item : list) {
            // 必须有 studentId 才能判断
            if (item.getStudentId() == null || item.getStudentId().trim().isEmpty()) {
                continue; // 跳过无效数据（可根据需求改为报错）
            }

            if (whitelistMapper.existsByStudentId(item.getStudentId())) {
                // 已存在：执行更新（只更新 dept_id 和 major）
                whitelistMapper.updateByStudentId(item);
            } else {
                // 不存在：执行插入
                whitelistMapper.insertWhitelist(item);
            }
        }
    }
}

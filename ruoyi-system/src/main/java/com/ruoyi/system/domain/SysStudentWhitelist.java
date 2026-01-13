package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SysStudentWhitelist {
    @TableId(type = IdType.AUTO)
    private Long id;               // 新增

    @TableField(value = "student_id")
    private String studentId;

    /**
     * 专业
     */
    private String major;

    /**
     * 所属部门ID（学院ID，关键字段！）
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER) // 防止前端传字符串
    private Long deptId;

    private String importBatch;
    /**
     * 所属部门名称
     */
    private String realName;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getImportBatch() {
        return importBatch;
    }

    public void setImportBatch(String importBatch) {
        this.importBatch = importBatch;
    }
}

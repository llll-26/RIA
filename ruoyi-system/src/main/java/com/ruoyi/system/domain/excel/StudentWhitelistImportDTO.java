package com.ruoyi.system.domain.excel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.Excel;


public class StudentWhitelistImportDTO {
    @ExcelProperty("学号")
    @Excel(name = "学号", width = 20)


    private String studentId;

    @ExcelProperty("学院ID")
    @Excel(name = "学院ID", width = 15, cellType = Excel.ColumnType.NUMERIC)
    private Long deptId;

    @ExcelProperty("专业")
    @Excel(name = "专业", width = 30)
    private String major;

    private String importBatch;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getImportBatch() {
        return importBatch;
    }

    public void setImportBatch(String importBatch) {
        this.importBatch = importBatch;
    }
}

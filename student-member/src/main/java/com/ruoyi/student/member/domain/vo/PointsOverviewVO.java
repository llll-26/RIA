package com.ruoyi.student.member.domain.vo;

import java.util.List;
//表示用户积分总览
public class PointsOverviewVO {
    private Integer total;
    private Integer service; // 作为 provider 获得的积分总和
    private Integer study;   // 作为 learner 获得的积分总和
    private List<PointRecordVO> records;
    private Integer used;       // 已使用
    private Integer available;  // 可用
    private int activity;

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public Integer getService() { return service; }
    public void setService(Integer service) { this.service = service; }

    public Integer getStudy() { return study; }
    public void setStudy(Integer study) { this.study = study; }

    public List<PointRecordVO> getRecords() { return records; }
    public void setRecords(List<PointRecordVO> records) { this.records = records; }
}

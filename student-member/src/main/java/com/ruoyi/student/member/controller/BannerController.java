package com.ruoyi.student.member.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.student.domain.BizActivity;
import com.ruoyi.student.service.IBizActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student/banner")
public class BannerController {

    @Autowired
    private IBizActivityService bizActivityService;

    /**
     * 获取首页轮播图（取前5个“进行中”或“即将开始”的活动）
     */
    @GetMapping("/list")
    public AjaxResult list() {
        BizActivity query = new BizActivity();
        // 当前查询条件为空，会返回所有活动（由前端/此处过滤）

        List<BizActivity> activities = bizActivityService.selectBizActivityList(query);

        // 只取有封面图、且状态不是“已取消(2)”或“已下架(3)”的活动，最多5条
        List<Map<String, Object>> banners = activities.stream()
                .filter(act -> act.getCoverImg() != null && !act.getCoverImg().trim().isEmpty())
                .filter(act -> act.getStatus() != null && act.getStatus() != 2 && act.getStatus() != 3)
                .limit(5)
                .map(act -> {
                    Map<String, Object> banner = new java.util.HashMap<>();
                    banner.put("activityId", act.getId());
                    banner.put("title", act.getTitle());
                    banner.put("coverImg", act.getCoverImg());
                    return banner;
                })
                .collect(Collectors.toList());

        return AjaxResult.success(banners);
    }
}

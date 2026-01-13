package com.ruoyi.student.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.student.service.IBizSkillOrderService;
import com.ruoyi.student.service.IBizSkillServiceService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student/stats")
public class StatsController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IBizSkillServiceService skillService;

    @Autowired
    private IBizSkillOrderService orderService;

    @GetMapping("/overview")
    public AjaxResult getOverview() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userService.selectUserCount());
        stats.put("totalSkills", skillService.selectSkillCount());
        stats.put("totalOrders", orderService.selectOrderCount());

        // 订单状态分布
        List<Map<String, Object>> statusDist = orderService.selectOrderStatusDistribution();
        stats.put("orderStatusDistribution", convertOrderStatusForChart(statusDist));

        // 各技能的订单数
        List<Map<String, Object>> skillDist = orderService.selectSkillOrderCountDistribution();
        stats.put("skillOrderDistribution", convertToNameValueList(skillDist));

        //dailyNewUsers
        List<Map<String, Object>> dailyNewUsers = userService.selectDailyNewUsersLast7Days();
        stats.put("dailyNewUsers", dailyNewUsers);

        return AjaxResult.success(stats);
    }

    // 通用转换方法（可复用）
    private List<Map<String, String>> convertToNameValueList(List<Map<String, Object>> list) {
        return list.stream().map(item -> {
            String name = (String) item.get("name");
            Object valueObj = item.get("value");
            String value = (valueObj instanceof Number) ? valueObj.toString() : "0";

            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("value", value);
            return map;
        }).collect(Collectors.toList());
    }

    /**
     * 将订单状态转换为适合图表显示的数据格式
     */
    private List<Map<String, String>> convertOrderStatusForChart(List<Map<String, Object>> orderStatusList) {
        return orderStatusList.stream().map(item -> {
            String name = (String) item.get("name");
            Long value = (Long) item.get("value");

            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("value", value.toString());
            return map;
        }).collect(Collectors.toList());
    }

}

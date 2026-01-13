package com.ruoyi.student.member.controller;

import com.ruoyi.common.annotation.IgnoreAuth;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.student.member.domain.UserPoint;
import com.ruoyi.student.member.domain.vo.RankVO;
import com.ruoyi.student.member.service.IPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private IPointsService pointsService;

    @IgnoreAuth
    @GetMapping("/top10")
    public AjaxResult getTop10Rank() {
        List<RankVO> top10 = pointsService.getTop10ByTotalPoints();
        return AjaxResult.success(top10);
    }
}

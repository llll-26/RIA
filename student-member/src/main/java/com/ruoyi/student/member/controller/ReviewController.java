package com.ruoyi.student.member.controller;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.member.domain.ServiceReview;
import com.ruoyi.student.member.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 服务评价控制器
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 提交服务评价
     *
     * @param review 前端传入的评价数据（含 orderId, rating, comment）
     * @return 成功/失败信息
     */
    @PostMapping("/add")
    public R<Void> addReview(@RequestBody ServiceReview review) {
        // 从 SecurityContext 或 Token 中获取当前用户 ID
        Long currentUserId = getCurrentUserId();

        if (currentUserId == null) {
            return R.fail("请先登录");
        }

        reviewService.addReview(currentUserId, review);
        return R.ok();
    }
    private Long getCurrentUserId() {
        try {
            return SecurityUtils.getLoginUser().getUserId();
        } catch (Exception e) {
            return null;
        }
    }
}

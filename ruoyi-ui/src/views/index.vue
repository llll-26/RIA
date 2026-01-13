<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>大学生技能交换平台</h2>
        <p>
          这是一个专为高校学生打造的技能共享与互助平台。在这里，你可以发布自己擅长的技能（如编程、设计、摄影、外语、乐器、考研辅导等），也可以寻找他人提供的服务进行学习或合作。通过“以技换技”或“积分兑换”的模式，促进校园内知识流动与能力成长。
        </p>
        <p>
          平台支持技能发布、需求匹配、在线沟通、评价反馈、积分管理等功能，致力于构建一个开放、诚信、高效的大学生技能生态社区。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="success">🎓 校园公益项目 · 免费使用</el-tag>
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-s-help"
            plain
            @click="goTarget('https://github.com/your-team/skill-exchange-platform')"
            >查看源码</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-message"
            plain
            @click="goTarget('/feedback')"
            >意见反馈</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>平台特性</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>核心功能</h4>
            <ul>
              <li>技能发布与展示</li>
              <li>需求发布与匹配</li>
              <li>私信沟通系统</li>
              <li>用户信用评价</li>
              <li>积分/时间银行机制</li>
              <li>校园认证体系</li>
              <li>活动与组队招募</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>技术架构</h4>
            <ul>
              <li>Vue 2 + Element UI</li>
              <li>Spring Boot</li>
              <li>JWT 权限控制</li>
              <li>MyBatis Plus</li>
              <li>Redis 缓存</li>
              <li>WebSocket 实时通信</li>
              <li>响应式设计（PC/手机）</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系我们</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-office-building"></i> 所属单位：<el-link
                href="/about"
                target="_blank"
                >XX大学创新创业中心</el-link
              >
            </p>
            <p>
              <i class="el-icon-message"></i> 反馈建议：<el-link
                href="/feedback"
                target="_blank"
                >提交问题或建议</el-link
              >
            </p>
            <p>
              <i class="el-icon-user"></i> 官方邮箱：<a href="mailto:skill@university.edu.cn">skill@university.edu.cn</a>
            </p>
            <p>
              <i class="el-icon-info"></i> 使用指南：<el-link
                href="/docs"
                target="_blank"
                >新手入门文档</el-link
              >
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v1.2.0 - 2026-01-03">
              <ol>
                <li>上线技能标签分类系统</li>
                <li>新增“技能地图”可视化展示</li>
                <li>优化移动端交互体验</li>
                <li>增加实名校园认证流程</li>
                <li>修复私信消息推送延迟问题</li>
                <li>新增用户信用分机制</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.1.0 - 2025-11-15">
              <ol>
                <li>正式开放全校公测</li>
                <li>上线积分兑换商城（虚拟礼品）</li>
                <li>支持技能预约与日程安排</li>
                <li>新增举报与审核机制</li>
                <li>优化搜索关键词匹配算法</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.0 - 2025-09-01">
              <ol>
                <li>平台正式上线</li>
                <li>支持技能发布、需求发布、消息沟通</li>
                <li>完成基础用户体系与权限管理</li>
                <li>实现响应式前端界面</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>加入我们</span>
          </div>
          <div class="body">
            <p>欢迎对平台感兴趣的同学们参与共建！</p>
            <p>✅ 前端开发（Vue）</p>
            <p>✅ 后端开发（Java/Spring Boot）</p>
            <p>✅ UI/UX 设计</p>
            <p>✅ 运营与推广</p>
            <p>📧 联系邮箱：<a href="mailto:skill@university.edu.cn">skill@university.edu.cn</a></p>
            <p style="margin-top: 15px; font-size: 12px; color: #999;">
              注：本平台为非营利性校园公益项目，所有代码开源，欢迎贡献！
            </p>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 当前平台版本
      version: "1.2.0"
    }
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank")
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
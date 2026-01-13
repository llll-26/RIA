<template>
  <div class="stats-overview">
    <!-- 原有统计卡片 -->
    <el-row :gutter="20" style="margin-bottom: 24px;">
      <el-col :span="8">
        <el-card shadow="hover" class="stat-card">
          <p>总用户数</p>
          <h3>{{ stats.totalUsers || 0 }}</h3>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="stat-card">
          <p>发布技能数</p>
          <h3>{{ stats.totalSkills || 0 }}</h3>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="stat-card">
          <p>完成订单数</p>
          <h3>{{ stats.totalOrders || 0 }}</h3>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
  <!-- 图表区域 -->
<el-row :gutter="20" style="margin-bottom: 24px;">
  <!-- 饼图：订单状态分布 -->
  <el-col :span="12">
    <el-card shadow="hover">
      <div ref="pieChart" class="chart-container"></div>
    </el-card>
  </el-col>
  <!-- 饼图：技能预约分布 -->
  <el-col :span="12">
    <el-card shadow="hover">
      <div ref="skillPieChart" class="chart-container"></div>
    </el-card>
  </el-col>
</el-row>

<!-- 柱状图：每日新增用户（单独一行） -->
<el-row :gutter="20">
  <el-col :span="24">
    <el-card shadow="hover">
      <div ref="barChart" class="chart-container"></div>
    </el-card>
  </el-col>
</el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';

// 引入封装好的 API
import { getStatsOverview } from '@/api/student/stats';

export default {
  name: 'StatsOverview',
  data() {
    return {
      stats: {},
      pieChart: null,
      skillPieChart: null,
      barChart: null
    };
  },
  async created() {
    await this.loadStats();
  },
mounted() {
  this.$nextTick(() => {
    if (Object.keys(this.stats).length > 0) {
      this.initCharts();
    }
  });
},
  beforeDestroy() {
    // 销毁图表实例，防止内存泄漏
    if (this.pieChart) this.pieChart.dispose();
    if (this.skillPieChart) this.skillPieChart.dispose();
    if (this.barChart) this.barChart.dispose();
  },
  methods: {
   renderSkillPieChart() {
  const dom = this.$refs.skillPieChart;
  if (!dom) return;

  if (this.skillPieChart) this.skillPieChart.dispose();
  this.skillPieChart = echarts.init(dom);

  // 🔧 合并相同 name，并将 value 转为数字
  const mergedMap = {};
  (this.stats.skillOrderDistribution || []).forEach(item => {
    const name = item.name || '未知技能';
    const value = parseInt(item.value, 10) || 0; // 字符串转数字

    if (mergedMap[name]) {
      mergedMap[name] += value;
    } else {
      mergedMap[name] = value;
    }
  });

  // 转为 ECharts 需要的格式
  const mergedData = Object.keys(mergedMap).map(name => ({
    name,
    value: mergedMap[name]
  }));

  console.log('合并后的技能数据:', mergedData); // 可选：调试用

  const option = {
    title: {
      text: '技能预约分布（按订单数）',
      left: 'center',
      top: 10,
      textStyle: { fontSize: 16 }
    },
    tooltip: { trigger: 'item' },
    legend: { 
      orient: 'vertical', 
      right: '5%',
      top: 'middle'
    },
    series: [{
      name: '技能占比',
      type: 'pie',
      radius: ['40%', '70%'],
      data: mergedData,
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }]
  };

  this.skillPieChart.setOption(option);
},
async loadStats() {
  try {
    const response = await getStatsOverview();
    if (response.code === 200) {
      this.stats = response.data || {};
      // 数据更新后重新渲染图表
      this.$nextTick(() => {
        this.initCharts();
      });
    } else {
      this.$message.error(response.msg || '加载失败');
    }
  } catch (error) {
    console.error('获取统计数据失败:', error);
    this.$message.error('网络错误');
  }
},

    initCharts() {
      this.renderPieChart();
      this.renderSkillPieChart();
      this.renderBarChart();
    },

    renderPieChart() {
      const dom = this.$refs.pieChart;
      if (!dom) return;

      // 销毁旧实例
      if (this.pieChart) this.pieChart.dispose();
      this.pieChart = echarts.init(dom);

      const option = {
        title: {
          text: '订单状态分布',
          left: 'center',
          top: 10,
          textStyle: { fontSize: 16 }
        },
        tooltip: { trigger: 'item' },
        legend: { 
          orient: 'vertical', 
          left: 'left',
          top: 'middle'
        },
        series: [{
          name: '订单占比',
          type: 'pie',
          radius: '60%',
          data: this.stats.orderStatusDistribution || [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      this.pieChart.setOption(option);
    },

    renderBarChart() {
      const dom = this.$refs.barChart;
      if (!dom) return;

      if (this.barChart) this.barChart.dispose();
      this.barChart = echarts.init(dom);

      const dailyData = this.stats.dailyNewUsers || [];
      const days = dailyData.map(item => item.day);
      const counts = dailyData.map(item => item.count);

      const option = {
        title: {
          text: '近7天新增用户',
          left: 'center',
          top: 10,
          textStyle: { fontSize: 16 }
        },
        tooltip: { trigger: 'axis' },
        xAxis: { type: 'category', data: days },
        yAxis: { type: 'value' },
        series: [{
          data: counts,
          type: 'bar',
          color: '#409EFF',
          label: {
            show: true,
            position: 'top'
          }
        }]
      };

      this.barChart.setOption(option);
    }
  }
};
</script>

<style scoped>
.stats-overview {
  padding: 20px;
}

.stat-card {
  text-align: center;
}
.stat-card p {
  margin: 0 0 8px;
  font-size: 14px;
  color: #999;
}
.stat-card h3 {
  margin: 0;
  font-size: 28px;
  font-weight: bold;
  color: #409eff;
}

.chart-container {
  width: 100%;
  height: 300px;
}
</style>
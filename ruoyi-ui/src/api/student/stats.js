import request from '@/utils/request';

// 获取数据概览统计信息
export function getStatsOverview() {
  return request({
    url: '/student/stats/overview',
    method: 'get'
  });
}
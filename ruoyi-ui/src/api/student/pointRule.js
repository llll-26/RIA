import request from '@/utils/request'

// 获取所有积分规则
export function getAllPointRules() {
  return request({
    url: '/student/point/rule/all',
    method: 'get'
  })
}

// 保存积分规则（批量 upsert）
export function savePointRules(rules) {
  return request({
    url: '/student/point/rule/save',
    method: 'post',
    data: rules
  })
}
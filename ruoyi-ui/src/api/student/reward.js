import request from '@/utils/request'

// 查询用户兑换记录列表
export function listReward(query) {
  return request({
    url: '/student/reward/list',
    method: 'get',
    params: query
  })
}

// 查询用户兑换记录详细
export function getReward(id) {
  return request({
    url: '/student/reward/' + id,
    method: 'get'
  })
}

// 新增用户兑换记录
export function addReward(data) {
  return request({
    url: '/student/reward',
    method: 'post',
    data: data
  })
}

// 修改用户兑换记录
export function updateReward(data) {
  return request({
    url: '/student/reward',
    method: 'put',
    data: data
  })
}

// 删除用户兑换记录
export function delReward(id) {
  return request({
    url: '/student/reward/' + id,
    method: 'delete'
  })
}

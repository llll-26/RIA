import request from '@/utils/request'

// 查询用户反馈列表
export function listFeedback(query) {
  return request({
    url: '/student/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询用户反馈详细
export function getFeedback(id) {
  return request({
    url: '/student/feedback/' + id,
    method: 'get'
  })
}

// 新增用户反馈
export function addFeedback(data) {
  return request({
    url: '/student/feedback',
    method: 'post',
    data: data
  })
}

// 修改用户反馈
export function updateFeedback(data) {
  return request({
    url: '/student/feedback',
    method: 'put',
    data: data
  })
}

// 删除用户反馈
export function delFeedback(id) {
  return request({
    url: '/student/feedback/' + id,
    method: 'delete'
  })
}

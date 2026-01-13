import request from '@/utils/request'

// 查询用户通知列表
export function listNotification(query) {
  return request({
    url: '/student/notification/list',
    method: 'get',
    params: query
  })
}

// 查询用户通知详细
export function getNotification(id) {
  return request({
    url: '/student/notification/' + id,
    method: 'get'
  })
}

// 新增用户通知
export function addNotification(data) {
  return request({
    url: '/student/notification',
    method: 'post',
    data: data
  })
}

// 修改用户通知
export function updateNotification(data) {
  return request({
    url: '/student/notification',
    method: 'put',
    data: data
  })
}

// 删除用户通知
export function delNotification(id) {
  return request({
    url: '/student/notification/' + id,
    method: 'delete'
  })
}

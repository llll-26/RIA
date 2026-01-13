import request from '@/utils/request'

// 查询志愿活动/讲座列表
export function listActivity(query) {
  return request({
    url: '/student/activity/list',
    method: 'get',
    params: query
  })
}

// 查询志愿活动/讲座详细
export function getActivity(id) {
  return request({
    url: '/student/activity/' + id,
    method: 'get'
  })
}

// 新增志愿活动/讲座
export function addActivity(data) {
  return request({
    url: '/student/activity',
    method: 'post',
    data: data
  })
}

// 修改志愿活动/讲座
export function updateActivity(data) {
  return request({
    url: '/student/activity',
    method: 'put',
    data: data
  })
}

// 删除志愿活动/讲座
export function delActivity(id) {
  return request({
    url: '/student/activity/' + id,
    method: 'delete'
  })
}
// 下架活动（批量）

export function hideActivity(ids) {
  return request({
    url: '/student/activity/hide/' + (Array.isArray(ids) ? ids.join(',') : ids),
    method: 'put'
  })
}

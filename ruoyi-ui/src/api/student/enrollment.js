import request from '@/utils/request'

// 查询用户活动报名记录列表
export function listEnrollment(query) {
  return request({
    url: '/student/enrollment/list',
    method: 'get',
    params: query
  })
}

// 查询用户活动报名记录详细
export function getEnrollment(id) {
  return request({
    url: '/student/enrollment/' + id,
    method: 'get'
  })
}

// 新增用户活动报名记录
export function addEnrollment(data) {
  return request({
    url: '/student/enrollment',
    method: 'post',
    data: data
  })
}

// 修改用户活动报名记录
export function updateEnrollment(data) {
  return request({
    url: '/student/enrollment',
    method: 'put',
    data: data
  })
}

// 删除用户活动报名记录
export function delEnrollment(id) {
  return request({
    url: '/student/enrollment/' + id,
    method: 'delete'
  })
}

// 管理员完成活动（发放积分）
export function adminCompleteEnrollment(enrollmentId) {
  return request({
    url: '/student/activity/admin/complete/' + enrollmentId,
    method: 'post'
  })
}
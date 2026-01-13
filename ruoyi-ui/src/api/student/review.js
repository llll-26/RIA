import request from '@/utils/request'

// 查询服务评价列表
export function listReview(query) {
  return request({
    url: '/student/review/list',
    method: 'get',
    params: query
  })
}

// 查询服务评价详细
export function getReview(id) {
  return request({
    url: '/student/review/' + id,
    method: 'get'
  })
}

// 新增服务评价
export function addReview(data) {
  return request({
    url: '/student/review',
    method: 'post',
    data: data
  })
}

// 修改服务评价
export function updateReview(data) {
  return request({
    url: '/student/review',
    method: 'put',
    data: data
  })
}

// 删除服务评价
export function delReview(id) {
  return request({
    url: '/student/review/' + id,
    method: 'delete'
  })
}

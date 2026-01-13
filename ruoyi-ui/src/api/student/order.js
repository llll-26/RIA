import request from '@/utils/request'

// 查询技能订单列表
export function listOrder(query) {
  return request({
    url: '/student/order/list',
    method: 'get',
    params: query
  })
}

// 查询技能订单详细
export function getOrder(id) {
  return request({
    url: '/student/order/' + id,
    method: 'get'
  })
}

// 新增技能订单
export function addOrder(data) {
  return request({
    url: '/student/order',
    method: 'post',
    data: data
  })
}

// 修改技能订单
export function updateOrder(data) {
  return request({
    url: '/student/order',
    method: 'put',
    data: data
  })
}

// 删除技能订单
export function delOrder(id) {
  return request({
    url: '/student/order/' + id,
    method: 'delete'
  })
}

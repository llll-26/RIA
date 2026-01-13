import request from '@/utils/request'

// 查询兑换奖品项列表
export function listItem(query) {
  return request({
    url: '/student/item/list',
    method: 'get',
    params: query
  })
}

// 查询兑换奖品项详细
export function getItem(id) {
  return request({
    url: '/student/item/' + id,
    method: 'get'
  })
}

// 新增兑换奖品项
export function addItem(data) {
  return request({
    url: '/student/item',
    method: 'post',
    data: data
  })
}

// 修改兑换奖品项
export function updateItem(data) {
  return request({
    url: '/student/item',
    method: 'put',
    data: data
  })
}

// 删除兑换奖品项
export function delItem(id) {
  return request({
    url: '/student/item/' + id,
    method: 'delete'
  })
}
export function hideItem(ids) {
  return request({
    url: '/student/item/hide',
    method: 'put',
    data: { ids } // 或 params，根据后端设计
  })
}
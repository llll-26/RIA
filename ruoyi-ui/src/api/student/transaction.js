import request from '@/utils/request'

// 查询积分流水列表
export function listTransaction(query) {
  return request({
    url: '/student/transaction/list',
    method: 'get',
    params: query
  })
}

// 查询积分流水详细
export function getTransaction(id) {
  return request({
    url: '/student/transaction/' + id,
    method: 'get'
  })
}

// 新增积分流水
export function addTransaction(data) {
  return request({
    url: '/student/transaction',
    method: 'post',
    data: data
  })
}

// 修改积分流水
export function updateTransaction(data) {
  return request({
    url: '/student/transaction',
    method: 'put',
    data: data
  })
}

// 删除积分流水
export function delTransaction(id) {
  return request({
    url: '/student/transaction/' + id,
    method: 'delete'
  })
}

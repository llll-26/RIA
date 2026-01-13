import request from '@/utils/request'

// 查询技能服务列表
export function listService(query) {
  return request({
    url: '/student/service/list',
    method: 'get',
    params: query
  })
}

// 查询技能服务详细
export function getService(id) {
  return request({
    url: '/student/service/' + id,
    method: 'get'
  })
}

// 新增技能服务
export function addService(data) {
  return request({
    url: '/student/service',
    method: 'post',
    data: data
  })
}

// 删除技能服务
export function delService(id) {
  return request({
    url: '/student/service/' + id,
    method: 'delete'
  })
}

// 审核通过
export function auditPass(id) {
  return request({
    url: '/student/service/audit/pass/' + id,
    method: 'post'
  })
}

// 审核拒绝
export function auditReject(id) {
  return request({
    url: '/student/service/audit/reject/' + id,
    method: 'post'
  })
}
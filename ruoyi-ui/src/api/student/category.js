import request from '@/utils/request'

// 查询技能标准分类列表
export function listCategory(query) {
  return request({
    url: '/student/category/list',
    method: 'get',
    params: query
  })
}

// 查询技能标准分类详细
export function getCategory(id) {
  return request({
    url: '/student/category/' + id,
    method: 'get'
  })
}

// 新增技能标准分类
export function addCategory(data) {
  return request({
    url: '/student/category',
    method: 'post',
    data: data
  })
}

// 修改技能标准分类
export function updateCategory(data) {
  return request({
    url: '/student/category',
    method: 'put',
    data: data
  })
}

// 删除技能标准分类
export function delCategory(id) {
  return request({
    url: '/student/category/' + id,
    method: 'delete'
  })
}

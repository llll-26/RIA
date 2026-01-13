// src/api/system/whitelist.js

import request from '@/utils/request'

// 导入白名单数据
export function importWhitelist(data) {
  return request({
    url: '/system/whitelist/importData',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

//下载导入模板
export function downloadTemplate() {
  return request({
    url: '/common/download/template/student_whitelist_template.xlsx',
    method: 'get',
    responseType: 'blob'
  })
}
// 获取白名单列表
export function getWhitelistList() {
  return request({
    url: '/system/whitelist/list',
    method: 'get'
  })
}
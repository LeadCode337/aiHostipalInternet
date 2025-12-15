import request from '@/utils/request'

// 查询职称管理列表
export function listTitle(query) {
  return request({
    url: '/system/title/list',
    method: 'get',
    params: query
  })
}

// 查询职称管理详细
export function getTitle(id) {
  return request({
    url: '/system/title/' + id,
    method: 'get'
  })
}

// 新增职称管理
export function addTitle(data) {
  return request({
    url: '/system/title',
    method: 'post',
    data: data
  })
}

// 修改职称管理
export function updateTitle(data) {
  return request({
    url: '/system/title',
    method: 'put',
    data: data
  })
}

// 删除职称管理
export function delTitle(id) {
  return request({
    url: '/system/title/' + id,
    method: 'delete'
  })
}

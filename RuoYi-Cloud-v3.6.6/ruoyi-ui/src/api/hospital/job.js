import request from '@/utils/request'

// 查询医生职称列表
export function listJob(query) {
  return request({
    url: '/hospital/job/list',
    method: 'get',
    params: query
  })
}

// 查询医生职称详细
export function getJob(id) {
  return request({
    url: '/hospital/job/' + id,
    method: 'get'
  })
}

// 新增医生职称
export function addJob(data) {
  return request({
    url: '/hospital/job',
    method: 'post',
    data: data
  })
}

// 修改医生职称
export function updateJob(data) {
  return request({
    url: '/hospital/job',
    method: 'put',
    data: data
  })
}

// 删除医生职称
export function delJob(id) {
  return request({
    url: '/hospital/job/' + id,
    method: 'delete'
  })
}

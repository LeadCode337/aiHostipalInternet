import request from '@/utils/request'

// 查询医生档案列表
export function listDoctor(query) {
  return request({
    url: '/hospital/doctor/list',
    method: 'get',
    params: query
  })
}

// 查询医生档案详细
export function getDoctor(id) {
  return request({
    url: '/hospital/doctor/' + id,
    method: 'get'
  })
}

// 新增医生档案
export function addDoctor(data) {
  return request({
    url: '/hospital/doctor',
    method: 'post',
    data: data
  })
}

// 修改医生档案
export function updateDoctor(data) {
  return request({
    url: '/hospital/doctor',
    method: 'put',
    data: data
  })
}

// 删除医生档案
export function delDoctor(id) {
  return request({
    url: '/hospital/doctor/' + id,
    method: 'delete'
  })
}

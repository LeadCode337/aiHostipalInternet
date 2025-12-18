import request from '@/utils/request'

// 查询药品库存列表
export function listMedicine(query) {
  return request({
    url: '/hospital/medicine/list',
    method: 'get',
    params: query
  })
}

// 查询药品库存详细
export function getMedicine(id) {
  return request({
    url: '/hospital/medicine/' + id,
    method: 'get'
  })
}

// 新增药品库存
export function addMedicine(data) {
  return request({
    url: '/hospital/medicine',
    method: 'post',
    data: data
  })
}

// 修改药品库存
export function updateMedicine(data) {
  return request({
    url: '/hospital/medicine',
    method: 'put',
    data: data
  })
}

// 删除药品库存
export function delMedicine(id) {
  return request({
    url: '/hospital/medicine/' + id,
    method: 'delete'
  })
}

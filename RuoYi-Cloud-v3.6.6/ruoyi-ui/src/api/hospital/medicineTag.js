import request from '@/utils/request'

// 查询药品标签列表
export function listMedicineTag(query) {
  return request({
    url: '/hospital/medicineTag/list',
    method: 'get',
    params: query
  })
}

// 查询药品标签详细
export function getMedicineTag(id) {
  return request({
    url: '/hospital/medicineTag/' + id,
    method: 'get'
  })
}

// 新增药品标签
export function addMedicineTag(data) {
  return request({
    url: '/hospital/medicineTag',
    method: 'post',
    data: data
  })
}

// 修改药品标签
export function updateMedicineTag(data) {
  return request({
    url: '/hospital/medicineTag',
    method: 'put',
    data: data
  })
}

// 删除药品标签
export function delMedicineTag(id) {
  return request({
    url: '/hospital/medicineTag/' + id,
    method: 'delete'
  })
}

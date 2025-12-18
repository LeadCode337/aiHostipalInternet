import request from '@/utils/request'

// 查询宣传中心列表
export function listArticle(query) {
  return request({
    url: '/hospital/article/list',
    method: 'get',
    params: query
  })
}

// 查询宣传中心详细
export function getArticle(id) {
  return request({
    url: '/hospital/article/' + id,
    method: 'get'
  })
}

// 新增宣传中心
export function addArticle(data) {
  return request({
    url: '/hospital/article',
    method: 'post',
    data: data
  })
}

// 修改宣传中心
export function updateArticle(data) {
  return request({
    url: '/hospital/article',
    method: 'put',
    data: data
  })
}

// 删除宣传中心
export function delArticle(id) {
  return request({
    url: '/hospital/article/' + id,
    method: 'delete'
  })
}

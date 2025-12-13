import request from '@/utils/request'

// 查询健康宣教文章列表
export function listArticle(query) {
  return request({
    url: '/article/article/list',
    method: 'get',
    params: query
  })
}

// 查询健康宣教文章详细
export function getArticle(id) {
  return request({
    url: '/article/article/' + id,
    method: 'get'
  })
}

// 新增健康宣教文章
export function addArticle(data) {
  return request({
    url: '/article/article',
    method: 'post',
    data: data
  })
}

// 修改健康宣教文章
export function updateArticle(data) {
  return request({
    url: '/article/article',
    method: 'put',
    data: data
  })
}

// 删除健康宣教文章
export function delArticle(id) {
  return request({
    url: '/article/article/' + id,
    method: 'delete'
  })
}

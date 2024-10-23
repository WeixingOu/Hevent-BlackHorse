import type { ArticleData, CategoryData, PageParam } from '@/types';
import request from '@/utils/request';

export const getCategoryListService = () => {
    return request({
        url: '/categories',
        method: 'get'
    });
}

export const addCategoryService = (categoryData: CategoryData) => {
    return request({
        url: '/categories',
        method: 'post',
        data: categoryData
    })
}

export const updateCategoryService = (categoryData: CategoryData) => {
    return request({
        url: `/categories/${categoryData.id}`,
        method: 'put',
        data: categoryData
    })
}

export const deleteCategoryService = (id: number) => {
    return request({
        url: `/categories/${id}`,
        method: 'delete'
    })
}

export const getArticleListService = (params: PageParam) => {
    return request({
        url: '/articles',
        params
    })
}

export const addArticleService = (articleData: ArticleData) => {
    return request({
        url: '/articles',
        method:'post',
        data: articleData
    })
}

export const updateArticleService = (articleData: ArticleData) => {
    return request({
        url: `/articles/${articleData.id}`,
        method: 'put',
        data: articleData
    })
}

export const deleteArticleService = (id: number) => {
    return request({
        url: `/articles/${id}`,
        method: 'delete'
    });
}
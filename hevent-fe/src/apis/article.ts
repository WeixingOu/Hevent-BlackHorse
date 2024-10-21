import type { CategoryData } from '@/types';
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
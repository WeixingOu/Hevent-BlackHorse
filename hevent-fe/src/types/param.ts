export interface PageParam {
    page: number,
    size: number,
    categoryId?: number,
    state?: 'DRAFT' | 'PUBLISHED'
} 
export interface Category {
    id: number;
    categoryName: string;
    categoryAlias: string;
    createTime: string;
    updateTime: string;
}

export interface CategoryData {
    id?: number | null;
    categoryName: string;
    categoryAlias: string;
}
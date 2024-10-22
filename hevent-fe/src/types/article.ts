export interface Article {
    id: number;
    title: string;
    content: string;
    coverImg: string;
    state: 'DRAFT' | 'PUBLISHED';
    categoryId: number;
    createTime: string;
    updateTime: string;
    categoryName?: string;
}

export interface ArticleData {
    id?: string; 
    title: string;
    content: string;
    coverImg: string;
    state: 'DRAFT' | 'PUBLISHED';
    categoryId: number | null;
}

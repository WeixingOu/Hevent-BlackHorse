export interface RegisterData {
    username: string;
    password: string;
    rePassword: string;
}

export interface LoginData {
    username: string;
    password: string;
}

export interface Profile {
    id: number | null | undefined;
    username: string;
    nickname: string;
    email: string;
    userPic?: string;
    createTime?: string;
    updateTime?: string;
}
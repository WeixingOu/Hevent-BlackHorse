import type { PasswordData, Profile } from '@/types';
import request from '@/utils/request';

export const getProfileService = () => {
    return request({
        url: '/users/auth',
        method: 'get'
    })
}

export const updateProfileService = (updateData: Profile) => {
    return request({
        url: '/users/me',
        method: 'put',
        data: updateData
    })
}

export const updateProfileAvatarService = (imgUrl: string) => {
    return request({
        url: 'users/me',
        method: 'patch',
        params: { userPicUrl: imgUrl }
    })
}

export const updatedPwdUpdateService = (passwordData: PasswordData) => {
    return request({
        url: '/users/auth',
        method: 'patch',
        data: passwordData
    })
}
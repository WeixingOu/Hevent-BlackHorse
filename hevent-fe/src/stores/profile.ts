import type { Profile } from '@/types';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useProfileStore = defineStore('profile',
    () => {
        const profile = ref<Profile | null>(null);

        const setProfile = (newProfile: Profile) => {
            profile.value = newProfile;
        }

        const removeProfile = () => {
            profile.value = null;
        }

        return {
            profile, setProfile, removeProfile
        }
    },
    {
        persist: true
    }
);
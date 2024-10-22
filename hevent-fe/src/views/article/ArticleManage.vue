<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Article Management</span>
                <div class="extra">
                    <el-button type="primary" @click="showAddDialog('Add Article')">Add Article</el-button>
                </div>
            </div>
        </template>

        <el-form inline>
            <el-form-item label="Article Category:">
                <el-select placeholder="Please select" v-model="categoryId" style="width:150px;">
                    <el-option v-for="c in categories" :key="c.id" :label="c.categoryName" :value="c.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Publish Status:">
                <el-select placeholder="Please select" v-model="state" style="width:150px;">
                    <el-option label="Published" value="PUBLISHED"></el-option>
                    <el-option label="Draft" value="DRAFT"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="articleList">Search</el-button>
                <el-button @click="categoryId='';state=''; articleList()">Reset</el-button>
            </el-form-item>
        </el-form>

        <el-table :data="articles" style="width: 100%">
            <el-table-column label="Title" width="400" prop="title"></el-table-column>
            <el-table-column label="Category" prop="categoryName"></el-table-column>
            <el-table-column label="Publish Time" prop="createTime">
                <template #default="scope">
                    {{ formatDate(scope.row.createTime) }}
                </template>
            </el-table-column>
            <el-table-column label="Status" prop="state"></el-table-column>
            <el-table-column label="Actions" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" ></el-button>
                    <el-button :icon="Delete" circle plain type="danger" ></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="No data available" />
            </template>
        </el-table>

        <el-pagination v-model:current-page="page" v-model:page-size="size" :page-sizes="[3, 5, 10, 15]" 
            layout="jumper, total, sizes, prev, pager, next" background :total="total" 
            @size-change="onSizeChange" @current-change="onCurrentChange"
            style="margin-top: 20px; justify-content: flex-end" />
    </el-card>


    <el-drawer direction="rtl" size="50%" v-model="visibleDrawer" :title="drawerTitle">
        <el-form :model="articleModel" label-width="100px">
            <el-form-item label="Title">
                <el-input v-model="articleModel.title" placeholder="Please enter a title"></el-input>
            </el-form-item>
            <el-form-item label="Category">
                <el-select placeholder="Please select" v-model="articleModel.categoryId">
                    <el-option v-for="c in categories" :key="c.id" :label="c.categoryName" :value="c.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Cover">
                <el-upload class="avatar-uploader" 
                    :auto-upload="true" :show-file-list="false" 
                    action="/api/v1/upload" name="file"
                    :headers="uploadHeaders"
                    :data="{ publicId: articleModel.id }"
                    @success="uploadSuccess"
                >
                    <el-image v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="Content">
                <div class="editor">
                    <quill-editor theme="snow" v-model:content="articleModel.content" contentType="html">
                    </quill-editor>

                </div>
            </el-form-item>
            <el-form-item>
                <el-button type="primary">Publish
                </el-button>
                <el-button type="info">Draft
                </el-button>
            </el-form-item>
        </el-form>
    </el-drawer>
</template>

<script lang="ts" setup>
    import {Delete, Edit, Plus} from '@element-plus/icons-vue';
    import { QuillEditor } from '@vueup/vue-quill';
    import '@vueup/vue-quill/dist/vue-quill.snow.css';
    import { reactive, ref } from 'vue';
    import type { Category, PageParam, Article } from '@/types';
    import type { ArticleData } from '@/types/article';
    import { getArticleListService, getCategoryListService } from '@/apis/article';
    import { format } from 'date-fns';
    import { useTokenStore } from "@/stores/token";
    import type { AxiosResponse } from 'axios';
    import { v4 as uuidv4 } from 'uuid';

    const tokenStore = useTokenStore();

    const uploadHeaders = {
        Authorization: `Bearer ${tokenStore.token}`
    };

    const uploadSuccess = (res: AxiosResponse) => {
        articleModel.coverImg = res.data.coverImgUrl;
    }

    const formatDate = (isoDate: string) => {
        return format(new Date(isoDate), 'yyyy-dd-MM HH:mm:ss');
    };

    const categories = ref<Category[]>([]);

    const categoryList = async () => {
        const res = await getCategoryListService();
        categories.value = res.data;
    }
    categoryList();

    const categoryId = ref();
    const state = ref();

    const page = ref(1);
    const total = ref(20);
    const size = ref(3);

    const onSizeChange = ( num: number ) => {
        size.value = num;
        articleList();
    }

    const onCurrentChange = ( num: number ) => {
        page.value = num;
        articleList();
    }
    const articles = ref<Article[]>([]);

    const articleList = async () => {
        const params = reactive<PageParam>({
            page: page.value - 1,
            size: size.value,
            categoryId: categoryId.value ? categoryId.value : null,
            state: state.value ? state.value : null
        })


        const res = await getArticleListService(params);
        articles.value = res.data.content;
        total.value = res.data.total;

        for (let i = 0; i < articles.value.length; i++) {
            const article = articles.value[i];

            for (let j = 0; j < categories.value.length; j++) {
                if (article.categoryId === categories.value[j].id) {
                    article.categoryName = categories.value[j].categoryName
                }
            }
        }
    }
    articleList();

    const visibleDrawer = ref(false);
    const drawerTitle = ref('')

    const articleModel = reactive<ArticleData>({
        id: uuidv4() + `temp_${Date.now()}`,
        title: '',
        content: '',
        coverImg: '',
        state: 'DRAFT',
        categoryId: null,
    })

    const clearData = () => {
        articleModel.title = '';
        articleModel.content = '';
        articleModel.coverImg = '';
        articleModel.state = 'DRAFT'; 
        articleModel.categoryId = null;
    }

    const showAddDialog = ( title: string ) => {
        clearData();
        drawerTitle.value = title;
        visibleDrawer.value = true;
    }
</script>

<style lang="scss" scoped>
    .page-container {
        min-height: 100%;
        box-sizing: border-box;

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
    }

    .avatar-uploader {
        :deep {
            .avatar {
                width: 178px;
                height: 178px;
                display: block;
            }

            .el-upload {
                border: 1px dashed var(--el-border-color);
                border-radius: 6px;
                cursor: pointer;
                position: relative;
                overflow: hidden;
                transition: var(--el-transition-duration-fast);
            }

            .el-upload:hover {
                border-color: var(--el-color-primary);
            }

            .el-icon.avatar-uploader-icon {
                font-size: 28px;
                color: #8c939d;
                width: 178px;
                height: 178px;
                text-align: center;
            }
        }
    }

    .editor {
        width: 100%;

        :deep(.ql-editor) {
            min-height: 200px;
        }
    }
</style>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Categories</span>

                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = 'Add Category'; clearData();">Add Category</el-button>
                </div>
            </div>
        </template>

        <el-table :data="categories" style="width: 100%">
            <el-table-column label="Index" width="100" type="index"></el-table-column>

            <el-table-column label="Category Name" prop="categoryName"></el-table-column>

            <el-table-column label="Category Alias" prop="categoryAlias">
                <template #default="scope">
                    <el-tag type="info" style="font-style: italic; color: black;">{{ scope.row.categoryAlias }}</el-tag>
                </template>
            </el-table-column>

            <el-table-column label="Actions" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger"></el-button>
                </template>
            </el-table-column>

            <template #empty>
                <el-empty description="No Data"/>
            </template>
        </el-table>

        <el-dialog width="30%" v-model="dialogVisible" :title="title">
            <el-form :model="categoryModel" :rules="rules" ref="form" label-width="100px" style="padding-right: 30px">
                <el-form-item label="Name" prop="categoryName" style="margin-bottom: 32px;">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>

                <el-form-item label="Alias" prop="categoryAlias" style="margin-bottom: 30px;">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="title==='Add Category'?addCategory():updateCategory()">Confirm</el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

<script setup lang="ts">
    import {Delete, Edit} from '@element-plus/icons-vue';
    import { reactive, ref } from 'vue';
    import type { Category, CategoryData } from '@/types';
    import { addCategoryService, getCategoryListService, updateCategoryService } from '../../apis/article';
    import { type FormRules, type FormInstance, ElMessage } from 'element-plus';
    import {type InternalRuleItem } from "async-validator";

    const title = ref('');

    const showDialog = ( row: Category ) => {
        dialogVisible.value = true;
        title.value = 'Edit Category';

        categoryModel.categoryName = row.categoryName;
        categoryModel.categoryAlias = row.categoryAlias;
        categoryModel.id = row.id;
    }


    const categories = ref<Category[]>([]);

    const categoryList = async () => {
        const res = await getCategoryListService();
        categories.value = res.data;
    };

    categoryList();

    const dialogVisible = ref(false);

    const categoryModel = reactive<CategoryData>({
        categoryName: '',
        categoryAlias: ''
    });

    const clearData = () => {
        categoryModel.categoryName = '';
        categoryModel.categoryAlias = '';
    }

    const notBlank =  (rule: InternalRuleItem, value: string, callback: (error?: Error | string) => void) => {
            if (value.trim() === '') {
                callback(new Error('The field must not be blank'));
            } else {
                callback();
            }
        };

    const rules: FormRules = {
        categoryName: [
            {
                required: true,
                message: 'Please enter category name',
                trigger: 'blur'
            },
            {
                min: 3, max: 32, message: 'Category name must be between 3 and 32 characters',
                trigger: 'blur'
            },
            {
                validator: notBlank, trigger: 'blur'
            }
        ],

        categoryAlias: [
            {
                required: true,
                message: 'Please enter category alias',
                trigger: 'blur'
            },
            {
                min: 3, max: 32, message: 'Category alias must be between 3 and 32 characters',
                trigger: 'blur'
            },
            {
                validator: notBlank, trigger: 'blur'
            }
        ]
    }
    
    const form = ref<FormInstance | null>();

    const addCategory = async () => {
        if (!form.value) return;

        form.value.validate(async (valid: boolean) =>{
            if (valid) {
                await addCategoryService(categoryModel);

                ElMessage.success('Category created successfully');

                await categoryList();
                dialogVisible.value = false;
                clearData();
            }
        })
    }

    const updateCategory = async () => {
        if (!form.value) return;

        form.value.validate(async (valid: boolean) =>{
            if (valid) {
                await updateCategoryService(categoryModel);

                ElMessage.success('Category updated successfully');

                await categoryList();
                dialogVisible.value = false;
                clearData();
            }
        })
    }
    
</script>

<style scoped lang="scss">
    .page-container {
        min-height: 100%;
        box-sizing: border-box;

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
    }
</style>
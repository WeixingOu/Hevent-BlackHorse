<script setup lang="ts">
import {Delete, Edit} from '@element-plus/icons-vue'


</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Categories</span>

                <div class="extra">
                    <el-button type="primary">Add Category</el-button>
                </div>
            </div>
        </template>

        <el-table style="width: 100%">
            <el-table-column label="Index" width="100" type="index"></el-table-column>

            <el-table-column label="Category Name" prop="categoryName"></el-table-column>

            <el-table-column label="Category Alias" prop="categoryAlias"></el-table-column>

            <el-table-column label="Actions" width="100">
                <template #default="{  }">
                    <el-button :icon="Edit" circle plain type="primary"></el-button>
                    <el-button :icon="Delete" circle plain type="danger"></el-button>
                </template>
            </el-table-column>

            <template #empty>
                <el-empty description="No Data"/>
            </template>
        </el-table>

        <el-dialog width="30%">
            <el-form ref="form" label-width="100px" style="padding-right: 30px">
                <el-form-item label="Category Name" prop="categoryName">
                    <el-input minlength="1" maxlength="10"></el-input>
                </el-form-item>

                <el-form-item label="Category Alias" prop="categoryAlias">
                    <el-input minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button>Cancel</el-button>
                    <el-button type="primary"> Confirm  </el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

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
</style>
<script setup lang="ts">
import { RouterView, useRoute } from 'vue-router'

const route = useRoute()

const menuItems = [
  { path: '/', title: '首页' },
  { path: '/projects', title: '项目管理' },
  { path: '/tasks', title: '任务管理' },
  { path: '/requirements', title: '需求管理' },
  { path: '/dashboard', title: '数据看板' },
  { path: '/about', title: '关于系统' },
]
</script>

<template>
  <el-container class="admin-layout">
    <el-aside class="admin-aside" width="220px">
      <div class="admin-brand">
        <span class="admin-brand__title">工程任务系统</span>
      </div>

      <el-menu class="admin-menu" :default-active="route.path" router>
        <el-menu-item v-for="item in menuItems" :key="item.path" :index="item.path">
          <span>{{ item.title }}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="admin-header">
        <div>
          <h1>后台管理系统</h1>
          <p>Engineering Task System</p>
        </div>
      </el-header>

      <el-main class="admin-main">
        <RouterView />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.admin-layout {
  min-height: 100vh;
  background: #f5f7fb;
}

.admin-aside {
  min-height: 100vh;
  background: #ffffff;
  border-right: 1px solid #e5e7eb;
}

.admin-brand {
  display: flex;
  align-items: center;
  height: 64px;
  padding: 0 20px;
  border-bottom: 1px solid #e5e7eb;
}

.admin-brand__title {
  color: #1f2937;
  font-size: 18px;
  font-weight: 600;
}

.admin-menu {
  border-right: 0;
}

.admin-header {
  display: flex;
  align-items: center;
  height: 64px;
  padding: 0 24px;
  background: #ffffff;
  border-bottom: 1px solid #e5e7eb;
}

.admin-header h1 {
  margin: 0;
  color: #111827;
  font-size: 20px;
  font-weight: 600;
}

.admin-header p {
  margin: 2px 0 0;
  color: #6b7280;
  font-size: 13px;
}

.admin-main {
  min-height: calc(100vh - 64px);
  padding: 24px;
  overflow: auto;
  background: #f5f7fb;
}
</style>

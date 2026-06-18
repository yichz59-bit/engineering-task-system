<script setup lang="ts">
import { computed, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { mockTasks, taskPriorityOptions, taskStatusOptions } from '@/mock/tasks'
import type { DialogMode, TaskItem, TaskPriority, TaskStatus } from '@/types/task'

const tasks = ref<TaskItem[]>(mockTasks.map((task) => ({ ...task })))

const queryForm = reactive({
  name: '',
  status: '',
  owner: '',
})

// Keep input edits separate from applied filters so typing does not immediately change the table.
const appliedQuery = reactive({
  name: '',
  status: '',
  owner: '',
})

const dialogVisible = ref(false)
const dialogMode = ref<DialogMode>('create')

const taskForm = reactive<TaskItem>({
  id: '',
  name: '',
  project: '',
  owner: '',
  status: '待开始',
  priority: '中',
  deadline: '',
})

const filteredTasks = computed(() => {
  return tasks.value.filter((task) => {
    const nameMatched = !appliedQuery.name || task.name.includes(appliedQuery.name)
    const statusMatched = !appliedQuery.status || task.status === appliedQuery.status
    const ownerMatched = !appliedQuery.owner || task.owner.includes(appliedQuery.owner)

    return nameMatched && statusMatched && ownerMatched
  })
})

const dialogTitle = computed(() => {
  const titleMap: Record<DialogMode, string> = {
    create: '新增任务',
    edit: '编辑任务',
    view: '查看任务',
  }

  return titleMap[dialogMode.value]
})

const isViewMode = computed(() => dialogMode.value === 'view')

const getStatusTagType = (status: TaskStatus) => {
  const typeMap: Record<TaskStatus, 'info' | 'primary' | 'success' | 'danger'> = {
    待开始: 'info',
    进行中: 'primary',
    已完成: 'success',
    已延期: 'danger',
  }

  return typeMap[status]
}

const getPriorityTagType = (priority: TaskPriority) => {
  const typeMap: Record<TaskPriority, 'danger' | 'warning' | 'info'> = {
    高: 'danger',
    中: 'warning',
    低: 'info',
  }

  return typeMap[priority]
}

const resetTaskForm = () => {
  Object.assign(taskForm, {
    id: '',
    name: '',
    project: '',
    owner: '',
    status: '待开始',
    priority: '中',
    deadline: '',
  })
}

const createTaskId = () => {
  // New IDs are derived from the current in-memory list, including tasks added during this session.
  const maxNumber = tasks.value.reduce((max, task) => {
    const taskNumber = Number(task.id.replace('TASK-', ''))

    return Number.isNaN(taskNumber) ? max : Math.max(max, taskNumber)
  }, 0)

  return `TASK-${String(maxNumber + 1).padStart(3, '0')}`
}

const applyQuery = () => {
  Object.assign(appliedQuery, queryForm)
}

const resetQuery = () => {
  Object.assign(queryForm, {
    name: '',
    status: '',
    owner: '',
  })
  Object.assign(appliedQuery, queryForm)
}

const openCreateDialog = () => {
  dialogMode.value = 'create'
  resetTaskForm()
  taskForm.id = createTaskId()
  dialogVisible.value = true
}

const openEditDialog = (task: TaskItem) => {
  dialogMode.value = 'edit'
  Object.assign(taskForm, task)
  dialogVisible.value = true
}

const openViewDialog = (task: TaskItem) => {
  dialogMode.value = 'view'
  Object.assign(taskForm, task)
  dialogVisible.value = true
}

const saveTask = () => {
  if (!taskForm.name || !taskForm.project || !taskForm.owner || !taskForm.deadline) {
    ElMessage.warning('请完整填写任务信息')
    return
  }

  // This page is static for now, so saves only update local memory and reset on refresh.
  if (dialogMode.value === 'create') {
    tasks.value.unshift({ ...taskForm })
    ElMessage.success('新增任务成功')
  }

  if (dialogMode.value === 'edit') {
    const taskIndex = tasks.value.findIndex((task) => task.id === taskForm.id)

    if (taskIndex !== -1) {
      tasks.value[taskIndex] = { ...taskForm }
      ElMessage.success('编辑任务成功')
    }
  }

  dialogVisible.value = false
}

const deleteTask = async (task: TaskItem) => {
  try {
    await ElMessageBox.confirm(`确认删除任务「${task.name}」吗？`, '删除确认', {
      confirmButtonText: '删除',
      cancelButtonText: '取消',
      type: 'warning',
    })

    tasks.value = tasks.value.filter((item) => item.id !== task.id)
    ElMessage.success('删除任务成功')
  } catch {
    ElMessage.info('已取消删除')
  }
}
</script>

<template>
  <main class="task-page">
    <el-card class="task-card" shadow="never">
      <template #header>
        <div class="task-card__header">
          <div>
            <h2>任务管理</h2>
            <p>统一维护任务分配、状态、优先级和截止日期</p>
          </div>
          <el-button type="primary" @click="openCreateDialog">新增任务</el-button>
        </div>
      </template>

      <el-form class="task-query" :model="queryForm" inline>
        <el-form-item label="任务名称">
          <el-input v-model="queryForm.name" clearable placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="任务状态">
          <el-select v-model="queryForm.status" clearable placeholder="请选择任务状态">
            <el-option
              v-for="status in taskStatusOptions"
              :key="status"
              :label="status"
              :value="status"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="queryForm.owner" clearable placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="applyQuery">查询</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table class="task-table" :data="filteredTasks" border>
        <el-table-column prop="id" label="任务编号" width="120" />
        <el-table-column prop="name" label="任务名称" min-width="180" />
        <el-table-column prop="project" label="所属项目" min-width="160" />
        <el-table-column prop="owner" label="负责人" width="110" />
        <el-table-column label="状态" width="110">
          <template #default="{ row }">
            <el-tag :type="getStatusTagType(row.status)">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="优先级" width="100">
          <template #default="{ row }">
            <el-tag :type="getPriorityTagType(row.priority)" effect="plain">
              {{ row.priority }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="deadline" label="截止日期" width="130" />
        <el-table-column label="操作" width="190" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" @click="openViewDialog(row)">查看</el-button>
            <el-button link type="primary" @click="openEditDialog(row)">编辑</el-button>
            <el-button link type="danger" @click="deleteTask(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="520px">
      <el-form class="task-dialog-form" :model="taskForm" label-width="90px">
        <el-form-item label="任务编号">
          <el-input v-model="taskForm.id" disabled />
        </el-form-item>
        <el-form-item label="任务名称">
          <el-input v-model="taskForm.name" :disabled="isViewMode" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="所属项目">
          <el-input v-model="taskForm.project" :disabled="isViewMode" placeholder="请输入所属项目" />
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="taskForm.owner" :disabled="isViewMode" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="taskForm.status" :disabled="isViewMode" placeholder="请选择状态">
            <el-option
              v-for="status in taskStatusOptions"
              :key="status"
              :label="status"
              :value="status"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="taskForm.priority" :disabled="isViewMode" placeholder="请选择优先级">
            <el-option
              v-for="priority in taskPriorityOptions"
              :key="priority"
              :label="priority"
              :value="priority"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="截止日期">
          <el-input v-model="taskForm.deadline" :disabled="isViewMode" placeholder="例如 2026-07-01" />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="dialogVisible = false">{{ isViewMode ? '关闭' : '取消' }}</el-button>
        <el-button v-if="!isViewMode" type="primary" @click="saveTask">保存</el-button>
      </template>
    </el-dialog>
  </main>
</template>

<style scoped>
.task-page {
  width: 100%;
}

.task-card {
  border-radius: 8px;
}

.task-card__header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
}

.task-card__header h2 {
  margin: 0;
  color: #111827;
  font-size: 20px;
  font-weight: 600;
}

.task-card__header p {
  margin: 4px 0 0;
  color: #6b7280;
  font-size: 13px;
}

.task-query {
  display: flex;
  flex-wrap: wrap;
  gap: 0 8px;
  padding: 4px 0 16px;
}

.task-query :deep(.el-input),
.task-query :deep(.el-select) {
  width: 200px;
}

.task-table {
  width: 100%;
}

.task-dialog-form :deep(.el-select) {
  width: 100%;
}
</style>

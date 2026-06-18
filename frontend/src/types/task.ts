export type TaskStatus = '待开始' | '进行中' | '已完成' | '已延期'

export type TaskPriority = '高' | '中' | '低'

export type DialogMode = 'create' | 'edit' | 'view'

export interface TaskItem {
  id: string
  name: string
  project: string
  owner: string
  status: TaskStatus
  priority: TaskPriority
  deadline: string
}

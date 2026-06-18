import type { TaskItem, TaskPriority, TaskStatus } from '@/types/task'

export const taskStatusOptions: TaskStatus[] = ['待开始', '进行中', '已完成', '已延期']

export const taskPriorityOptions: TaskPriority[] = ['高', '中', '低']

// Static demo data for the task list. Runtime changes are kept in page memory only.
export const mockTasks: TaskItem[] = [
  {
    id: 'TASK-001',
    name: '需求评审会议准备',
    project: '工程任务系统',
    owner: '张晨',
    status: '进行中',
    priority: '高',
    deadline: '2026-06-24',
  },
  {
    id: 'TASK-002',
    name: '任务列表页面原型确认',
    project: '工程任务系统',
    owner: '李然',
    status: '已完成',
    priority: '中',
    deadline: '2026-06-20',
  },
  {
    id: 'TASK-003',
    name: '项目成员权限梳理',
    project: '协作平台升级',
    owner: '王宁',
    status: '待开始',
    priority: '中',
    deadline: '2026-06-28',
  },
  {
    id: 'TASK-004',
    name: '接口字段对齐',
    project: '数据看板建设',
    owner: '赵一',
    status: '已延期',
    priority: '高',
    deadline: '2026-06-18',
  },
  {
    id: 'TASK-005',
    name: '测试用例补充',
    project: '工程任务系统',
    owner: '陈思',
    status: '进行中',
    priority: '低',
    deadline: '2026-07-02',
  },
  {
    id: 'TASK-006',
    name: '看板指标口径确认',
    project: '数据看板建设',
    owner: '李然',
    status: '待开始',
    priority: '低',
    deadline: '2026-07-05',
  },
]

import { post } from '@/utils/request'

export default {
  pageList: query => post('/api/teacher/task/page', query),
  edit: query => post('/api/teacher/task/edit', query),
  select: id => post('/api/teacher/task/select/' + id),
  deleteTask: id => post('/api/teacher/task/delete/' + id)
}

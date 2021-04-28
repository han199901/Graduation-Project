import { post } from '@/utils/request'

export default {
  page: query => post('/api/teacher/examPaperAnswer/page', query)
}

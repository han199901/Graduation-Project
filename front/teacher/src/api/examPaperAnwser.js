import { post } from '@/utils/request'

export default {
  page: query => post('/api/teacher/examPaperAnswer/page', query),
  pageList: query => post('/api/teacher/examPaperAnswer/pageList', query),
  answerSubmit: form => post('/api/teacher/examPaperAnswer/answerSubmit', form),
  read: id => post('/api/teacher/examPaperAnswer/read/' + id),
  edit: form => post('/api/teacher/examPaperAnswer/edit', form)
}

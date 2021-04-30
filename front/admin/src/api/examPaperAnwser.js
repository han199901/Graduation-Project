import { post } from '@/utils/request'

export default {
  page: query => post('/api/admin/examPaperAnswer/page', query),

  // Judge API

  pageList: query => post('/api/admin/examPaperAnswer/pageList', query),
  answerSubmit: form => post('/api/admin/examPaperAnswer/answerSubmit', form),
  read: id => post('/api/admin/examPaperAnswer/read/' + id),
  edit: form => post('/api/admin/examPaperAnswer/edit', form)

}

import { post } from '@/utils/request'

export default {
  pageList: query => post('/api/teacher/message/page', query),
  send: query => post('/api/teacher/message/send', query)
}

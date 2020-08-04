import { post } from '@/utils/request'

export default {
  getDeptPageList: query => post('/api/admin/dept/page/list', query)
}

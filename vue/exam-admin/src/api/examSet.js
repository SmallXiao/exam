import { post } from '@/utils/request'

export default {
  subjectList: () => post('/api/admin/education/subject/list'),
  edit: query => post('/api/admin/examSet/edit', query),
  select: id => post('/api/admin/examSet/select/' + id)
}

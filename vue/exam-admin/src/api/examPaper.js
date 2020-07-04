import { post } from '@/utils/request'

export default {
  pageList: query => post('/api/admin/question/subjectpageList', query),
  taskExamPage: query => post('/api/admin/exam/paper/taskExamPage', query),
  edit: query => post('/api/admin/exam/paper/edit', query),
  edit1: query => post('/api/admin/exam/paper/insert', query),
  select: id => post('/api/admin/exam/paper/select/' + id),
  selectList: () => post('/api/admin/exam/paper/select/'),
  deletePaper: id => post('/api/admin/exam/paper/delete/' + id),
  deleteData: ids => post('/api/admin/exam/paper/deletebatch/' + ids)
}

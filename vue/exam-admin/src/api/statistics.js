import { post } from '@/utils/request'

export default {
  getDailyReport: query => post('/api/statistics/dailyReport', query),
  getDailyUserReport: query => post('/api/statistics/dailyUserReport', query),
  getDeptReport: query => post('/api/statistics/deptReport', query),
  getMonthlyReport: query => post('/api/statistics/monthlyReport', query),
  getUserAnswerDateReport: query => post('/api/statistics/userAnswerDateReport', query),
  getWrongQuestionReport: query => post('/api/statistics/wrongQuestionReport', query)
}

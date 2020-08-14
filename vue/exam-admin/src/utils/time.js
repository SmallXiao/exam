/**
 * 获取当前时间
 */
export function getTime () {
  let date = new Date()
  let year = date.getFullYear()
  let month = date.getMonth() + 1
  let day = date.getDate()
  let hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
  let minute = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
  let second = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
  // eslint-disable-next-line no-unused-expressions
  month >= 1 && month <= 9 ? (month = '0' + month) : ''
  // eslint-disable-next-line no-unused-expressions
  day >= 0 && day <= 9 ? (day = '0' + day) : ''
  let timer = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second
  return timer
}

/**
 * 获取日期
 */
// eslint-disable-next-line camelcase
export function getDateStr (p_count) {
  let dd = new Date()
  // eslint-disable-next-line camelcase
  dd.setDate(dd.getDate() + p_count) // 获取p_count天后的日期
  let y = dd.getFullYear()
  let m = dd.getMonth() + 1 // 获取当前月份的日期
  if (m < 10) {
    m = '0' + m
  }
  let d = dd.getDate()
  if (d < 10) {
    d = '0' + d
  }
  return y + '' + m + '' + d
}

/**
 * 获取上周一
 */
export function getLastWeekMonday () {
  let now = new Date()
  let nowTime = now.getTime()
  let day = now.getDay()
  let oneDayTime = 24 * 60 * 60 * 1000
  let MondayTime = nowTime - (day - 1) * oneDayTime // 显示周一
  return formatDate(new Date(MondayTime))
}

/**
 * 获取上周日
 */
export function getLastWeekSunday () {
  let now = new Date()
  let nowTime = now.getTime()
  let day = now.getDay()
  let oneDayTime = 24 * 60 * 60 * 1000
  let SundayTime = nowTime + (7 - day) * oneDayTime // 显示周日
  return formatDate(new Date(SundayTime))
}

/**
 * 格式化日期：yyyy-MM-dd
 */
function formatDate (date) {
  let myyear = date.getFullYear()
  let mymonth = date.getMonth() + 1
  let myweekday = date.getDate()
  if (mymonth < 10) {
    mymonth = '0' + mymonth
  }
  if (myweekday < 10) {
    myweekday = '0' + myweekday
  }
  return myyear + mymonth + myweekday
}

/**
 * 获取当月第一天
 */
export function getCurrentMonthFirst () {
  let date = new Date()
  date.setDate(1)
  let month = parseInt(date.getMonth() + 1)
  let day = date.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return date.getFullYear() + month + day
}

/**
 * 获取当月最后一天
 */
export function getCurrentMonthLast () {
  let date = new Date()
  let currentMonth = date.getMonth()
  let nextMonth = ++currentMonth
  let nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1)
  let oneDay = 1000 * 60 * 60 * 24
  let lastTime = new Date(nextMonthFirstDay - oneDay)
  let month = parseInt(lastTime.getMonth() + 1)
  let day = lastTime.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return date.getFullYear() + month + day
}

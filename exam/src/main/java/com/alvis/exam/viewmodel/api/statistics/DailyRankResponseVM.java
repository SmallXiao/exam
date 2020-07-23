package com.alvis.exam.viewmodel.api.statistics;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author sunxiao
 * @Date 2020/7/23
 **/
@Getter
@Setter
public class DailyRankResponseVM {

    // 排名
    private Integer rank;

    // 用户ID
    private Integer userId;

    // 错题个数
    private Integer errorCount;

    // 花费时间
    private Integer spendTime;
}

package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "答题时间，耗时（毫秒）")
    private Integer spendTime;
}

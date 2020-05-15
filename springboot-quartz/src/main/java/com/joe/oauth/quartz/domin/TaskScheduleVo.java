package com.joe.oauth.quartz.domin;

import lombok.Data;

/**
 * @program: geometry-bi
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-03-24 09:22
 **/
@Data
public class TaskScheduleVo {

    /**
     * 所选作业类型:
     * 0  -> 每秒
     * 4  -> 每分
     * 1  -> 每天
     * 3  -> 每周
     * 2  -> 每月
     */
    Integer jobType;

    /**月*/
    Integer[] dayOfMonths;

    /**天*/
    Integer[] dayOfWeeks;

    /**秒  */
    Integer second;

    /**分  */
    Integer minute;

    /**时  */
    Integer hour;

}

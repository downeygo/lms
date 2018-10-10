package com.imen.lms.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @autor LIGANG
 * @data 2018/10/6 11:14
 * @description
 */
@Getter
@Setter
@ToString
public class Permission extends BaseDomain {
    private String name;//名称
    private String resource;//表达式
}

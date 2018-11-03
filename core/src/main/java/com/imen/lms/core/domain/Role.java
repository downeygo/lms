package com.imen.lms.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 11:16
 * @description
 */
@Getter@Setter@ToString
public class Role extends BaseDomain{
    private String name;//名称
    private String code;//编码s
    private List<Permission> permissions;//拥有的权限
}

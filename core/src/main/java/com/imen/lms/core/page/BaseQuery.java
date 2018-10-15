package com.imen.lms.core.page;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:40
 * @description 高级查询
 */
@Setter
@Getter
@Mapper
public class BaseQuery {
    private int currentPage = 1;//当前页
    private int pageSize = 10;//每页条数

    public int getStart() {
        return (currentPage - 1) * pageSize;
    }

}

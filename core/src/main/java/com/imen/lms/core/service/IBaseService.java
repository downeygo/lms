package com.imen.lms.core.service;

import com.imen.lms.core.page.BaseQuery;
import com.imen.lms.core.page.PageResult;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:49
 * @description
 */
public interface IBaseService<T1, T2> {
    /**
     * 分页+高级查询
     *
     * @param queryObj
     * @return
     */
    PageResult<T1> query(T2 queryObj, Integer currentPage, Integer pageSize);
}

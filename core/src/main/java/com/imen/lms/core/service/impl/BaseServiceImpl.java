package com.imen.lms.core.service.impl;

import com.imen.lms.core.mapper.BaseMapper;
import com.imen.lms.core.page.BaseQuery;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:51
 * @description
 */
@Service
@Transactional
public class BaseServiceImpl<T1, T2> implements IBaseService<T1, T2> {

    @Autowired
    private BaseMapper<T1, T2> baseMapper;
    @Autowired
    private BaseQuery bq;

    /**
     * 分页+高级查询
     *
     * @param queryObj
     * @return
     */
    @Override
    public PageResult<T1> query(T2 queryObj, Integer currentPage, Integer pageSize) {
        Integer count = baseMapper.queryCount(queryObj);
        if (count <= 0) {
            return new PageResult<T1>(0, Collections.EMPTY_LIST, 1, pageSize);
        }
        List<T1> listResult = baseMapper.query(queryObj);
        System.out.println(bq.getCurrentPage()+"**"+bq.getPageSize());
        return new PageResult<T1>(count, listResult, currentPage, pageSize);
    }
}

package com.imen.lms.core.mapper;

import com.imen.lms.core.page.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:42
 * @description
 */
@Mapper
public interface BaseMapper<T> {
    /**
     * 根据您查询条件查询总数
     *
     * @param queryObj
     * @return
     */
    Integer queryCount(T queryObj);

    /**
     * 根据查询条件查询结果
     *
     * @param queryObj
     * @return
     */
    List<T> query(T queryObj);
}

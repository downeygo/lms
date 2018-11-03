package com.imen.lms.core.service;

import com.imen.lms.core.page.PageResult;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:49
 * @description
 */
public interface IBaseService<T> {
    /**
     * 删除
     * @param id id
     * @return
     */
    int deleteOne(Integer id);

    /**
     * 查询所有
     *
     * @return 集合
     */
    List<T> listAll();

    /**
     * 更新
     *
     * @param obj 实体类
     * @return 结果数
     */
    int updateOne(T obj);

    /**
     * 插入
     *
     * @param obj 实体类
     * @return 结果数
     */
    int insertOne(T obj);

    /**
     * 通过id查询
     *
     * @param id
     * @return 实体类
     */
    T getByID(Integer id);

    /**
     * 分页+高级查询
     *
     * @param queryObj
     * @return
     */
    PageResult query(T queryObj, Integer currentPage, Integer pageSize);
}

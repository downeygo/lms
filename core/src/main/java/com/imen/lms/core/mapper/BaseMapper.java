package com.imen.lms.core.mapper;

import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.IDeviceService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:42
 * @description
 */
@Mapper
public interface BaseMapper<T> {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteOne(Integer id);

    /**
     * 查询所有
     * @return 集合
     */
    List<T> listAll();

    /**
     * 更新
     * @param obj 实体类
     * @return 结果数
     */
    int updateOne(T obj);

    /**
     * 插入
     * @param obj 实体类
     * @return 结果数
     */
    int insertOne(T obj);

    /**
     * 通过id查询
     * @param id
     * @return 实体类
     */
    T getByID(Integer id);

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

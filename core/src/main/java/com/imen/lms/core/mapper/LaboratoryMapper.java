package com.imen.lms.core.mapper;

import com.imen.lms.core.domain.Laboratory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:20
 * @description
 */
@Mapper
public interface LaboratoryMapper {
    /**
     * 查询实验室信息
     * @return 实验室信息结合
     */
    List<Laboratory> selectAll();
}

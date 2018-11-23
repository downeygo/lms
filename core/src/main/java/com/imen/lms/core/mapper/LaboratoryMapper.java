package com.imen.lms.core.mapper;

import com.imen.lms.core.domain.Laboratory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:20
 * @description
 */
@Mapper
public interface LaboratoryMapper extends BaseMapper<Laboratory> {
    /**
     * 查询实验室成员
     * @param id
     * @return
     */
    List<Map<String, String>> getMember(Integer id);

    /**
     * 添加实验室成员
     * @param labxuser
     * @return
     */
    int addMember(Map<String, Object> labxuser);
}

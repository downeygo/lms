package com.imen.lms.core.service;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.page.LaboratoryQuery;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:28
 * @description
 */
public interface ILaboratoryService {
    /**
     * 查询实验室信息
     *
     * @return 实验室列表
     */
    List<Laboratory> selectAll();
}

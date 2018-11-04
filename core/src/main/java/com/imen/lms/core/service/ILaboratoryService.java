package com.imen.lms.core.service;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.page.LaboratoryQuery;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:28
 * @description
 */
public interface ILaboratoryService extends IBaseService<Laboratory> {
    /**
     * 添加实验室
     * @param laboratory
     * @return
     */
    int insertOneCus(Laboratory laboratory);

    /**
     * 关闭或启用实验室
     * @param id
     * @return
     */
    int closeOrOpen(Integer id);


}

package com.imen.lms.core.service;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.page.LaboratoryQuery;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询用户实验室中间表
     * @param id
     * @return
     */
    List<Map<String, String>> getMember(Integer id);

    /**
     * 获取用户字符串
     * @param member
     * @return
     */
    String getMemberString(List<Map<String, String>> member);

    /**
     * 添加实验室成员
     * @param labID
     * @param userID
     * @param typeID
     * @return
     */
    int addMember(Integer labID, Integer userID, Integer typeID);
}

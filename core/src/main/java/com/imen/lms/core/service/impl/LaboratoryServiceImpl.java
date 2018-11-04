package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.mapper.LaboratoryMapper;
import com.imen.lms.core.service.ILaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:29
 * @description
 */
@Service
@Transactional
public class LaboratoryServiceImpl extends BaseServiceImpl<Laboratory> implements ILaboratoryService {

    @Autowired
    private LaboratoryMapper laboratoryMapper;


    @Override
    public int insertOneCus(Laboratory laboratory) {
        laboratory.setEstablishTime(new Date());
        laboratory.setStatus(Laboratory.STATUS_NORMAL);
        return this.insertOne(laboratory);
    }

    @Override
    public int closeOrOpen(Integer id) {
        Laboratory laboratory = laboratoryMapper.getByID(id);
        if (laboratory == null) {
            throw new RuntimeException("操作失败,请联系管理员");
        }
        if (laboratory.getStatus() == Laboratory.STATUS_CLOSE) {
            laboratory.setStatus(Laboratory.STATUS_NORMAL);
        } else if (laboratory.getStatus() == Laboratory.STATUS_NORMAL) {
            laboratory.setStatus(Laboratory.STATUS_CLOSE);
        }else {
            throw new RuntimeException("操作失败,请联系管理员");
        }
        return this.updateOne(laboratory);
    }


}

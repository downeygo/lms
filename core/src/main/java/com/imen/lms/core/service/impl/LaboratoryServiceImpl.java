package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.mapper.LaboratoryMapper;
import com.imen.lms.core.page.LaboratoryQuery;
import com.imen.lms.core.service.ILaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:29
 * @description
 */
@Service
@Transactional
public class LaboratoryServiceImpl implements ILaboratoryService {

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Override
    public List<Laboratory> selectAll() {
        return laboratoryMapper.selectAll();
    }
}

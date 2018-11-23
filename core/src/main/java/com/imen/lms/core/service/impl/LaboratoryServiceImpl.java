package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.mapper.LaboratoryMapper;
import com.imen.lms.core.mapper.UserInforMapper;
import com.imen.lms.core.service.ILaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserInforMapper userInforMapper;



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
        } else {
            throw new RuntimeException("操作失败,请联系管理员");
        }
        return this.updateOne(laboratory);
    }

    @Override
    public List<Map<String, String>> getMember(Integer id) {
        return laboratoryMapper.getMember(id);
    }

    public String getMemberString(List<Map<String, String>> member){
        String memberString = "";
        for (Map<String, String> m : member) {
            memberString += m.get("memberName") + "(" + m.get("type") + ")  ";
        }
        return memberString;
    }

    @Override
    public int addMember(Integer labID, Integer userID, Integer typeID) {
        Laboratory lab = laboratoryMapper.getByID(labID);
        if (lab == null){
            throw new RuntimeException("实验室不存在，添加失败！");
        }

        UserInfor userInfor = userInforMapper.get(userID);

        if (userInfor == null){
            throw new RuntimeException("用户不存在，添加失败！");
        }

        Map<String, Object> labxuser = new HashMap<>();
        labxuser.put("id", labID);
        labxuser.put("labName", lab.getName());
        labxuser.put("memberID", userID);
        labxuser.put("memberName", userInfor.getRealName());
        labxuser.put("type", typeID);

        return laboratoryMapper.addMember(labxuser);
    }


}

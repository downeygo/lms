package com.imen.lms.core.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/24 15:01
 * @description JSON返回工具
 */
@Getter@Setter
public class JSONResult<T> {
    private boolean success;

    public JSONResult(boolean success, List<T> objList, List<T> objList2) {
        this.success = success;
        this.objList = objList;
        this.objList2 = objList2;
    }

    private String msg;
    private String msg2;
    private List<T> objList;
    private List<T> objList2;
    private T obj;

    public JSONResult(String msg){
        this.msg=msg;
    }

    public JSONResult(boolean success,String msg){
        this.success=success;
        this.msg=msg;
    }

    public JSONResult(String msg, String msg2) {
        this.msg = msg;
        this.msg2 = msg2;
    }

    public JSONResult(boolean success, String msg, String msg2) {
        this.success = success;
        this.msg = msg;
        this.msg2 = msg2;
    }

    public JSONResult(boolean success, List<T> objList) {
        this.success = success;
        this.objList = objList;
    }

    public JSONResult(boolean success, T obj) {
        this.success = success;
        this.obj = obj;
    }
}

package com.imen.lms.core.util;

import java.util.Arrays;

/**
 * @autor LIGANG
 * @data 2018/10/6 20:04
 * @description
 */
public class StringUtil {
    public static Integer[] stringToIntArr(String str) {
        if (!isEmpty(str)) {
            String[] split = str.split(",");
            Integer[] intArr = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                intArr[i] = Integer.valueOf(split[i]);
            }
            return intArr;
        }else{
            return new Integer[0];
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}

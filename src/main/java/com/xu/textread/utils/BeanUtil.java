package com.xu.textread.utils;

import org.springframework.beans.BeanUtils;

/**
 * @Author xyc
 * @CreteDate 2023/2/11 15:28
 **/
public class BeanUtil {
    public static  <T> T copyProperties(Object source, T target) {

        if (source == null) {
            return target;
        }

        BeanUtils.copyProperties(source, target);

        return target;
    }
}

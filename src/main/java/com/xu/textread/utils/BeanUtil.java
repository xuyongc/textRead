package com.xu.textread.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xyc
 * @CreteDate 2023/2/11 15:28
 **/
public class BeanUtil {
    public static <T> T copyProperties(Object source, T target) {

        if (source == null) {
            return target;
        }

        BeanUtils.copyProperties(source, target);

        return target;
    }

    public static <T> List<T> copyList(List sources, T target) {
        List<T> result = new ArrayList<>();

        if (sources != null) {
            for (Object source : sources) {
                result.add(copyProperties(source, target));
            }
        }

        return result;
    }

}

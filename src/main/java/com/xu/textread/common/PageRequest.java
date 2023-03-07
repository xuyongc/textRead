package com.xu.textread.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author xyc
 * @CreteDate 2023/2/8 17:37
 *
 * 通用分页请求参数
 **/
@Data
public class PageRequest implements Serializable {

    /**
     * 页面长度
     */
    protected int pageSize;

    /**
     * 第几页
     */
    protected int pageNumber;
}

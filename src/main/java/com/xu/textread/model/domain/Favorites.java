package com.xu.textread.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收藏夹
 * @TableName favorites
 */
@TableName(value ="favorites")
@Data
public class Favorites implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Long favoritesId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文章Id
     */
    private Long textId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑-1删除 -0存在
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
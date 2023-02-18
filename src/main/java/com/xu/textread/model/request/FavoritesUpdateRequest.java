package com.xu.textread.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏夹
 * @author aniki
 * @TableName favorites
 */
@Data
public class FavoritesUpdateRequest implements Serializable {
    /**
     * 表修改
     */
    private Long updateCode;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文章Id
     */
    private Long textId;

}
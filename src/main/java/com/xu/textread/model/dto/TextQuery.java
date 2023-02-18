package com.xu.textread.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.textread.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 *
 * @author aniki
 * @TableName text
 */
@Data
public class TextQuery extends PageRequest implements Serializable {
    /**
     * 文章Id
     */
    private Long textId;

    /**
     * 文章标题
     */
    private String textTitle;

    /**
     * 作者Id
     */
    private Long textAuthorId;

}
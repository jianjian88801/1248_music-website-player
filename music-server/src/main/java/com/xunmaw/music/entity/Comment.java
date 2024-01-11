package com.xunmaw.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 评论类型（0歌曲1歌单）
     */
    private Boolean type;

    /**
     * 歌曲id
     */
    private Integer songId;

    /**
     * 歌单id
     */
    private Integer songListId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 收藏时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 评论点赞数
     */
    private Integer up;


}

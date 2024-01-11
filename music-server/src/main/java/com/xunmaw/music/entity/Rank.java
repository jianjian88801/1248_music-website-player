package com.xunmaw.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 评价
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Accessors(chain = true)
public class Rank implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌单id
     */
    private Integer songListId;

    /**
     * 用户id
     */
    private Integer consumerId;

    /**
     * 评分
     */
    private Integer score;


}

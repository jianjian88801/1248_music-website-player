package com.xunmaw.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 歌曲(Song)表实体类
 *
 * @author makejava
 * @since 2021-06-17 14:19:42
 */
@SuppressWarnings("serial")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)//链式set方法
public class Song extends Model<Song> {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //歌手id
    private Integer singerId;
    //革命
    private String name;
    //简介
    private String introduction;
    //创建时间
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //歌曲图片
    private String pic;
    //歌词
    private String lyric;
    //歌曲地址
    private String url;

}

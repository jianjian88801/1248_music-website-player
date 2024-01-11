package com.xunmaw.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 前端用户
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别（1男0女）
     */
    private Boolean sex;

    /**
     * 电话
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")//date字符串转Date类型
    private Date birth;

    /**
     * 签名
     */
    private String introduction;

    /**
     * 地区
     */
    private String location;

    /**
     * 头像
     */
    private String avator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}

package com.xunmaw.music.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 歌手(Singer)实体类
 *
 * @author makejava
 * @since 2021-06-10 18:25:22
 */
public class Singer implements Serializable {
    private static final long serialVersionUID = 372984511951162091L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别（0女1男2组合3不明）
    */
    private Byte sex;
    /**
    * 头像
    */
    private String pic;
    /**
    * 生日
    */
    private String birth;
    /**
    * 地区
    */
    private String location;
    /**
    * 简介
    */
    private String introduction;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}

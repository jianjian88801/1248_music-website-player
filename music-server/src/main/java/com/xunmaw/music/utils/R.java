package com.xunmaw.music.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *  统一返回结果
 */
@Data
public class R {

    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    /**
     * 构造函数私有化
     */
    private R(){

    }

    /**
     * 返回成功结果
     * @return
     */
    public static R ok(){
       R r = new R();
       r.setCode(ResponseEnum.SUCCESS.getCode());
       r.setMessage(ResponseEnum.SUCCESS.getMessage());
       return r;
    }

    /**
     * 返回其他枚举结果
     * @return
     */
    public static R result(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }



    /**
     * 返回失败结果
     * @return
     */
    public static R error(){
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 给data赋值
     * @param key
     * @param value
     * @return
     */
    public R data(String key,Object value){
      this.data.put(key,value);
      return this;
    }

    /**
     * 设置自定义返回信息
     * @param message
     * @return
     */
    public R message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置自定义返回码
     * @param code
     * @return
     */
    public R code(Integer code){
        this.setCode(code);
        return this;
    }



}
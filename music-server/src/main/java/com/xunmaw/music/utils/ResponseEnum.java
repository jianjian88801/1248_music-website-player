package com.xunmaw.music.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {

    //定义枚举
    SUCCESS(0, "成功"),
    ERROR(-1, "服务器内部错误"),
    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(-103, "文件上传错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败");
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;
}
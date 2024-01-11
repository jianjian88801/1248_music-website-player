package com.xunmaw.music.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author : smalljop
 * @description : 分页
 * @create : 2020-12-09 10:47
 **/
@Data
public class PageRequest {

    private String searchKey;

    private Integer  current;


    private Integer size;
    /**
     * 排序列
     */

    private String orderByColumn ;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    private String isAsc = "asc";


    public Page toMybatisPage() {
        return new Page(current, size);
    }
}
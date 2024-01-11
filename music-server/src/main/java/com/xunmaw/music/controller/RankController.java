package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.xunmaw.music.entity.Rank;
import com.xunmaw.music.service.RankService;
import com.xunmaw.music.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 评价 前端控制器
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
@RestController
public class RankController {


    @Resource
    private RankService service;

    /**
     * 新增评价
     */
    @RequestMapping(value = "/rank/add",method = RequestMethod.POST)
    public Object add(Rank rank){
        JSONObject jsonObject = new JSONObject();
        boolean flag = service.insertRank(rank);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评价成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评价失败");
        return jsonObject;
    }

    /**
     * 计算平均分,根据SongListId查询rank
     */
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public Object rankOfSongListId(Integer songListId){
        return service.rankOfSongListId(songListId);
    }
}


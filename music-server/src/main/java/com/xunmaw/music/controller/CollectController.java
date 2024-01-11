package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.entity.Collect;
import com.xunmaw.music.service.CollectService;
import com.xunmaw.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(Collect collect){
        JSONObject jsonObject = new JSONObject();
        if(collect.getSongId()==null||collect.getSongId().equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏歌曲为空");
            return jsonObject;
        }
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",collect.getSongId());
        queryWrapper.eq("user_id",collect.getUserId());
        if(collectService.getOne(queryWrapper)!=null){//用户已收藏该歌曲
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已收藏");
            return jsonObject;
        }

        boolean flag = collectService.save(collect);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        boolean flag = collectService.remove(queryWrapper);
        return flag;
    }

    /**
     * 查询所有收藏
     */
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public Object allCollect(){
        return collectService.list();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(Integer userId){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("user_id",userId);
        return collectService.list(queryWrapper);
    }


}


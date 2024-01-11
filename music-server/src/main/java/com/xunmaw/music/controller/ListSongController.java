package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.entity.ListSong;
import com.xunmaw.music.service.ListSongService;
import com.xunmaw.music.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *   歌单包含歌曲列表 前端控制器
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Resource
    private ListSongService listSongService;

    /**
     * 给歌单添加歌曲
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(ListSong listSong){
        JSONObject jsonObject = new JSONObject();
        boolean flag = listSongService.save(listSong);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"保存失败");
        return jsonObject;

    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public List<ListSong> detail(Integer songListId){
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return listSongService.list(queryWrapper);
    }

    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(Integer songId,Integer songListId){
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        //删除XXX歌单的XXX歌曲
        queryWrapper.eq("song_list_id",songListId);
        queryWrapper.eq("song_id",songId);
        boolean flag = listSongService.remove(queryWrapper);
        return flag;
    }


}


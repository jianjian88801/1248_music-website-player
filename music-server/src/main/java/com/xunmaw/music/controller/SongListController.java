package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.entity.SongList;
import com.xunmaw.music.service.SongListService;
import com.xunmaw.music.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 歌单功能接口
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Resource
    private SongListService songListService;

    /**
     * 添加歌单
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(SongList songList){
        JSONObject jsonObject = new JSONObject();
        boolean flag = songListService.insertSongList(songList);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改歌单
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(SongList songList) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = songListService.updateById(songList);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }


    /**
     * 删除歌单
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSongList(Integer id){
        boolean flag = songListService.removeById(id);
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(Integer id){
        return songListService.getById(id);
    }

    /**
     * 查询所有歌单
     */
    @RequestMapping(value = "/allSongList",method = RequestMethod.GET)
    public Object allSongList(){
        return songListService.list();//查询所有
    }

    /**
     * 根据标题精确查询歌单列表
     */
    @RequestMapping(value = "/songListOfTitle",method = RequestMethod.GET)
    public List<SongList> songListOfName(String title){//歌单标题
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        return songListService.list(queryWrapper);
    }

    /**
     * 根据标题模糊查询歌单列表
     */
    @RequestMapping(value = "/likeTitle",method = RequestMethod.GET)
    public Object likeTitle(String title){
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return songListService.list(queryWrapper);
    }

    /**
     * 根据风格模糊查询歌单列表
     */
    @RequestMapping(value = "/likeStyle",method = RequestMethod.GET)
    public Object likeStyle(String style){
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style",style);
        return songListService.list(queryWrapper);
    }

    /**
     * 更新歌单图片
     */
    @RequestMapping(value = "/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.updateById(songList);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }
}


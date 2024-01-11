package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.entity.Song;
import com.xunmaw.music.service.SongService;
import com.xunmaw.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌曲(Song)表控制层
 *
 * @author makejava
 * @since 2021-06-17 14:19:44
 */
@RestController
@RequestMapping("song")
public class SongController {
    /**
     * 服务对象
     */
    @Autowired
    private SongService songService;

    //上传歌曲
    @RequestMapping("/add")
    public Object addSong(Song song, @RequestParam("file") MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();

        //上传歌曲文件,文件为空，上传失败
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //文件路径，歌曲存储路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/" + fileName;


        try {
            mpFile.transferTo(dest);
            song.setUrl(storeUrlPath);
            System.out.println(song);
            Boolean flag = songService.insertSong(song);
            System.out.println(flag);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "保存成功");
                jsonObject.put("avator", storeUrlPath);
                return jsonObject;
            }
        } catch (IOException e) {
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "保存失败");
                return jsonObject;
        }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败");
            return jsonObject;
    }


    /**
     * 根据歌手id查询歌曲,返回一个歌曲列表
     */
    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
    public List<Song> songOfSingerId(Integer singerId) {
        return songService.songOfSingerId(singerId);
    }

    /**
     * 修改歌曲
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSong(Song song) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = songService.update(song, null);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 根据主键删除歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSinger(Integer id) {
        //-TODO 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        boolean flag = songService.removeById(id);
        return flag;
    }

    /**
     * 更新歌曲图片
     */
    @RequestMapping(value = "/updateSongPic", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        //如果文件是空的，直接返回
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/" + fileName;
        try {
            avatorFile.transferTo(dest);//上传
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.updateById(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }


    /**
     * 根据歌曲id查询歌曲对象
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object detail(Integer songId) {
        return songService.getById(songId);
    }

    /**
     * 根据歌曲名称精确查询歌曲
     */
    @RequestMapping(value = "/songOfSongName", method = RequestMethod.GET)
    public List<Song> songOfSongName(String songName) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", songName);
        return songService.list(queryWrapper);
    }

    /**
     * 根据歌手名字模糊查询歌曲
     */
    @RequestMapping(value = "/likeSongOfName", method = RequestMethod.GET)
    public Object likeSongOfName(String singerName) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", singerName);
        return songService.list(queryWrapper);
    }

    /**
     * 查询所有歌曲
     */
    @RequestMapping(value = "/allSong", method = RequestMethod.GET)
    public Object allSong() {
        return songService.list(null);
    }
}

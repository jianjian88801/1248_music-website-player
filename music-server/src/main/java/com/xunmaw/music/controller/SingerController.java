package com.xunmaw.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xunmaw.music.entity.Singer;
import com.xunmaw.music.service.SingerService;
import com.xunmaw.music.utils.Consts;
import com.xunmaw.music.utils.PageRequest;
import com.xunmaw.music.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 歌手(Singer)表控制层
 *
 * @author makejava
 * @since 2021-06-10 18:25:24
 */
@RestController
@RequestMapping("singer")
public class SingerController {
    /**
     * 服务对象
     */
    @Resource
    private SingerService singerService;

    //添加歌手
    @PostMapping("/add")
    public Object addSinger(Singer singer){
        JSONObject jsonObject=new JSONObject();
        boolean flag = singerService.insert(singer);
        if(flag){//保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }
    //修改歌手
    @PostMapping("/update")
    public Object editSinger(Singer singer){
        JSONObject jsonObject=new JSONObject();
        boolean flag = singerService.update(singer);
        if(flag){ //保存成功
          //  System.out.println("修改成功");
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }
    //删除歌手
    @RequestMapping("/delete")
    public Object deleteSinger(Integer id){
        JSONObject jsonObject=new JSONObject();
        boolean flag = singerService.deleteById(id);
        if(flag){ //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Singer selectOne(Integer id) {
        return this.singerService.queryById(id);
    }

    //查询所有歌手
    @RequestMapping("allSinger")
    public Object findAllSinger(){
        //System.out.println("查询所有歌手成功");
        return singerService.queryAll();//直接返回列表
    }
    @PostMapping ("list")
    public R singerList(@RequestBody PageRequest pageRequest){
        return R.ok().data("data",singerService.selectSingerByPage(pageRequest));
    }


    //根据歌手name模糊查询
    @RequestMapping("singersOfName")
    public Object singleOfName(String name){
       return  singerService.singerOfName("%"+name.trim()+"%");
    }

    //根据歌手sex模糊查询
    @RequestMapping("singersOfSex")
    public Object singleOfSex(String sex){//0 女   1 男    2组合   3不明
        Integer sex1=Integer.parseInt(sex.trim());
        return  singerService.singerOfSex(sex1);
    }

    /**
     * 更新歌手图片
     */
    @RequestMapping(value = "/updateSingerPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
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
}

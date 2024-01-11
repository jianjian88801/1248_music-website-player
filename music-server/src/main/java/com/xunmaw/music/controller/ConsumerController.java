package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.entity.Consumer;
import com.xunmaw.music.service.ConsumerService;
import com.xunmaw.music.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 前端用户 前端控制器
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    /**
     * 添加前端用户
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addConsumer(Consumer consumer){
        JSONObject jsonObject = new JSONObject();


        if(consumer.getUsername()==null||consumer.getUsername().equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }

        //根据username查询用户是否已存在
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",consumer.getUsername());
        Consumer consumer1 = consumerService.getOne(queryWrapper);
        if(consumer1!=null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名已存在");
            return jsonObject;
        }

        if(consumer.getPassword()==null||consumer.getPassword().equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.save(consumer);
        if(flag){ //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改前端用户
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateConsumer(Consumer consumer){
        JSONObject jsonObject = new JSONObject();
        if(consumer.getUsername()==null||consumer.getUsername().equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(consumer.getPassword()==null||consumer.getPassword().equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        boolean flag = consumerService.updateById(consumer);
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
     * 删除前端用户
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteConsumer(Integer id){
        boolean flag = consumerService.removeById(id);
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Consumer selectByPrimaryKey(Integer id){
        return consumerService.getById(id);
    }

    /**
     * 查询所有前端用户
     */
    @RequestMapping(value = "/allConsumer",method = RequestMethod.GET)
    public Object allConsumer(){
        return consumerService.list();
    }

    /**
     * 更新前端用户图片
     */
    @RequestMapping(value = "/updateConsumerPic",method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")
                +"img"+System.getProperty("file.separator")+"userPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/userPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.updateById(consumer);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avator",storeAvatorPath);
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

    /**
     * 前端用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //保存到前端用户的对象中
        Consumer consumerTemp = new Consumer();
        consumerTemp.setUsername(username);
        consumerTemp.setPassword(password);
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",consumerTemp.getUsername());
        queryWrapper.eq("password",consumerTemp.getPassword());
        Consumer consumer = consumerService.getOne(queryWrapper);
        if(consumerTemp!=null){   //验证成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            QueryWrapper<Consumer> queryWrapperTwo = new QueryWrapper<>();
            queryWrapperTwo.eq("username",consumer.getUsername());
            jsonObject.put("userMsg",consumerService.getOne(queryWrapperTwo));//登陆成功返回用户的信息
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }
}


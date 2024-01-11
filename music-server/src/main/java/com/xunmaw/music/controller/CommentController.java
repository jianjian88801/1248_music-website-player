package com.xunmaw.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.entity.Comment;
import com.xunmaw.music.service.CommentService;
import com.xunmaw.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addComment(Comment comment){
        JSONObject jsonObject = new JSONObject();
        System.out.println(comment);
        boolean flag = commentService.save(comment);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateComment(Comment comment){
        JSONObject jsonObject = new JSONObject();
        boolean flag = commentService.updateById(comment);
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
     * 删除评论
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteComment(Integer id){
        boolean flag = commentService.removeById(id);
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(Integer id){
        return commentService.getById(id);
    }

    /**
     * 查询所有评论
     */
    @RequestMapping(value = "/allComment",method = RequestMethod.GET)
    public Object allComment(){
        return commentService.list();
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @RequestMapping(value = "/commentOfSongId",method = RequestMethod.GET)
    public Object commentOfSongId(Integer songId){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",songId);
        return commentService.list(queryWrapper);
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @RequestMapping(value = "/commentOfSongListId",method = RequestMethod.GET)
    public Object commentOfSongListId(Integer songListId){//歌曲id
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return commentService.list(queryWrapper);
    }

    /**
     * 给某个评论点赞
     */
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Object like(Comment comment){
        JSONObject jsonObject = new JSONObject();
        boolean flag = commentService.updateById(comment);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"点赞失败");
        return jsonObject;
    }
}


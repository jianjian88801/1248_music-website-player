package com.xunmaw.music.service.impl;

import com.xunmaw.music.entity.Comment;
import com.xunmaw.music.dao.CommentDao;
import com.xunmaw.music.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

}

package com.xunmaw.music.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmaw.music.entity.Song;

import java.util.List;

/**
 * 歌曲(Song)表服务接口
 *
 * @author makejava
 * @since 2021-06-17 14:19:43
 */
public interface SongService extends IService<Song> {

    //添加歌曲
    Boolean insertSong(Song song);

    //根据歌手id查询歌曲列表
    List<Song> songOfSingerId(Integer id);
}

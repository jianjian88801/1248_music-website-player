package com.xunmaw.music;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunmaw.music.dao.SongDao;
import com.xunmaw.music.entity.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestSongDao {

    @Autowired
    private SongDao songDao;

    @Test
    public void testQuerySongById(){
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id",1);//查询
        List<Song> songs = songDao.selectList(queryWrapper);
        songs.forEach(song -> System.out.println(song));
    }
}

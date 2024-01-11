package com.xunmaw.music.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmaw.music.entity.Singer;
import com.xunmaw.music.utils.PageRequest;
import com.xunmaw.music.utils.PageUtils;

import java.util.List;

/**
 * 歌手(Singer)表服务接口
 *
 * @author makejava
 * @since 2021-06-10 18:25:23
 */
public interface SingerService extends IService<Singer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Singer queryById(Integer id);



    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Singer> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param singer 实例对象
     * @return 实例对象
     */
    boolean insert(Singer singer);

    /**
     * 修改数据
     *
     * @param singer 实例对象
     * @return 实例对象
     */
    boolean update(Singer singer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询所有歌手
     *
     * @return
     */
    List<Singer> queryAll();

    //通过name模糊查询
    List<Singer> singerOfName(String name);

    //通过sex模糊查询
    List<Singer> singerOfSex(Integer singerId);

    PageUtils selectSingerByPage(PageRequest pageRequest);

}

package com.xunmaw.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xunmaw.music.entity.Singer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 歌手(Singer)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-10 18:25:23
 */
public interface SingerDao extends BaseMapper<Singer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Singer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Singer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param singer 实例对象
     * @return 对象列表
     */
    List<Singer> queryAll(Singer singer);






    /**
     * 新增数据
     *
     * @param singer 实例对象
     * @return 影响行数
     */
    int insert(Singer singer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Singer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Singer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Singer> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Singer> entities);

    /**
     * 修改数据
     *
     * @param singer 实例对象
     * @return 影响行数
     */
    int update(Singer singer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询所有用户
     * @return 对象列表
     */
    List<Singer> findAll();

    /**
     * 通过name模糊查询
     * @param name
     * @return
     */
    List<Singer> singerOfName(String name);


    /**
     * 通过sex模糊查询
     * @param sex
     * @return
     */
    List<Singer> singerOfSex(Integer sex);
}


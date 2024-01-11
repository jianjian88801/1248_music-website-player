package com.xunmaw.music.dao;

import com.xunmaw.music.entity.Rank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 评价 Mapper 接口
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
public interface RankDao extends BaseMapper<Rank> {
    /**
     * 查总分
     */
    int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    int selectRankNum(Integer songListId);

    //添加评价
    int insert(Rank rank);
}

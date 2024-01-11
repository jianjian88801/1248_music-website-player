package com.xunmaw.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xunmaw.music.entity.Rank;

/**
 * <p>
 * 评价 服务类
 * </p>
 *
 * @author xunmaw
 * @since 2021-06-21
 */
public interface RankService extends IService<Rank> {

    boolean insertRank(Rank rank);
    /**
     * 查总分
     */
     int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
     int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     */
     int rankOfSongListId(Integer songListId);
}

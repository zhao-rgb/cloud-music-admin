package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.entity.Song;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
public interface SongService extends IService<Song> {
    /**
     * 查询所有歌曲
     * @return
     */
    List<Map<String, Object>> selectAll();

    /**
     * 模糊查
     * @return
     */
    List<Song> getSongBy(String filed);

    /**
     * 导出数据
     */
    void exportData();
}

package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.entity.Song;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SongMapper;
import com.soft1851.music.admin.service.SongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {
    @Resource
    private SongMapper songMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.select("song_name", "singer", "duration", "comment_count", "like_count", "create_time");
        List<Map<String, Object>> song = songMapper.selectMaps(wrapper);
        if(song != null){
            return song;
        }
        throw new CustomException("歌曲查询异常", ResultCode.DATABASE_ERROR);
    }

    @Override
    public List<Song> getSongBy(String field) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("singer", field)
                .or()
                .like("song_name", field)
                .or()
                .eq("song_id", field)
                .orderByDesc("play_count");
        return songMapper.selectList(wrapper);
    }
}

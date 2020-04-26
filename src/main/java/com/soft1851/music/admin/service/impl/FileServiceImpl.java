package com.soft1851.music.admin.service.impl;

import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.entity.Song;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.service.FileService;
import com.soft1851.music.admin.service.SongService;
import com.soft1851.music.admin.util.ExcelUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhao
 * @className FileServiceImpl
 * @Description TODO
 * @Date 2020/4/26
 * @Version 1.0
 **/
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private SongService songService;

    @Override
    public Map<String, String> exportSongList() {
        Map<String, String> map = new LinkedHashMap<>();
        String[] names = {"歌单Id", "歌单名称", "封面", "播放量", "类型", "歌曲数量", "创建时间"};
        String[] field = {"song_list_id", "song_list_name", "thumbnail", "plays_counts", "type", "song_count", "create_time"};
        int len = names.length;
        for (int i = 0; i < len; i++) {
            map.put(field[i], names[i]);
        }
        return map;
    }

    @Override
    public void importSong(File file) {
        List<Song> songs = ExcelUtils.importExcel(file);
        try {
            songService.saveBatch(songs);
        }catch (Exception e){
            throw new CustomException("歌曲批量导入异常", ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public Map<String, String> downloadSongModel() {
        Map<String, String> map = new LinkedHashMap<>();
        String[] names = {"歌名", "歌手", "时长", "封面图", "歌曲地址", "歌词"};
        String[] field = {"song_name", "singer", "duration", "thumbnail", "url", "lyric"};
        int len = names.length;
        for (int i = 0; i < len; i++) {
            map.put(field[i], names[i]);
        }
        return map;
    }
}

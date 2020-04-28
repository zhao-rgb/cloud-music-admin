package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.domain.entity.Song;
import com.soft1851.music.admin.service.SongService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Resource
    private SongService songService;

    @GetMapping("/list")
    public List<Map<String, Object>> selectAll() {
        return songService.selectAll();
    }

    @GetMapping("/select")
    public List<Song> getSongBy(@Param("field") String field) {
        return songService.getSongBy(field);
    }

    @GetMapping(value = "/export")
    public void exportData() {
        songService.exportData();
    }
}

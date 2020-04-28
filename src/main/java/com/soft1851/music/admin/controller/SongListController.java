package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.service.SongListService;
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
@RequestMapping("/songList")
public class SongListController {
    @Resource
    private SongListService songListService;

    @GetMapping("/list")
    public List<Map<String, Object>> selectAll() {
        return songListService.selectAll();
    }

    @GetMapping("/page")
    public List<SongList> getByPage(@Param("currentPage") int currentPage, @Param("size") int size) {
        return songListService.getByPage(currentPage, size);
    }

    @GetMapping("/type")
    public List<Map<String, Object>> getByType(){
        return songListService.getByType();
    }

    @GetMapping("/select")
    public List<SongList> blurSelectSongList(@Param("field") String field) {
        return songListService.blurSelect(field);
    }

    @GetMapping(value = "/export")
    public void export() {
        songListService.exportData();
    }
}

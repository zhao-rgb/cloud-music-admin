package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.domain.entity.Song;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SongMapper;
import com.soft1851.music.admin.service.SongService;
import com.soft1851.music.admin.util.ExcelConsumer;
import com.soft1851.music.admin.util.ExportDataAdapter;
import com.soft1851.music.admin.util.ThreadPool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
@Service
@Slf4j
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


    @SneakyThrows
    @Override
    public void exportData() {
        String excelPath = "D:\\picture\\song.xlsx";
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<Song> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(() -> produceExportData(exportDataAdapter, latch));
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(() -> new ExcelConsumer<>(Song.class, exportDataAdapter, sxssfWorkbook, latch, "歌曲数据").run());
        latch.await();
        //使用字节流写数据
        OutputStream outputStream = new FileOutputStream(excelPath);
        sxssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 生产者生产数据
     *
     * @param exportDataAdapter
     * @param latch
     */
    private void produceExportData(ExportDataAdapter<Song> exportDataAdapter, CountDownLatch latch) {
        List<Song> songs = songMapper.selectList(null);
        songs.forEach(exportDataAdapter::addData);
        log.info("数据生产完成");
        //数据生产结束
        latch.countDown();
    }
}

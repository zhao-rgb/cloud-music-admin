package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SongListMapper;
import com.soft1851.music.admin.service.SongListService;
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
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {
    @Resource
    private SongListMapper songListMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.select("song_list_id", "song_list_name", "thumbnail", "type", "song_count", "create_time", "play_counts")
                .orderByDesc("play_counts");
        List<Map<String, Object>> songLists = songListMapper.selectMaps(wrapper);
        if(songLists != null){
            return songLists;
        }
        throw new CustomException("歌单查询异常", ResultCode.DATABASE_ERROR);
    }

    @Override
    public List<SongList> getByPage(int current, int size) {
        Page<SongList> page = new Page<>(current, size);
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        IPage<SongList> iPage = songListMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    @Override
    public List<Map<String, Object>> getByType() {
//        Page<Map<String, Object>> page = new Page<>(1, 5);
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.select("type").groupBy("type").orderByDesc("play_counts");
        List<Map<String, Object>> maps = songListMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            if ("0".equals(map.get("type"))) {
                map.remove("type");
            }else {
                QueryWrapper<SongList> wrapper1 = new QueryWrapper<>();
                wrapper1.orderByDesc("play_counts").eq("type", map.get("type"));
                List<Map<String, Object>> songLists = songListMapper.selectMaps(wrapper1);
                map.put("child", songLists);
            }
        }
//        IPage<Map<String, Object>> iPage = songListMapper.selectMapsPage(page, wrapper);
        return maps;
    }

    @Override
    public List<SongList> blurSelect(String field) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("song_list_name", field)
                .or().like("type", field);
        return songListMapper.selectList(wrapper);
    }

    @SneakyThrows
    @Override
    public void exportData() {
        String excelPath = "D:\\picture\\songList.xlsx";
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<SongList> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(() -> produceExportData(exportDataAdapter, latch));
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(() -> new ExcelConsumer<>(SongList.class, exportDataAdapter, sxssfWorkbook, latch, "歌单数据").run());
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
    private void produceExportData(ExportDataAdapter<SongList> exportDataAdapter, CountDownLatch latch) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("song_count");
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        songLists.forEach(exportDataAdapter::addData);
        log.info("数据生产完成");
        //数据生产结束
        latch.countDown();
    }
}

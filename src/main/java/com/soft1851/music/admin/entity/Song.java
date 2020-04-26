package com.soft1851.music.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("song")
public class Song extends Model<Song> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌曲id
     */
    @TableId("song_id")
    private String songId;

    /**
     * 歌曲名称
     */
    @TableField("song_name")
    private String songName;

    /**
     * 排序id
     */
    @TableField("sort_id")
    private String sortId;

    /**
     * 歌手
     */
    @TableField("singer")
    private String singer;

    /**
     * 时长
     */
    @TableField("duration")
    private String duration;

    /**
     * 封面图
     */
    @TableField("thumbnail")
    private String thumbnail;

    /**
     * 歌曲地址
     */
    @TableField("url")
    private String url;

    /**
     * 歌词
     */
    @TableField("lyric")
    private String lyric;

    /**
     * 评论量
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 收藏量
     */
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 播放量
     */
    @TableField("play_count")
    private Integer playCount;

    /**
     * 删除标志
     */
    @TableField("delete_flag")
    private String deleteFlag;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.songId;
    }

}

package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.entity.SysAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {
    /**
     * 根据name查询管理员信息，包含其所有角色
     * @param name
     * @return
     */
    SysAdmin selectByName(@Param("name") String name);

}

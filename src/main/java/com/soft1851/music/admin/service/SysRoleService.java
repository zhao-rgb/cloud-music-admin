package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.entity.SysRole;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxl
 * @since 2020-04-21
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据id查询角色，包含其所有菜单
     * @param roleId
     * @return
     */
    Map selectRoleById(int roleId);
}

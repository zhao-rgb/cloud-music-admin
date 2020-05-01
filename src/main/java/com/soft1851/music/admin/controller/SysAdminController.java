package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@RequestMapping("/sysAdmin")
@Slf4j
@Validated
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 登录
     *
     * @return String
     */
    @PostMapping("/login")
    public Map login(@RequestBody @Valid LoginDto loginDto) {
        log.info(loginDto.toString());
        return sysAdminService.login(loginDto);
    }

    /**
     * 修改个人信息
     * @param sysAdmin
     * @return
     */
    @PutMapping("/update")
    public ResponseResult updateInfo(@RequestBody @Valid SysAdmin sysAdmin) {
        sysAdminService.updateSysAdmin(sysAdmin);
        return ResponseResult.success();
    }
}

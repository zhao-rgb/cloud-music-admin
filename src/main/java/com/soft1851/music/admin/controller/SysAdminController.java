package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.SysAdminDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{id}")
    public SysAdmin getSysAdmin(@PathVariable String id) {
        log.info(sysAdminService.getSysAdminById(id).toString());
        return sysAdminService.getSysAdminById(id);
    }

    @PostMapping("/profile")
    public ResponseResult updateInfo(@RequestBody SysAdminDto sysAdminDto) {
        return ResponseResult.success(sysAdminService.updateInfo(sysAdminDto));
    }

    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") String id) {
        String url = AliOssUtil.upload(multipartFile);
        SysAdminDto sysAdminDto = SysAdminDto.builder().id(id).avatar(url).build();
        sysAdminService.updateAvatar(sysAdminDto);
        return ResponseResult.success(url);
    }
}

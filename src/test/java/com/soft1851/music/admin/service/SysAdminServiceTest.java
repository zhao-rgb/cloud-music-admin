package com.soft1851.music.admin.service;

import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.SysAdminDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SysAdminServiceTest {

    @Resource
    private SysAdminService sysAdminService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder().name("music").password("123456").build();
        sysAdminService.login(loginDto);
    }

    @Test
    void updateAvatar() {
        SysAdminDto sysAdminDto = SysAdminDto.builder().id("DE35D7CC05AF96A21D7ADFC8651E6614").avatar("11.jpg").build();
        sysAdminService.updateAvatar(sysAdminDto);
    }
}
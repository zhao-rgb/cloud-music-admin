package com.soft1851.music.admin.service;

import com.soft1851.music.admin.dto.LoginDto;
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
}
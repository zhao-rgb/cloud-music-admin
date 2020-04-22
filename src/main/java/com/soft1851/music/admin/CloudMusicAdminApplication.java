package com.soft1851.music.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
//注册过滤器注解
@ServletComponentScan
@MapperScan("com.soft1851.music.admin.mapper")
public class CloudMusicAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMusicAdminApplication.class, args);
    }

}

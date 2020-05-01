package com.soft1851.music.admin.controller;

import com.soft1851.music.admin.annotation.ControllerWebLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhao
 * @className HelloController
 * @Description TODO
 * @Date 2020/4/30
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/")
@Slf4j
public class HelloController {
    /**
     * 采用自定义的WebLog日志注解，记录调用的接口方法名，是否需要持久化
     * @return string
     */
    @RequestMapping(value = "hello")
    @ControllerWebLog(name = "getHello", isSaved = true)
    public String getHello(String arg1, int arg2){
        log.info("控制层获得参数" + arg1);
        log.info("控制层获得参数" + arg2);
        //休眠，模拟接口耗时
        try{
            Thread.sleep(6000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return "hello spring boot";
    }

}

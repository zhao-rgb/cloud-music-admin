package com.soft1851.music.admin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.service.RedisService;
import com.soft1851.music.admin.util.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhao
 * @className LoginInterceptor
 * @Description 登录拦截器
 *  可以做下参数校验、验证码有效性等
 * @Date 2020/4/21
 * @Version 1.0
 **/
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;

    /**
     * 前置处理，拦截登录请求，校验参数、验证码等
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("进入登录拦截器");
        //此处调用工具类来取得请求体内容，防止流一次读取被关闭，控制层就取不到数据
        String body = null;
        try {
            body = HttpHelper.getBodyString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(body);
        //从redis取得指定用户名的验证码
        JSONObject jsonObject = JSONObject.parseObject(body);
        //判断以用户名作为key的数据是否还存在
        String name = jsonObject.getString("name");
        String verifyCode = jsonObject.getString("verifyCode");
        if (redisService.existsKey(name)) {
            log.info("验证码正确");
            //取得redis中的验证码
            String correctCode = redisService.getValue(name, String.class);
            //忽略大小写比对，成功则放行到controller调用登录接口
            if (correctCode.equalsIgnoreCase(verifyCode)) {
                log.info("登录拦截器放行");
                return true;
            } else {
                throw new CustomException("验证码错误", ResultCode.USER_VERIFY_CODE_ERROR);
            }
        } else {
            throw new CustomException("用户名错误或验证码失效", ResultCode.USER_INPUT_ERROR);
        }
    }
}
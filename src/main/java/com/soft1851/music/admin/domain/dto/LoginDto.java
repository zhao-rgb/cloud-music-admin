package com.soft1851.music.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zhao
 * @className LoginDto
 * @Description TODO
 * @Date 2020/4/21
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "名字不能为空")
    @Size(max = 20,message = "名字不能大于16位")
    private String name;

    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 20,message = "密码不能小于6位并且不能大于20位")
    private String password;

    @NotNull(message = "验证码不能为空")
    @Size(min = 4,message = "验证码格式不对")
    private String verifyCode;

}

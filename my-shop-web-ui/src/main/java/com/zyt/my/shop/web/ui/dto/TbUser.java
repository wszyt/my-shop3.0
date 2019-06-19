package com.zyt.my.shop.web.ui.dto;


import com.zyt.my.shop.commons.dto.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data

public class TbUser implements Serializable {
    private Long id;
    @Length(min = 1, max = 10, message = "用户名长度在1-10个字符之间")
    private String username;
    @Length(min = 1, max = 10, message = "密码长度在1-20个字符之间")
    private String password;
    private String password2;
    @Pattern (regexp = RegexpUtils.PHONE, message = "手机格式不正确")
    private String phone;
    @Pattern (regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
    @Length(min = 1, message = "请输入验证码")
    private String verification;
}

package com.zyt.my.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.commons.utils.EmailSendUtils;
import com.zyt.my.shop.commons.validator.BeanValidator;
import com.zyt.my.shop.web.ui.api.UsersApi;
import com.zyt.my.shop.web.ui.constant.SystemConstants;
import com.zyt.my.shop.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    private EmailSendUtils emailSendUtils;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {

        if (!tbUser.getPassword ().equals (tbUser.getPassword2 ())) {
            model.addAttribute ("baseResult", BaseResult.fail ("两次输入的密码不一致"));
            return "register";
        }

        //验证码验证
        if (!checkVerification (tbUser, request)) {
            model.addAttribute ("baseResult", BaseResult.fail ("验证码输入错误，请重新输入"));
            return "register";
        }

        String validator = BeanValidator.validator (tbUser);

        if (validator != null) {
            model.addAttribute ("baseResult", BaseResult.fail (validator));
            return "register";
        }


        TbUser user = UsersApi.register (tbUser);

        if (user == null) {
            model.addAttribute ("baseResult", BaseResult.fail ("用户名或者邮箱或电话已存在"));
            return "register";
        }

        else {
            emailSendUtils.send ("用户登录", String.format ("欢迎用户[%s] 成功注册 MyShop", user.getUsername ()), user.getEmail ());
            request.getSession ().setAttribute (SystemConstants.SESSION_USER_KEY, user);
            return "redirect:/index";
        }
    }

    private boolean checkVerification(TbUser tbUser, HttpServletRequest request) {
        String verification = (String) request.getSession ().getAttribute (Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals (verification, tbUser.getVerification ())) {
            return true;
        }
        return false;
    }

}

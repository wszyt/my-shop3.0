package com.zyt.my.shop.web.api.web.controller.v1;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.domain.TbUser;
import com.zyt.my.shop.web.api.service.TbUserService;
import com.zyt.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser) {
        TbUser user = tbUserService.login (tbUser);
        if(user == null) {
            return BaseResult.fail ("账号或者密码错误");
        }

        else {
            TbUserDTO dto = new TbUserDTO ();
            BeanUtils.copyProperties (user, dto);
            return BaseResult.success ("成功", dto);
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResult register(TbUser tbUser) {
        BaseResult baseResult = tbUserService.register (tbUser);
        if (baseResult.getStatus () == 200) {
            TbUserDTO dto = new TbUserDTO ();
            BeanUtils.copyProperties (tbUser, dto);
            return BaseResult.success ("注册成功", dto);
        }
        return BaseResult.fail ("失败");
    }
}

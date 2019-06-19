package com.zyt.my.shop.web.api.service.impl;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.domain.TbUser;
import com.zyt.my.shop.web.api.dao.TbUserDao;
import com.zyt.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login (tbUser);

        if(user != null) {
            //将明文密码加密
            String password = DigestUtils.md5DigestAsHex (tbUser.getPassword ().getBytes ());
            if (password.equals (user.getPassword ())) {
                return  user;
            }
        }

        return null;
    }

    @Override
    public BaseResult register(TbUser tbUser) {

        Integer integer = isExist (tbUser);

        if (isExist (tbUser) == 0) {
            tbUser.setPassword (DigestUtils.md5DigestAsHex (tbUser.getPassword ().getBytes ()));
            tbUser.setUpdated (new Date ());
            tbUser.setCreated (new Date ());
            tbUserDao.insert (tbUser);
            return BaseResult.success ("注册成功");
        }


        return BaseResult.fail ("用户名/邮箱/手机号已存在");
    }

    @Override
    public Integer isExist(TbUser tbUser) {
        return tbUserDao.isExist (tbUser);
    }
}

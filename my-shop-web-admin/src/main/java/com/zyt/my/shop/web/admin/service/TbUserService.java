package com.zyt.my.shop.web.admin.service;

import com.zyt.my.shop.commons.persistence.BaseService;
import com.zyt.my.shop.domain.TbUser;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);
}

package com.zyt.my.shop.web.api.service;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.domain.TbUser;

/**
 * 会员管理
 */
public interface TbUserService {
    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);

    /**
     * 注册
     * @param tbUser
     * @return
     */
    BaseResult register(TbUser tbUser);

    /**
     * 验证是否已被注册
     * @param tbUser
     * @return
     */
    Integer isExist(TbUser tbUser);
}

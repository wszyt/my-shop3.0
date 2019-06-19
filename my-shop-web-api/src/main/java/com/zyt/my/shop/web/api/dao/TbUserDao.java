package com.zyt.my.shop.web.api.dao;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 */

@Repository
public interface TbUserDao {
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
    void insert(TbUser tbUser);

    /**
     * 验证是否已被注册
     * @param tbUser
     * @return
     */
    Integer isExist(TbUser tbUser);

}

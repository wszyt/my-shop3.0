package com.zyt.my.shop.web.admin.service.Impl;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.commons.dto.PageInfo;
import com.zyt.my.shop.commons.dto.RegexpUtils;
import com.zyt.my.shop.commons.validator.BeanValidator;
import com.zyt.my.shop.domain.TbUser;
import com.zyt.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zyt.my.shop.web.admin.dao.TbUserDao;
import com.zyt.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

//    @Autowired
//    private TbUserDao tbUserDao;
//
//    @Override
//    public List<TbUser> selectAll() {
//        return tbUserDao.selectAll ();
//    }
//
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator (tbUser);

        //验证不通过
        if(validator != null) {
            return BaseResult.fail (validator);
        }

        //通过验证
        else {
            tbUser.setUpdated (new Date ());


            //新增用户
            if (tbUser.getId () == null) {
                //密码需要加密
                tbUser.setPassword (DigestUtils.md5DigestAsHex (tbUser.getPassword ().getBytes ()));
                tbUser.setCreated (new Date ());
                dao.insert (tbUser);
            }

            //编辑用户
            else {
                //密码需要加密
                tbUser.setPassword (DigestUtils.md5DigestAsHex (tbUser.getPassword ().getBytes ()));
                update (tbUser);
            }

            return BaseResult.success ("保存用户信息成功");
        }
    }

//    @Override
//    public void delete(Long id) {
//        tbUserDao.delete (id);
//    }
//
//    @Override
//    public TbUser getById(Long id) {
//        return tbUserDao.getById (id);
//    }
//
//    @Override
//    public void update(TbUser tbUser) {
//        tbUserDao.update (tbUser);
//    }
//
////    @Override
////    public List<TbUser> selectByUsername(String username) {
////        return tbUserDao.selectByUsername (username);
////    }
//
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail (email);
        if (tbUser != null) {
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex (password.getBytes ());

            //判断加密后的密码和数据库中存放的密码是否匹配，匹配则表示允许登录
            if (md5Password.equals (tbUser.getPassword ())) {
                return tbUser;
            }
        }
        return null;
    }
//
//
//
//    @Override
//    public void deleteMulti(String[] ids) {
//        tbUserDao.deleteMulti (ids);
//    }
//
//    @Override
//    public PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser) {
//        int count = tbUserDao.count (tbUser);
//
//        Map<String, Object> params = new HashMap<> ();
//        params.put ("start", start);
//        params.put ("length", length);
//        params.put ("tbUser", tbUser);
//
//        PageInfo<TbUser> pageInfo = new PageInfo<> ();
//        pageInfo.setDraw (draw);
//        pageInfo.setRecordsTotal (count);
//        pageInfo.setRecordsFiltered (count);
//        pageInfo.setData (tbUserDao.page (params));
//
//        return pageInfo;
//    }
//
//    @Override
//    public int count(TbUser tbUser) {
//        return tbUserDao.count (tbUser);
//    }


}

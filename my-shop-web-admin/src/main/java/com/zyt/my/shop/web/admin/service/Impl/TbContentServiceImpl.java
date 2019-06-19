package com.zyt.my.shop.web.admin.service.Impl;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.commons.validator.BeanValidator;
import com.zyt.my.shop.domain.TbContent;
import com.zyt.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zyt.my.shop.web.admin.dao.TbContentDao;
import com.zyt.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService{

//    @Autowired
//    private TbContentDao tbContentDao;
//
//    @Override
//    public List<TbContent> selectAll() {
//        return tbContentDao.selectAll ();
//    }
//
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator (tbContent);

        //验证不通过
        if (validator != null) {
            return BaseResult.fail (validator);
        }

        //验证通过
        else {
            tbContent.setUpdated (new Date ());


            //新增
            if (tbContent.getId () == null) {
                tbContent.setCreated (new Date ());
                dao.insert (tbContent);
            }

            //编辑用户
            else {
                update (tbContent);
            }

            return BaseResult.success ("保存内容信息成功");
        }
    }


//
//    @Override
//    public void delete(Long id) {
//        tbContentDao.delete (id);
//    }
//
//    @Override
//    public TbContent getById(Long id) {
//        return tbContentDao.getById (id);
//    }
//
//    @Override
//    public void update(TbContent tbContent) {
//        tbContentDao.update (tbContent);
//    }
//
//    @Override
//    public void deleteMulti(String[] ids) {
//        tbContentDao.deleteMulti (ids);
//    }
//
//    @Override
//    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
//        int count = tbContentDao.count (tbContent);
//
//        Map<String, Object> params = new HashMap<> ();
//        params.put ("start", start);
//        params.put ("length", length);
//        params.put ("tbContent", tbContent);
//
//        PageInfo<TbContent> pageInfo = new PageInfo<> ();
//        pageInfo.setDraw (draw);
//        pageInfo.setRecordsTotal (count);
//        pageInfo.setRecordsFiltered (count);
//        pageInfo.setData (tbContentDao.page (params));
//
//        return pageInfo;
//    }
//
//    @Override
//    public int count(TbContent tbContent) {
//        return tbContentDao.count (tbContent);
//    }

}

package com.zyt.my.shop.web.admin.service.Impl;

import com.zyt.my.shop.commons.dto.BaseResult;
import com.zyt.my.shop.commons.dto.PageInfo;
import com.zyt.my.shop.commons.validator.BeanValidator;
import com.zyt.my.shop.domain.TbContent;
import com.zyt.my.shop.domain.TbContentCategory;
import com.zyt.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.zyt.my.shop.web.admin.dao.TBContentCategoryDao;
import com.zyt.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentCategoryImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TBContentCategoryDao> implements TbContentCategoryService{

//    @Override
//    public List<TbContentCategory> selectByPid(Long pid) {
//        return tbContentCategoryDao.selectByPid (pid);
//    }
//
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator (entity);

        if (validator != null) {
            return BaseResult.fail (validator);
        }

        else {
            TbContentCategory parent = entity.getParent ();
            //如果没有选择父节点则默认设置为根目录
            if (parent == null || parent.getId () == null) {
                //0代表根目录
                parent.setId (0L);

            }

            entity.setUpdated (new Date ());

            //新增
            if (entity.getId () == null) {
                entity.setCreated (new Date ());
                entity.setIsParent (false);

                //判断当前新增的节点有没有父级节点
                if (parent.getId () != 0L) {
                    TbContentCategory currentCategoryParent = getById (parent.getId ());
                    if (currentCategoryParent != null) {
                        //为父级节点设置isParent为true
                        currentCategoryParent.setIsParent (true);
                        update (currentCategoryParent);
                    }
                }

                //父级节点为0表示为根目录
                else {
                    //根目录一定是父级目录
                    entity.setIsParent (true);
                }

                dao.insert (entity);
            }

            else {
                update (entity);
            }


            return BaseResult.success ("保存分类信息成功");
        }
    }
//
//    @Override
//    public void delete(Long id) {
//        tbContentCategoryDao.delete (id);
//    }
//
//    @Override
//    public TbContentCategory getById(Long id) {
//        return tbContentCategoryDao.getById (id);
//    }
//
////    /**
////     * 用户信息有效性验证
////     * @param tbContentCategory
////     */
////    private BaseResult checkTbContentCategory(TbContentCategory tbContentCategory) {
////        BaseResult baseResult = BaseResult.success ("保存成功");
////
////        //非空验证
////        if(tbContentCategory.getName () == null) {
////            baseResult = BaseResult.fail ("内容的所属分类不能为空，请重新输入");
////        }
////
////
////
////        return baseResult;
////    }
//
//    @Override
//    public void update(TbContentCategory tbContentCategory) {
//        tbContentCategoryDao.update (tbContentCategory);
//    }
}

package com.zyt.my.shop.web.admin.abstracts;

import com.zyt.my.shop.commons.dto.PageInfo;
import com.zyt.my.shop.commons.persistence.BaseDao;
import com.zyt.my.shop.commons.persistence.BaseEntity;
import com.zyt.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 全部信息
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll ();
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id){
        dao.delete (id);
    }

    /**
     * 根据id查找信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id){
        return dao.getById (id);
    }

    /**
     * 修改用户信息
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity){
        dao.update (entity);
    }


    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        dao.deleteMulti (ids);
    }

    /**
     * 查询总数
     * @return
     */
    @Override
    public int count(T entity){
        return dao.count (entity);
    }


    /**
     * 分页查询
     * @param
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count (entity);

        Map<String, Object> params = new HashMap<> ();
        params.put ("start", start);
        params.put ("length", length);
        params.put ("pageParams", entity);

        PageInfo<T> pageInfo = new PageInfo<> ();
        pageInfo.setDraw (draw);
        pageInfo.setRecordsTotal (count);
        pageInfo.setRecordsFiltered (count);
        pageInfo.setData (dao.page (params));

        return pageInfo;
    }
}

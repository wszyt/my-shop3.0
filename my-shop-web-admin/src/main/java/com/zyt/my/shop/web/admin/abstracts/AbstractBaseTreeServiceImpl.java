package com.zyt.my.shop.web.admin.abstracts;

import com.zyt.my.shop.commons.persistence.BaseEntity;
import com.zyt.my.shop.commons.persistence.BaseTreeDao;
import com.zyt.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

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
    public void delete(Long id) {
        dao.delete (id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById (id);
    }

    /**
     * 更新
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update (entity);
    }

    /**
     * 根据父级节点id查询
     * @param pid
     * @return
     */
    @Override
    public List<T> selectByPid(Long pid) {
        return dao.selectByPid (pid);
    }

}

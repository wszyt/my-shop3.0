package com.zyt.my.shop.commons.persistence;

import java.util.List;

/**
 * 通用的树形结构接口
 */
public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * 全部信息
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查找信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 修改用户信息
     * @param entity
     */
    void update(T entity);

    /**
     * 根据父级节点ID查询所有子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}

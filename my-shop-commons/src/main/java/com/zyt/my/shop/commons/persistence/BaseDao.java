package com.zyt.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 所有数据访问层的基类
 */
public interface BaseDao<T extends BaseEntity> {
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
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params 需要两个参数 start/记录数开始的位置 length/每页记录数
     * @return
     */
    List<T> page(Map<String, Object> params);

    /**
     * 查询总数
     * @return
     */
    int count(T entity);
}

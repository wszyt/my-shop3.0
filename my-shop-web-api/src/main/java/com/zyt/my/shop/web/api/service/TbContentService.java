package com.zyt.my.shop.web.api.service;

import com.zyt.my.shop.domain.TbContent;

import java.util.List;


public interface TbContentService {
    /**
     * 根据类别ID查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);

}

package com.hlbbxh.learnproduct.service;

import com.hlbbxh.learnproduct.entity.ProductCategory;

import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:38:39
 */
public interface ProductCategoryService {
    /**
     * 获取商品种类
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

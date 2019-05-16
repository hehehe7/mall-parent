package com.mall.malladmin.vo.product;

import lombok.Data;

import java.io.Serializable;

/**
 *  商品属性名属性值关联表
 */
@Data
public class ProductDetailVo implements Serializable {
    /**
     * 编号
     */
    private Integer detailId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 详情信息
     */
    private String describe;
}

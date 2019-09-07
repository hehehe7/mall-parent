package com.mall.malladmin.mapper.product;

import com.mall.malladmin.dto.product.ProductDto;
import com.mall.malladmin.dto.product.ProductPropertyDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductMapper {
    /**
     * 查询
     * @return
     */
    List<ProductDto> getList(@Param("dto") ProductDto dto);

    /**
     * 查询商品销售属性值
     * @param dto
     * @return
     */
    List<ProductPropertyDto> findPropertyIsSale(@Param("dto") ProductDto dto);
    /**
     * 查询商品非销售属性值
     * @param dto
     * @return
     */
    List<String> findPropertyNotSale(@Param("dto") ProductDto dto);

    /**
     * 逻辑删除
     * @param productId
     * @return
     */
    @Update("UPDATE mall_product a SET a.is_delete = '1' WHERE a.product_id = #{productId}")
    int updateIsDelete(@Param("productId") Integer productId);

    /**
     * 根据类目ID修改对应商品是否可用
     * @param typeId
     * @param isUsable
     * @return
     */
    int updateProductIsUsable(@Param("typeId") Integer typeId, @Param("isUsable")String isUsable);

}

package com.mall.dao.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.system.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单列表
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 获取列表
     * @param dto
     * @return
     */
    List<MenuDTO> getList(@Param("dto") MenuDTO dto);
    /**
     * 根据父节点获取列表
     * @param menuId
     * @return
     */
    List<MenuDTO> getListById(@Param("menuId") String menuId);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeDTO> getMenuTree(@Param("dto") MenuAuthorityDTO dto);
}

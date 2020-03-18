package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.MenuEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单信息service
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 新增
     * @param dto
     * @return
     */
    @Transactional
    MenuEntity add(MenuDTO dto);
    /**
     * 修改
     * @param dto
     * @return
     */
    @Transactional
    void update(MenuDTO dto, String id);
    /**
     * 获取
     * @param dto
     * @return
     */
    List<MenuEntity> getList(MenuDTO dto);
    /**
     * 获取
     * @param dto
     * @return
     */
    PageInfo<MenuDTO> getPage(MenuDTO dto);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeDTO> getMenuTree(MenuAuthorityDTO dto);

    /**
     * 根据父节点获取菜单列表
     * @param menuId
     * @return
     */
    List<MenuDTO> getListById(String menuId);
    /**
     *  根据ID查找
     * @param menuId
     * @return
     */
    MenuDTO findById(String menuId);
    /**
     * 删除（逻辑删除）
     * @param ids
     */
    @Transactional
    void deleteMenu(List<String> ids);

    /**
     * 是否隐藏
     * @param dto
     */
    void updateIsHidden(MenuDTO dto);

    /**
     * 获取按钮列表
     * @return
     */
    MenuDTO getButtonList(MenuDTO dto);
}

package com.mall.manage.controller.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.controller.system.utils.MenuUtil;
import com.mall.manage.model.param.system.MenuCreateParam;
import com.mall.manage.model.vo.system.MenuVO;
import com.mall.manage.service.system.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * 菜单
 */
@Api(value = "菜单", tags = "菜单")
@Slf4j
@RestController
@RequestMapping(value = "/menu")
public class MenuController extends GenericController {

    @Resource(name = "menuService")
    private MenuService menuService;

    @ApiOperation("根据上级ID获取下级列表")
    @GetMapping(value = "/sub-list/{parentId}")
    protected RestResult<List<MenuVO>> getSubListByParentId(@PathVariable String parentId) {
        List<MenuEntity> menuEntityList = menuService.list(Wrappers.<MenuEntity>lambdaQuery().eq(MenuEntity::getParentId, parentId));
        List<MenuVO> result = MenuUtil.getVOListEntity(menuEntityList);
        return RestResult.success(result);
    }

    @ApiOperation("菜单详情")
    @GetMapping(value = "/detail/{id}")
    protected RestResult<MenuVO> detail(@PathVariable String id) {
        MenuEntity menuEntity = menuService.getById(id);
        MenuVO result = new MenuVO();
        BeanUtils.copyProperties(menuEntity, result);
        return RestResult.success(result);
    }

    @ApiOperation("新建")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:CREATE') or hasRole('ADMIN')")
    @PutMapping(value = "/menu/create")
    protected RestResult<MenuEntity> createMenu(@RequestBody MenuCreateParam param) {
        MenuEntity result = menuService.addMenuAndButton(param);
        return RestResult.success(result);
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateMenu.do/{id}")
    protected RestResult updateMenu(@RequestBody MenuDTO dto, @PathVariable @NotBlank(message = "ID不能为空") String id) {
        if (StringUtils.isBlank(id)) {
            return RestResult.validateFailed("ID为空！");
        }
        return RestResult.success();
    }

    @ApiOperation("删除")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteMenu.do")
    protected RestResult deleteMenu(List<String> ids) {
        if (null == ids || ids.size() == 0) {
            return RestResult.validateFailed("id为空！");
        }
        return RestResult.success();
    }

    @ApiOperation("是否隐藏")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsHidden.do")
    protected RestResult updateIsHidden(@RequestBody MenuDTO dto) {
        menuService.updateIsHidden(dto);
        return RestResult.success();
    }


    @ApiOperation("获取菜单树")
    @GetMapping(value = "getMenuTree.do")
    protected RestResult getMenuTree(MenuAuthorityDTO dto) {
        List<TreeDTO> result = menuService.getMenuTree(dto);
        return RestResult.success(result);
    }

    @ApiOperation("获取按钮列表")
    @PostMapping(value = "getButtonList.do")
    protected RestResult getButtonList(MenuDTO dto) {
        return RestResult.success();
    }
}

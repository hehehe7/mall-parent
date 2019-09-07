package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.constant.CommonConstant;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductPropertyNameDto;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.mapper.product.ProductPropertyMapper;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyNameService")
public class ProductPropertyNameServiceImpl implements ProductPropertyNameService {

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private ProductPropertyMapper productPropertyMapper;

    @Override
    public CommonResultDto add(ProductPropertyNameEntity entity) {
        if(ProductPropertyNameEntity.IS_SALE.equals(entity.getIsSale())){
            List<ProductPropertyNameEntity>  propertyNameList = productPropertyNameRepository.findByTypeIdAndIsSale(entity.getTypeId(), ProductPropertyNameEntity.IS_SALE);
            if(null != propertyNameList && propertyNameList.size() >=3){
                return new CommonResultDto().validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        List<ProductPropertyNameEntity>  typeIdAndNameList = productPropertyNameRepository.findByTypeIdAndName(entity.getTypeId(), entity.getName());
        if(null != typeIdAndNameList && typeIdAndNameList.size() > 0 ){
            return new CommonResultDto().validateFailed("同个分类下属性名不能相同！");
        }
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        ProductPropertyNameEntity result = productPropertyNameRepository.save(entity);
        if(result == null){
            return new CommonResultDto().failed();
        }
        return new CommonResultDto().success();
    }

    @Override
    public Optional<ProductPropertyNameEntity> findById(Integer id) {
        return productPropertyNameRepository.findById(id);
    }

    @Override
    public void update(ProductPropertyNameDto dto) {
        ProductPropertyNameEntity entity = productPropertyNameRepository.findById(dto.getPropertyNameId()).get();
        BeanUtils.copyProperties(dto,entity);
        productPropertyNameRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyMapper.updateIsDelete(id);
    }

    @Override
    public void updateIsDeleteByTypeId(Integer typeId) {
        productPropertyNameRepository.updateIsDeleteByTypeId(typeId);
    }

    @Override
    public List<ProductPropertyNameEntity> findList(ProductPropertyNameEntity entity) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        List<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example);
        return result;
    }

    @Override
    public List<ProductPropertyNameEntity> findByTypeId(Integer typeId) {
        return productPropertyNameRepository.findByTypeId(typeId);
    }

    @Override
    public Page<ProductPropertyNameEntity> findPage(ProductPropertyNameEntity entity, Pageable page) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        Page<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example, page);
        return result;
    }

    @Override
    public CommonResultDto updateIsSale(ProductPropertyNameDto dto) {
        ProductPropertyNameEntity entity = productPropertyNameRepository.findById(dto.getPropertyNameId()).get();
        if(ProductPropertyNameEntity.IS_SALE.equals(dto.getIsSale())){
            List<ProductPropertyNameEntity>  propertyNameList = productPropertyNameRepository.findByTypeIdAndIsSale(entity.getTypeId(), ProductPropertyNameEntity.IS_SALE);
            if(null != propertyNameList && propertyNameList.size() >=3){
                return new CommonResultDto().validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        entity.setIsSale(dto.getIsSale());
        productPropertyNameRepository.save(entity);
        return new CommonResultDto().success();
    }

    @Override
    public CommonResultDto updateIsShow(ProductPropertyNameDto dto) {
        ProductPropertyNameEntity entity = productPropertyNameRepository.findById(dto.getPropertyNameId()).get();
        entity.setIsShow(dto.getIsShow());
        productPropertyNameRepository.save(entity);
        return new CommonResultDto().success();
    }

}

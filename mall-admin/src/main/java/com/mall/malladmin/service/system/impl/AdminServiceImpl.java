package com.mall.malladmin.service.system.impl;

import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.entity.system.AdminEntity;
import com.mall.malladmin.mapper.AdminMapper;
import com.mall.malladmin.repository.system.AdminRepository;
import com.mall.malladmin.service.system.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminEntity add(AdminEntity entity) {
        entity.setCreateTime(new Date());
        entity.setIsUsable(AdminEntity.IS_USABLE);
        return adminRepository.save(entity);
    }

    @Override
    public List<AdminEntity> getList(AdminDto dto) {
        List<AdminEntity>entitys = adminRepository.findAll((Specification<AdminEntity>) (root, query, criteriaBuilder)->{
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(dto.getRole())){
                list.add(criteriaBuilder.like(root.get("role").as(String.class), "%"+dto.getRole()+"%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        });
        return entitys;
    }

    @Override
    public Page<AdminEntity> getPage(AdminDto dto) {
        Sort sort = new Sort(Sort.Direction.ASC, "modifyTime");
        Pageable page = PageRequest.of(dto.getPageNum()-1, dto.getPageSize(), sort);
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(dto, entity);
        Example<AdminEntity> example = Example.of(entity);
        Page<AdminEntity> result = adminRepository.findAll(example, page);
        return result;
    }

    @Override
    public Optional<AdminEntity> findById(String userId) {
        return adminRepository.findById(userId);
    }

    @Override
    public AdminDto findByLoginId(String loginCode) {
        return adminMapper.findByLoginId(loginCode);
    }
}

package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallUser;
import com.mall.mallmbg.model.MallUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallUserMapper {
    int countByExample(MallUserExample example);

    int deleteByExample(MallUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(MallUser record);

    int insertSelective(MallUser record);

    List<MallUser> selectByExample(MallUserExample example);

    MallUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") MallUser record, @Param("example") MallUserExample example);

    int updateByExample(@Param("record") MallUser record, @Param("example") MallUserExample example);

    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);
}
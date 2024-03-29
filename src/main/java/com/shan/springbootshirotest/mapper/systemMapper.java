package com.shan.springbootshirotest.mapper;

import com.shan.springbootshirotest.pojo.system;
import com.shan.springbootshirotest.pojo.systemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface systemMapper {
    int countByExample(systemExample example);

    int deleteByExample(systemExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(system record);

    int insertSelective(system record);

    List<system> selectByExample(systemExample example);

    system selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") system record, @Param("example") systemExample example);

    int updateByExample(@Param("record") system record, @Param("example") systemExample example);

    int updateByPrimaryKeySelective(system record);

    int updateByPrimaryKey(system record);
}
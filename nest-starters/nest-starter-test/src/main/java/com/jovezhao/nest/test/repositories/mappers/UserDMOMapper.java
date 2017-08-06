package com.jovezhao.nest.test.repositories.mappers;


import com.jovezhao.nest.test.repositories.mappers.dmo.UserDMO;


public interface UserDMOMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDMO record);

    int insertSelective(UserDMO record);

    UserDMO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDMO record);

    int updateByPrimaryKey(UserDMO record);
}
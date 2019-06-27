package com.zp.chapter01.mapper;

import com.zp.chapter01.pojo.dataobject.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 需求：查询用户
     */


     List<UserDo> findAll();
}

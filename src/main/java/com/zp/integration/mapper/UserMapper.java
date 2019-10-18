package com.zp.integration.mapper;

import com.zp.integration.pojo.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 需求：查询用户
     */
    List<UserDO> findAll();

    /**
     * 动态条件查询总数目
     */
    Integer countByBaseCondition(Map<String, Object> map);

    /**
     * 动态条件查询(支持分页)
     * @param map
     * @return
     */
    List<UserDO> selectByBaseConditionPageable(Map<String, Object> map);
}

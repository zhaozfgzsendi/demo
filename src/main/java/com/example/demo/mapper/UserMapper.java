package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据token从user_info表中查询用户信息
     * @param token
     * @return
     */
    @Select("select name, account_id as accountId, token as token, gmt_create as gmtCreate, gmt_modified as gmtModified from user_info where token = #{token}")
    UserInfo findByToken(@Param("token") String token);

    /**
     * 入库用户信息
     */
    @Insert("insert into user_info (name, account_id, token, gmt_create, gmt_modified)" +
            " values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(UserInfo userInfo);
}

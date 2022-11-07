package com.li.mapper;

import com.li.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    List<User> selectPage(@Param("pageNum") Integer pageNum,
                          @Param("pageSize") Integer pageSize,
                          @Param("username") String username,
                          @Param("role") String role
                          );

    Integer selectCount(@Param("username") String username,
                        @Param("role") String role);

    User selectById(@Param("id") String id);

    Integer insertOne(User user);
    Integer updateOne(User user);
    Integer deleteById(@Param("id") String id);
}


package com.li.mapper;

import com.li.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {

    List<Blog> selectAll();

    List<Blog> selectPage(@Param("currentPage") Integer currentPage,
                          @Param("pageSize") Integer pageSize,
                          @Param("title") String title
    );

    Integer selectCount(@Param("title") String title);

    Blog selectById(String id);

    Integer insertOne(Blog Blog);
    Integer updateOne(Blog Blog);
    Integer deleteById(@Param("id") String id);
}





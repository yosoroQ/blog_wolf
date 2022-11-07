package com.li.mapper;

import com.li.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> selectAll();

    List<Category> selectPage(@Param("currPage") Integer currPage,
                          @Param("pageSize") Integer pageSize,
                          @Param("categoryName") String categoryName
    );

    Integer selectCount(@Param("categoryName") String categoryName);

    Category selectById(@Param("id") String id);

    Integer insertOne(Category Category);
    Integer updateOne(Category Category);
    Integer deleteById(@Param("id") String id);
}



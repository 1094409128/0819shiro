package com.aaa.zxz.shiro.mapper;

import com.aaa.zxz.shiro.entity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectCategoryAll();

    @Select("select id from category WHERE category_name =#{categoryName}")
    int selectByCategoryId(String categoryName);
}
package com.aaa.zxz.shiro.mapper;


import com.aaa.zxz.shiro.entity.Book;
import com.aaa.zxz.shiro.entity.vo.BookVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<BookVo> selectBookAll();

    @Select("select id FROM book WHERE book_name=#{bookName}")
    int selectBookId(@Param("bookName") String bookName);
}
package com.aaa.zxz.shiro.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.mapper
 * @Author: zxz
 * @CreateDate: 2019/8/28 8:37
 * @Version: 1.0
 */
public interface BookAndCategoryMapper {

    @Update("update bookandcategory SET category_id = #{categoryId} WHERE book_id=#{bookId} ")
    int updatecategoryId(@Param("categoryId") int categoryId,@Param("bookId") int bookId);

    @Delete("delete from bookandcategory where book_id=#{bookId} ")
    int delete(@Param("bookId")int bookId);

    @Insert("INSERT INTO bookandcategory(book_id,category_id) values(#{bookId},#{categoryId})")
    int insert(@Param("bookId") int bookId,@Param("categoryId") int categoryId);



}

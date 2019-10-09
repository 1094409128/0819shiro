package com.aaa.zxz.shiro.service;


import com.aaa.zxz.shiro.entity.Book;
import com.aaa.zxz.shiro.entity.vo.BookVo;
import com.aaa.zxz.shiro.mapper.BookAndCategoryMapper;
import com.aaa.zxz.shiro.mapper.BookMapper;
import com.aaa.zxz.shiro.utils.MapUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookAndCategoryMapper bookAndCategoryMapper;
    /**
     * 方法实现说明
     * @date      2019/8/27 21:38
     * @author     zxz
     * @method      所有图书的信息
     * @see        * @param null
     * @return    
     * @exception 
     */
    public Map<String,Object> selectBookAll(){
        List<BookVo> bookList = bookMapper.selectBookAll();
        //调用封装好的方法将结果存入map中
        Map<String, Object> map = MapUtlis.returnMap(bookList);
        return map;
    }

    /**
     * 方法实现说明
     * @date      2019/8/27 21:42
     * @author     zxz
     * @method      根据id查询图书信息，展示信息用
     * @see        * @param null
     * @return
     * @exception
     */
    public Map<String,Object> selectByBook(Integer id){
        Book book = bookMapper.selectByPrimaryKey(id);
        //调用封装好的方法将结果存入map中
        Map<String, Object> map = MapUtlis.returnMap(book);
        return map;
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 8:51
     * @author     zxz
     * @method      修改图书信息
     * @see        * @param null
     * @return
     * @exception
     */
    public int updateBook(Book book,int categoryId){
        //修改图书信息
        int i = bookMapper.updateByPrimaryKeySelective(book);
        if(i > 0){
            //修改图书成功
            //修改图书所对应的类别关联表
            int id = bookAndCategoryMapper.updatecategoryId(categoryId, book.getId());
        }
        return i;
    }

    public int deleteBook(int id){
        //删除图书信息
        int i = bookMapper.deleteByPrimaryKey(id);
        if (i > 0){
            //说明成功
            //成功后删除图书所对应的类别关联表
            int i1 = bookAndCategoryMapper.delete(id);
        }
        return i;
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 11:07
     * @author     zxz
     * @method      添加图书的service
     * @see        * @param null
     * @return
     * @exception
     */
    public int insert(Book book,int categoryId){
        //添加图书信息
        int i = bookMapper.insertSelective(book);
        //根据添加的图书名字得到id
        int id = bookMapper.selectBookId(book.getBookName());
        if (i > 0 ){
            //说明查询成功
            //添加图书和类别的关联表
            int insert = bookAndCategoryMapper.insert(id, categoryId);
        }
        return i ;
    }
}

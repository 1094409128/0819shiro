package com.aaa.zxz.shiro.controller;

import com.aaa.zxz.shiro.entity.Book;
import com.aaa.zxz.shiro.entity.vo.BookVo;
import com.aaa.zxz.shiro.service.BookServiceImpl;
import com.aaa.zxz.shiro.service.CategoryServiceImpl;
import com.aaa.zxz.shiro.utils.MapUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.controller
 * @Author: zxz
 * @CreateDate: 2019/8/27 21:03
 * @Version: 1.0
 */
@Controller
public class BookController {

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 方法实现说明
     * @date      2019/8/28 11:04
     * @author     zxz
     * @method      查询所有图书信息
     * @see        * @param null
     * @return    
     * @exception 
     */
    @RequestMapping("/bookPage")
    public String bookPage(ModelMap modelMap){
        Map<String, Object> bookMap = bookService.selectBookAll();
        List resultBook =(List) MapUtlis.getObject(bookMap);
        modelMap.addAttribute("resultBook",resultBook);
        return "book_page";
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 11:05
     * @author     zxz
     * @method      根据图书id查询图书信息
     * @see        * @param null
     * @return    
     * @exception 
     */
    @RequestMapping("/selectUpdateBook")
    public String selectUpdateBook(Integer id,ModelMap modelMap){
        Map<String, Object> bookMap = bookService.selectByBook(id);
        Book mapBook =(Book) MapUtlis.getObject(bookMap);
        Map<String, Object> categoryMap = categoryService.selectCategoryAll();
        List mapCategory = (List)MapUtlis.getObject(categoryMap);
        modelMap.addAttribute("mapBook",mapBook);
        modelMap.addAttribute("mapCategory",mapCategory);
        return "update";
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 11:05
     * @author     zxz
     * @method      修改图书信息
     * @see        * @param null
     * @return    
     * @exception 
     */
    @RequestMapping("/update")
    public String update(BookVo bookVo){
        Book book = new Book();
        book.setId(bookVo.getId());
        book.setBookName(bookVo.getBookName());
        book.setMoney(bookVo.getMoney());
        //得到类别的value值
        String id =bookVo.getCategoryName();
        int categoryId = Integer.parseInt(id);
        int i = bookService.updateBook(book,categoryId);
        return "redirect:bookPage";
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 11:06
     * @author     zxz
     * @method      删除图书信息
     * @see        * @param null
     * @return    
     * @exception 
     */
    @RequestMapping("/deletebook")
    public String delete(Integer id){
        int i = bookService.deleteBook(id);
            return "redirect:bookPage";
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 11:06
     * @author     zxz
     * @method      跳转添加页面
     * @see        * @param null
     * @return    
     * @exception 
     */
    @RequestMapping("/insert")
    public String insert(ModelMap modelMap){
        Map<String, Object> categoryMap = categoryService.selectCategoryAll();
        List mapCategory = (List)MapUtlis.getObject(categoryMap);
        modelMap.addAttribute("mapCategory",mapCategory);
        return "insert";
    }

    /**
     * 方法实现说明
     * @date      2019/8/28 11:06
     * @author     zxz
     * @method      添加图书信息
     * @see        * @param null
     * @return    
     * @exception 
     */
    @RequestMapping("/insertBook")
    public String insertBook(BookVo bookVo){
        Book book = new Book();
        book.setBookName(bookVo.getBookName());
        book.setMoney(bookVo.getMoney());
        String categoryName = bookVo.getCategoryName();
        int categoryId = Integer.parseInt(categoryName);
        System.out.println(categoryId+"::::::....................");
        int i = bookService.insert(book, categoryId);
        return "redirect:bookPage";
    }
}

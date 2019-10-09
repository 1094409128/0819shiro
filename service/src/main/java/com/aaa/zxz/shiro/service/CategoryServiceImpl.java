package com.aaa.zxz.shiro.service;

import com.aaa.zxz.shiro.entity.Category;
import com.aaa.zxz.shiro.mapper.CategoryMapper;
import com.aaa.zxz.shiro.utils.MapUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.service
 * @Author: zxz
 * @CreateDate: 2019/8/27 21:47
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 方法实现说明
     * @date      2019/8/27 21:50
     * @author     zxz
     * @method      查询所有的图书类别
     * @see        * @param null
     * @return    
     * @exception 
     */
    public Map<String,Object> selectCategoryAll(){
        List<Category> category = categoryMapper.selectCategoryAll();
        //调用封装好的方法将结果存入map中
        Map<String, Object> map = MapUtlis.returnMap(category);
        return map;
    }
}

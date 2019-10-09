package com.aaa.zxz.shiro.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.utils
 * @Author: zxz
 * @CreateDate: 2019/8/27 18:56
 * @Version: 1.0
 */
public  class MapUtlis {

    static Map<String, Object> map = new HashMap<String, Object>();
    /**
     * 方法实现说明
     * @date      2019/8/27 19:13
     * @author     zxz
     * @method      清除map
     * @see        * @param null
     * @return    
     * @exception 
     */
    public static void clear(Map<String,Object> map){
        if (!map.isEmpty()){
            map.clear();
        }
    }


    /**
     * @date      2019/8/28 18:28
     * @author     zxz
     * @method      数据库查询到数据返回的类型是object放入map
     * @see        * @param null
     * @return    
     * @exception 
     */
    public static Map<String,Object> returnMap(Object object){
        MapUtlis.clear(map);
        if (null!=object && !object.toString().equals("[]")){
            map.put("code",200);
            map.put("result", object);
        }else {
            map.put("code", 404);
        }
        return map;
    }


    /**
     * 方法实现说明
     * @date      2019/8/27 22:58
     * @author     zxz
     * @method      取出map中的数据为Object
     * @see        * @param null
     * @return    
     * @exception 
     */
    public static Object getObject(Map<String,Object> map){
        Object object =null;
        if(200 == (Integer) map.get("code")){
             object = map.get("result");
        }
        return object;
    }
}

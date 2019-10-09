package com.aaa.zxz.shiro.service;

import com.aaa.zxz.shiro.entity.User;
import com.aaa.zxz.shiro.mapper.PermissionMapper;
import com.aaa.zxz.shiro.mapper.RoleMapper;
import com.aaa.zxz.shiro.mapper.UserMapper;
import com.aaa.zxz.shiro.utils.MapUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.service
 * @ClassName: UserService
 * @Author: zxz
 * @CreateDate: 2019/8/19 10:48
 * @Version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 方法实现说明
     * @date      2019/8/19 10:51
     * @author     zxz
     * @method      根据user的用户名查询所有信息
     * @see        * @param null
     * @return    map类型
     * @exception
     */
    public Map<String,Object> selectUserByUsername(String username){
        User user = userMapper.selectUsername(username);
        Map<String, Object> map = MapUtlis.returnMap(user);
        return map;
    }

    /**
     * 方法实现说明
     * @date      2019/8/27 19:40
     * @author     zxz
     * @method      根据用户名查询用户所拥有的角色
     * @see        * @param null
     * @return    
     * @exception 
     */
    public Map<String,Object> selectByrole(String username){
        List<String> roleList = roleMapper.selectByRole(username);
        //调用封装好的方法将结果存入map中
        Map<String, Object> map = MapUtlis.returnMap(roleList);
        return map;
    }

    /**
     * 方法实现说明
     * @date      2019/8/27 21:38
     * @author     zxz
     * @method      查询权限
     * @see        * @param null
     * @return
     * @exception
     */
    public Map<String,Object> selectByPermission(){
        List<String> permissionMapperList = permissionMapper.selectPermission();
        //调用封装好的方法将结果存入map中
        Map<String, Object> map = MapUtlis.returnMap(permissionMapperList);
        return map;
    }
}

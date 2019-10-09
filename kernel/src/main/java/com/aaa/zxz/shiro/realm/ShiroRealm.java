package com.aaa.zxz.shiro.realm;

import com.aaa.zxz.shiro.entity.User;
import com.aaa.zxz.shiro.service.UserService;
import com.aaa.zxz.shiro.utils.MapUtlis;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * 方法实现说明
 * @date      2019/8/19 10:33
 * @author     zxz
 * @method      
 * @see        * @param null
 * @return    
 * @exception 
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 方法实现说明
     * @date      2019/8/19 11:16
     * @author     zxz
     * @method      认证方法
     * @see        * @param took
     * @return    org.apache.shiro.authc.AuthenticationInfo
     * @exception AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken took) throws AuthenticationException {
        // 获取用户名信息(通过token获取)
        String username = took.getPrincipal().toString();
        //通过获取到的用户名去数据库查找用户名是否存在
        Map<String, Object> map = userService.selectUserByUsername(username);
        //判断用户是否存在
        if(200 != (Integer) map.get("code")){
            /*
            没有数据  抛出"用户不存在"异常
             */
            throw new UnknownAccountException("用户名不存在");
        }
        // 说明从数据库中查询到数据了 从map中取出user对象
        User user = (User) map.get("result");
        /**
         * SimpleAuthenticationInfo对象
         *      第一个参数:
         *          !!!!!!!!!!用户名/用户对象 username/user
         *      第二个参数:
         *          密码  password
         *      第三个参数:
         *          盐值  salt
         *      第四个参数:
         *          当前realm名字 this.getName()
         */
        // 密码匹配
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(), ByteSource.Util.bytes(user.getUsername()),this.getName());
       //SecurityUtils.getSubject()方法得到session
        System.out.println( ByteSource.Util.bytes(user.getUsername())+"加密后的研制是");
        Session session = SecurityUtils.getSubject().getSession();
        // 需要把密码置空
        user.setPassword("null");
        // 把认证成功的用户存入到session中
        session.setAttribute("user", user);
        return info;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username =(String) principalCollection.getPrimaryPrincipal();
        //调用service方法得到用户所拥有的角色
        Map<String, Object> map = userService.selectByrole(username);
        //调用封装好的getObject方法取出查询结果
        List roleList =(List) MapUtlis.getObject(map);
        //将角色信息添加到info中
        info.addRoles(roleList);
        //得到用户所拥有的权限
        Map<String, Object> permissionmMap = userService.selectByPermission();
        //调用封装好的getObject方法取出查询结果
        List permissionList =(List) MapUtlis.getObject(permissionmMap);
        //将权限放入info中
        info.addStringPermissions(permissionList);
        return info;
    }


}

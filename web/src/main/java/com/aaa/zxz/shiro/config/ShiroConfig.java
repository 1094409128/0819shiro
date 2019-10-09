package com.aaa.zxz.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.aaa.zxz.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.config
 * @ClassName: ShiroConfig
 * @Author: zxz
 * @CreateDate: 2019/8/19 10:57
 * @Version: 1.0
 */

@SpringBootApplication
public class ShiroConfig {
    /**
     * 方法实现说明
     * @date      2019/8/19 10:59
     * @author     zxz
     * @method      LifecycleBeanPostProcessor:表示把shiro的生命周期交给spring进行托管
     * 相当于在application.xml配置文件中配置<bean id="lifecycleBeanPostProcessor" class="LifecycleBeanPostProcessor.class"></bean>信息
     * @see        * @param null
     * @return org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @exception null
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor processor = new LifecycleBeanPostProcessor();
        return processor;
    }

    /**
     * 方法实现说明
     * @date      2019/8/19 11:02
     * @author     zxz
     * @method
     *      密码加密
     *      setHashAlgorithmName:根据名称设置密码的加密方式
     *      setHashIterations:设置的加密次数
     *      setStoredCredentialsHexEncoded:把用户所提交的密码转换成16进制
     * @see        * @param null
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     * @exception null
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    

    /**
     * 方法实现说明
     * @date      2019/8/19 11:35
     * @author     zxz
     * @method      配置了shiroRealm
     *              @DependsOn:
     *              DependsOn:翻译过来就是依赖于xxxx
     *              所以最终所有的Realm都必须要依赖于shiro的生命周期
     * @see        * @param 
     * @return    com.aaa.zxz.shiro.realm.ShiroRealm
     * @exception
     */
    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        // 在shiro的认证阶段中，配置了密码的加密
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        // 在开发阶段，不需要缓存
        // shiroRealm.setCacheManager(ehCacheManager());
        return shiroRealm;
    }
    

    /**
     * 方法实现说明
     * @date      2019/8/19 13:54
     * @author     zxz
     * @method      ehCacheManager
     *  配置了shiro所必须使用的ehcache的缓存
     * @see        * @param 
     * @return    org.apache.shiro.cache.ehcache.EhCacheManager
     * @exception
     */
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        return new EhCacheManager();
    }

   

    /**
     * 方法实现说明
     * @date      2019/8/19 13:57
     * @author     zxz
     * @method      securityManager
     *              创建SecurityManager对象
     *              相当于在applicaiton.xml配置文件中
     *              <bean id="securityManager" class="xxxxxx">
     *                引入myRealm
     *             </bean>
     * @see        * @param 
     * @return    org.apache.shiro.web.mgt.DefaultWebSecurityManager
     * @exception
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        //securityManager.setSessionManager(ehCacheManager());
        return securityManager;
    }


    /**
     * 方法实现说明
     * @date      2019/8/19 14:04
     * @author     zxz
     * @method      shiroFilterFactoryBean
     * @see        * @param
     * @return    org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @exception
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        /**
         *  1.logout:退出当前所登录用户
         *  2.anon:匿名可以访问(不进行认证可以直接访问的地址)
         *  3.authc:需要认证后所访问@RequestMapping("/xxxx")
         *  4.*和**的区别:
         *      *:com.aaa.lee.shiro
         *          -- realm :com.aaa.lee.shiro.realm/*只会代表当前目录的子目录(一级)
         *              -- myrealm
         *                  -- myAuthentication(认证)
         *      **:
         *          当前目录下的所有目录都进行匹配
         *  5.setLoginUrl():登录的路径
         *  6.setSuccessUrl():登录成功后所要跳转的路径
         *  7.setUnauthorizedUrl:如果权限的时候所需要跳转的路径
         *  8./static/** = user:所有认证成功后的用户可以访问的路径
         *  9./public/** = role["admin"]：当拥有admin角色的时候可以访问的路径
         *      role["admin","user","book_manager"]：这三个同时满足的时候才可以访问
         *  10.permission
         */
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/**", "authc");
        filter.setLoginUrl("/login");
        filter.setSuccessUrl("/index");
        filter.setUnauthorizedUrl("/404");
        filter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filter;
    }



    /**
     * 方法实现说明
     * @date      2019/8/19 14:08
     * @author     zxz
     * @method      defaultAdvisorAutoProxyCreator
     *
     *       @ConditionalOnMissingBean:条件注解
     *       当找不到某一个bean的时候才会被加载
     *      在springboot的源码中拥有DefaultAdvisorAutoProxyCreator bean的配置
     *      在自己的配置类中如果再次配置DefaultAdvisorAutoProxyCreator bean，则bean冲突报错！
     *      当无法加载springboot所自带的bean的时候，自己配置bean信息才会被加载
     *      创建一个shiro代理构建器(java自带的，还有cglib)
     *      通过动态代理创建出shiro的代理对象，再把这个对象交给spring进行托管
     * @see        * @param
     * @return    org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @exception
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    /**
     * 方法实现说明
     * @date      2019/8/19 14:11
     * @author     zxz
     * @method      authorizationAttributeSourceAdvisor
     *      授权源适配器
     *      源数据
     * @see        * @param
     * @return    org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @exception
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager());
        return sourceAdvisor;
    }


    /**
     * 方法实现说明
     * @date      2019/8/19 14:13
     * @author     zxz
     * @method      shiroDialect
     *          thymeleaf所支持shiro的标签
     * @see        * @param
     * @return    at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     * @exception
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}

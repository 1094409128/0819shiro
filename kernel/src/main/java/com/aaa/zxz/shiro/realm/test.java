package com.aaa.zxz.shiro.realm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.realm
 * @Author: zxz
 * @CreateDate: 2019/8/28 11:16
 * @Version: 1.0
 */
public class test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(111);
        String s = list.toString();
        System.out.println(!s .equals("[]"));
    }
}

package com.aaa.zxz.shiro.entity.vo;

import java.io.Serializable;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.entity.vo
 * @Author: zxz
 * @CreateDate: 2019/8/27 20:53
 * @Version: 1.0
 */
public class BookVo implements Serializable {
    private Integer id;
    private String bookName;
    private Double money;
    private Integer categoryId;
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "BookVo{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", money=" + money +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

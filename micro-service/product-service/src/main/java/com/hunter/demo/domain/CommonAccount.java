package com.hunter.demo.domain;

import java.io.Serializable;

/**
 * 账户(CommonAccount)实体类
 *
 * @author makejava
 * @since 2021-08-05 10:37:48
 */
public class CommonAccount implements Serializable {
    private static final long serialVersionUID = 338574873048504579L;
    
    private Long id;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 币种ID
    */
    private Object coinId;
    /**
    * 账户类型:币币账户;OTC账户;钱包账户
    */
    private String type;
    /**
    * 总余额
    */
    private Object total;
    /**
    * 可用余额
    */
    private Object balance;
    /**
    * 用户冻结
    */
    private Object userFreeze;
    /**
    * 系统冻结
    */
    private Object sysFreeze;
    /**
    * 创建时间
    */
    private Object createTime;
    /**
    * 创建人id
    */
    private Long createBy;
    /**
    * 更新时间
    */
    private Object updateTime;
    /**
    * 修改人id
    */
    private Long updateBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Object getCoinId() {
        return coinId;
    }

    public void setCoinId(Object coinId) {
        this.coinId = coinId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public Object getUserFreeze() {
        return userFreeze;
    }

    public void setUserFreeze(Object userFreeze) {
        this.userFreeze = userFreeze;
    }

    public Object getSysFreeze() {
        return sysFreeze;
    }

    public void setSysFreeze(Object sysFreeze) {
        this.sysFreeze = sysFreeze;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

}
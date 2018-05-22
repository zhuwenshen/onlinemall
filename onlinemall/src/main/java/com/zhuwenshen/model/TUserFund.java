package com.zhuwenshen.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_user_fund")
public class TUserFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 资金
     */
    private BigDecimal fund;

    /**
     * 版本
     */
    private Integer version;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取资金
     *
     * @return fund - 资金
     */
    public BigDecimal getFund() {
        return fund;
    }

    /**
     * 设置资金
     *
     * @param fund 资金
     */
    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
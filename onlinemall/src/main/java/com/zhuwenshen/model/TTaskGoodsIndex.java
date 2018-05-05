package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_task_goods_index")
public class TTaskGoodsIndex {
    /**
     * goodsId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 最近统计时间
     */
    @Column(name = "latest_time")
    private Date latestTime;

    /**
     * 获取goodsId
     *
     * @return id - goodsId
     */
    public String getId() {
        return id;
    }

    /**
     * 设置goodsId
     *
     * @param id goodsId
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取最近统计时间
     *
     * @return latest_time - 最近统计时间
     */
    public Date getLatestTime() {
        return latestTime;
    }

    /**
     * 设置最近统计时间
     *
     * @param latestTime 最近统计时间
     */
    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }
}
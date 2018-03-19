package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_chat")
public class TChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 发送方id
     */
    @Column(name = "sender_id")
    private String senderId;

    /**
     * 接收方id
     */
    @Column(name = "receiver_id")
    private String receiverId;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 图片url
     */
    @Column(name = "content_url")
    private String contentUrl;

    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 接收方是否查看
     */
    private Boolean checked;

    /**
     * 发送方是否删除
     */
    @Column(name = "sender_deleted")
    private Boolean senderDeleted;

    /**
     * 接收方是否删除
     */
    @Column(name = "receiver_deleted")
    private Boolean receiverDeleted;

    /**
     * 是否删除；0否 1是
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人id
     */
    @Column(name = "create_userid")
    private String createUserid;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新人id
     */
    @Column(name = "update_userid")
    private String updateUserid;

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
     * 获取发送方id
     *
     * @return sender_id - 发送方id
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * 设置发送方id
     *
     * @param senderId 发送方id
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    /**
     * 获取接收方id
     *
     * @return receiver_id - 接收方id
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * 设置接收方id
     *
     * @param receiverId 接收方id
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    /**
     * 获取信息内容
     *
     * @return content - 信息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置信息内容
     *
     * @param content 信息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取图片url
     *
     * @return content_url - 图片url
     */
    public String getContentUrl() {
        return contentUrl;
    }

    /**
     * 设置图片url
     *
     * @param contentUrl 图片url
     */
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取接收方是否查看
     *
     * @return checked - 接收方是否查看
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * 设置接收方是否查看
     *
     * @param checked 接收方是否查看
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * 获取发送方是否删除
     *
     * @return sender_deleted - 发送方是否删除
     */
    public Boolean getSenderDeleted() {
        return senderDeleted;
    }

    /**
     * 设置发送方是否删除
     *
     * @param senderDeleted 发送方是否删除
     */
    public void setSenderDeleted(Boolean senderDeleted) {
        this.senderDeleted = senderDeleted;
    }

    /**
     * 获取接收方是否删除
     *
     * @return receiver_deleted - 接收方是否删除
     */
    public Boolean getReceiverDeleted() {
        return receiverDeleted;
    }

    /**
     * 设置接收方是否删除
     *
     * @param receiverDeleted 接收方是否删除
     */
    public void setReceiverDeleted(Boolean receiverDeleted) {
        this.receiverDeleted = receiverDeleted;
    }

    /**
     * 获取是否删除；0否 1是
     *
     * @return deleted - 是否删除；0否 1是
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除；0否 1是
     *
     * @param deleted 是否删除；0否 1是
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人id
     *
     * @return create_userid - 创建人id
     */
    public String getCreateUserid() {
        return createUserid;
    }

    /**
     * 设置创建人id
     *
     * @param createUserid 创建人id
     */
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid == null ? null : createUserid.trim();
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新人id
     *
     * @return update_userid - 更新人id
     */
    public String getUpdateUserid() {
        return updateUserid;
    }

    /**
     * 设置更新人id
     *
     * @param updateUserid 更新人id
     */
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid == null ? null : updateUserid.trim();
    }
}
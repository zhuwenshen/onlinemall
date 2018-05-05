package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_identified_infor_of_merchant")
public class TIdentifiedInforOfMerchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 商家id
     */
    @Column(name = "merchant_information_id")
    private String merchantInformationId;

    /**
     * 认证流程;1用户申请 2管理员审批 3通过 4失败
     */
    @Column(name = "certification_workflow")
    private Integer certificationWorkflow;

    /**
     * 申请时间
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 审批结果类型;0未审核1通过 2失败
     */
    @Column(name = "approval_type")
    private Integer approvalType;

    /**
     * 审批结果;对审批的结果描述
     */
    @Column(name = "approval_result")
    private String approvalResult;

    /**
     * 审批时间
     */
    @Column(name = "approval_time")
    private Date approvalTime;

    /**
     * 备注
     */
    private String remake;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取商家id
     *
     * @return merchant_information_id - 商家id
     */
    public String getMerchantInformationId() {
        return merchantInformationId;
    }

    /**
     * 设置商家id
     *
     * @param merchantInformationId 商家id
     */
    public void setMerchantInformationId(String merchantInformationId) {
        this.merchantInformationId = merchantInformationId == null ? null : merchantInformationId.trim();
    }

    /**
     * 获取认证流程;1用户申请 2管理员审批 3通过 4失败
     *
     * @return certification_workflow - 认证流程;1用户申请 2管理员审批 3通过 4失败
     */
    public Integer getCertificationWorkflow() {
        return certificationWorkflow;
    }

    /**
     * 设置认证流程;1用户申请 2管理员审批 3通过 4失败
     *
     * @param certificationWorkflow 认证流程;1用户申请 2管理员审批 3通过 4失败
     */
    public void setCertificationWorkflow(Integer certificationWorkflow) {
        this.certificationWorkflow = certificationWorkflow;
    }

    /**
     * 获取申请时间
     *
     * @return apply_time - 申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请时间
     *
     * @param applyTime 申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取审批结果类型;0未审核1通过 2失败
     *
     * @return approval_type - 审批结果类型;0未审核1通过 2失败
     */
    public Integer getApprovalType() {
        return approvalType;
    }

    /**
     * 设置审批结果类型;0未审核1通过 2失败
     *
     * @param approvalType 审批结果类型;0未审核1通过 2失败
     */
    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }

    /**
     * 获取审批结果;对审批的结果描述
     *
     * @return approval_result - 审批结果;对审批的结果描述
     */
    public String getApprovalResult() {
        return approvalResult;
    }

    /**
     * 设置审批结果;对审批的结果描述
     *
     * @param approvalResult 审批结果;对审批的结果描述
     */
    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult == null ? null : approvalResult.trim();
    }

    /**
     * 获取审批时间
     *
     * @return approval_time - 审批时间
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 设置审批时间
     *
     * @param approvalTime 审批时间
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * 获取备注
     *
     * @return remake - 备注
     */
    public String getRemake() {
        return remake;
    }

    /**
     * 设置备注
     *
     * @param remake 备注
     */
    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
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
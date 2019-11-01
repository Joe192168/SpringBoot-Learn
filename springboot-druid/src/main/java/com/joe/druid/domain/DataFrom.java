package com.joe.druid.domain;

import java.util.List;

/**
 * @Program: geometry-bi
 * @Description: TODO 表单实体
 * @Author: xiaoqiaohui
 * @Create: 2019/10/31 11:52
 * @Version: 1.0.0
 */
public class DataFrom {

    private String treeId;
    private String type;
    private String name;
    private String pId;
    private String wsId;
    private String owner;
    private String ownerName;
    private String modifyUser;
    private String auth3rdFlag;
    private String version;
    private String gmtCreate;
    private String gmtModified;
    private String workspaceId;
    private String permissionList;
    private String subType;
    private String authLevel;
    private String description;
    private String catalogName;
    private String aimDirId;
    private String formId;
    private String formName;
    //物理表名称
    private String formNameEn;
    private String formBaseConfig;
    //窗体布局
    private String formLayoutConfig;
    private String objectId;
    private String objectType;
    private String ruleId;
    private String creator;
    private List<Component> components;

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getAuth3rdFlag() {
        return auth3rdFlag;
    }

    public void setAuth3rdFlag(String auth3rdFlag) {
        this.auth3rdFlag = auth3rdFlag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(String permissionList) {
        this.permissionList = permissionList;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getAimDirId() {
        return aimDirId;
    }

    public void setAimDirId(String aimDirId) {
        this.aimDirId = aimDirId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormNameEn() {
        return formNameEn;
    }

    public void setFormNameEn(String formNameEn) {
        this.formNameEn = formNameEn;
    }

    public String getFormBaseConfig() {
        return formBaseConfig;
    }

    public void setFormBaseConfig(String formBaseConfig) {
        this.formBaseConfig = formBaseConfig;
    }

    public String getFormLayoutConfig() {
        return formLayoutConfig;
    }

    public void setFormLayoutConfig(String formLayoutConfig) {
        this.formLayoutConfig = formLayoutConfig;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "DataFrom{" +
                "treeId='" + treeId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", pId='" + pId + '\'' +
                ", wsId='" + wsId + '\'' +
                ", owner='" + owner + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", auth3rdFlag='" + auth3rdFlag + '\'' +
                ", version='" + version + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ", workspaceId='" + workspaceId + '\'' +
                ", permissionList='" + permissionList + '\'' +
                ", subType='" + subType + '\'' +
                ", authLevel='" + authLevel + '\'' +
                ", description='" + description + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", aimDirId='" + aimDirId + '\'' +
                ", formId='" + formId + '\'' +
                ", formName='" + formName + '\'' +
                ", formNameEn='" + formNameEn + '\'' +
                ", formBaseConfig='" + formBaseConfig + '\'' +
                ", formLayoutConfig='" + formLayoutConfig + '\'' +
                ", objectId='" + objectId + '\'' +
                ", objectType='" + objectType + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", creator='" + creator + '\'' +
                ", components=" + components +
                '}';
    }
}

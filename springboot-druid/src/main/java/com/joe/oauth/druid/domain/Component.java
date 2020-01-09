package com.joe.oauth.druid.domain;

/**
 * @Program: geometry-bi
 * @Description: TODO 表单组成部分
 * @Author: xiaoqiaohui
 * @Create: 2019/10/31 11:35
 * @Version: 1.0.0
 */
public class Component {

    private String componentId;
    private String componentType;
    private String componentOrder;
    private String delete;
    private String componentConfig;
    private String props;
    private String label;
    private String componentNameEn;
    private String columnPath;
    private String defaultValue;
    private String formId;
    private String ruleId;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentOrder() {
        return componentOrder;
    }

    public void setComponentOrder(String componentOrder) {
        this.componentOrder = componentOrder;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getComponentConfig() {
        return componentConfig;
    }

    public void setComponentConfig(String componentConfig) {
        this.componentConfig = componentConfig;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getComponentNameEn() {
        return componentNameEn;
    }

    public void setComponentNameEn(String componentNameEn) {
        this.componentNameEn = componentNameEn;
    }

    public String getColumnPath() {
        return columnPath;
    }

    public void setColumnPath(String columnPath) {
        this.columnPath = columnPath;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return "Component{" +
                "componentId='" + componentId + '\'' +
                ", componentType='" + componentType + '\'' +
                ", componentOrder='" + componentOrder + '\'' +
                ", delete='" + delete + '\'' +
                ", componentConfig='" + componentConfig + '\'' +
                ", props='" + props + '\'' +
                ", label='" + label + '\'' +
                ", componentNameEn='" + componentNameEn + '\'' +
                ", columnPath='" + columnPath + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", formId='" + formId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                '}';
    }
}

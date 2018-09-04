package com.tools.sqlword.bean;

public class columnBean {

    //列名
    private String columnName;

    //字段类型
    private String coulumType;

    //默认填写
    private String coulumDefault;

    //字段注释
    private String coulumComment;

    //表名
    private String tableName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCoulumType() {
        return coulumType;
    }

    public void setCoulumType(String coulumType) {
        this.coulumType = coulumType;
    }

    public String getCoulumDefault() {
        return coulumDefault;
    }

    public void setCoulumDefault(String coulumDefault) {
        this.coulumDefault = coulumDefault;
    }

    public String getCoulumComment() {
        return coulumComment;
    }

    public void setCoulumComment(String coulumComment) {
        this.coulumComment = coulumComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

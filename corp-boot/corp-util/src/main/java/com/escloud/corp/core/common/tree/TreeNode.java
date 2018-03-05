package com.escloud.corp.core.common.tree;

import java.io.Serializable;
import java.util.List;

public class TreeNode<T> implements Tree<T>, Serializable {

    private static final long serialVersionUID = -1653478000503676779L;

    private String id;//节点id
    private String parentId;//父节点id
    private T data;//节点数据
    private Boolean selected = false;

    List<TreeNode<T>> children;//子节点

    public List<TreeNode<T>> getChildren() {
        return this.children;
    }

    public T getData() {
        return data;
    }

    public String getParentId() {
        return this.parentId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}

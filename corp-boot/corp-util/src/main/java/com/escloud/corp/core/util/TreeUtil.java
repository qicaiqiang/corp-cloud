package com.escloud.corp.core.util;


import com.escloud.corp.core.common.tree.Tree;
import com.escloud.corp.core.common.tree.TreeNode;

import java.util.*;

public class TreeUtil {

    public static List<TreeNode> build(List<? extends Tree> all, List<? extends Tree> has) {

        if (all != null) {
            int len = all.size();
            if (len > 0) {
                List<TreeNode> mainList = new ArrayList<TreeNode>();
                Map<String, TreeNode> map = new HashMap<String, TreeNode>();
                for (Tree t : all) {
                    String id = t.getId();
                    String parentId = t.getParentId();
                    Object data = t.getData();
                    TreeNode treeNode = new TreeNode();
                    treeNode.setData(data);
                    treeNode.setId(id);
                    treeNode.setParentId(parentId);
                    if(has != null && has.size() > 0 && has.contains(t)){
                        treeNode.setSelected(true);
                    }
                    if (parentId == null || "".equals(parentId)) {
                        mainList.add(treeNode);
                    }
                    map.put(id, treeNode);
                }
                Collection<TreeNode> values = map.values();
                for (TreeNode t : values) {
                    String parentId = t.getParentId();
                    if (parentId != null && !"".equals(parentId)) {
                        TreeNode parent = map.get(parentId);
                        List<TreeNode> children = parent.getChildren();
                        if (children == null) {
                            children = new ArrayList<TreeNode>();
                            parent.setChildren(children);
                        }
                        children.add(t);
                    }
                }
                return mainList;
            }
        }
        return null;
    }
}

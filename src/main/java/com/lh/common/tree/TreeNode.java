package com.lh.common.tree;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-12-26 18:08
 * @Version: 1.0.0
 */
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对应业务表中的id字段,前端数据树中的key
     */
    @JSONField(ordinal = 1)
    private String id;

    /**
     * 对应业务表中的id字段,前端数据树中的value
     */
    @JSONField(ordinal = 2)
    private String value;

    /**
     * 树节点Pid
     */
    @JSONField(ordinal = 3)
    private String parentId;
    /**
     * 树节点名
     */
    @JSONField(ordinal = 4)
    private String title;
    /**
     * 树节点层级
     */
    @JSONField(ordinal = 5)
    private Integer level;
    /**
     * 树图标
     */
    @JSONField(ordinal = 6)
    private String icon;
    /**
     * 树地址
     */
    @JSONField(ordinal = 7)
    private String url;
    /**
     * 树节点排序号
     */
    @JSONField(ordinal = 8)
    private Integer orderNum;
    /**
     * 树节点是否选中
     */
    @JSONField(ordinal = 9)
    private Boolean checked;
    /**
     * 树节点是否显示
     */
    @JSONField(ordinal = 10)
    private Boolean showed;
    /**
     * 业务主键Id
     */
    @JSONField(ordinal = 11)
    private String rId;
    /**
     * 树节点父节点
     */
    @JSONField(serialize = false)
    private TreeNode parent;
    /**
     * 树节点的子节点
     */
    @JSONField(ordinal = 11)
    private List<TreeNode> children = new ArrayList<>();

    // =============== 类方法 START =============

    /**
     * 获取下级节点
     * @return
     */
    public List<TreeNode> getChildren() {
        if (children == null || children.size() <= 0) {
            return null;
        }
        return children;
    }

    /**
     * 添加树子节点
     * @param treeNode
     */
    public void addChildren(TreeNode treeNode) {
        this.children.add(treeNode);
    }

    public TreeNode() {
    }

    public TreeNode(String id, String value,String parentId, String title, Integer orderNum, Boolean checked) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
        this.title = title;
        this.orderNum = orderNum;
        this.checked = checked;
    }
    // =============== 类方法 END =============

    // ============= get set START ===============
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getShowed() {
        return showed;
    }

    public void setShowed(Boolean showed) {
        this.showed = showed;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    // ============= get set END ===============
}

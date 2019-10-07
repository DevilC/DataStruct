package Node;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public abstract class Node {
    public Node parent;

    public ArrayList<Node> children;

    public Circle nodeCircle;

    private int key;

    private int level;

    private Object value;

    /**
     * 初始化画图属性,根据node的level和panel的大小初始化nodeCricle的位置坐标及半径
     * @param width   宽度
     * @param height  高度
     */
    public abstract void initGraphField(int width, int height);

    /**
     * 初始化画图属性，缺省panel长宽500*500
     */
    public void initGraphField(){
        initGraphField(500, 500);
    }

    /**
     * 构造函数
     * @param key 排序key值
     */
    public Node(int key){
        this.key = key;
    }

    /**
     * 构造函数
     * @param key  排序key值
     * @param value   承载value值
     */
    Node(int key, Object value){
        this.key = key;
        this.value = value;
    }

    public ArrayList<Node> getChildren(){
        return this.children;
    }

    /**
     * 获取指定的子节点
     * @param loc
     * @return
     */
    public Node getChild (int loc){
        return children.get(loc);
    }

    /**
     * 设置子节点
     * @param loc
     */
    public void setChild(int loc, Node targetNode){
        children.set(loc, targetNode);
    }

    /**
     * 设置parent
     * @param targetNode
     */
    public void setParent(Node targetNode){
        this.parent = targetNode;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

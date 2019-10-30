package Node;

import javafx.scene.shape.Circle;

public class BinaryTreeNode extends Node {
    public static int LEFT_CHILD = 0;
    public static int RIGHT_CHILD = 1;

    /**
     * 构造函数，输入key
     * @param key
     */
    public BinaryTreeNode(int key){
        super(key);
    }

    /**
     * 构造函数， 输入key和value
     * @param key
     * @param object
     */
    public BinaryTreeNode(int key, Object value){
        super(key, value);
    }

    /**
     * 是否含有左孩子
     * @return
     */
    public boolean hasLeft(){
        return !(null == this.getChild(LEFT_CHILD));
    }

    /**
     * 是否含有右孩子
     * @return
     */
    public boolean hasRight(){
        return !(null == this.getChild(RIGHT_CHILD));
    }

    /**
     * 获取左孩子
     * @return
     */
    public BinaryTreeNode getLeftChild(){
        return (BinaryTreeNode) getChild(LEFT_CHILD);
    }

    /**
     * 获取右孩子
     * @return
     */
    public BinaryTreeNode getRightChild(){
        return (BinaryTreeNode) getChild(RIGHT_CHILD);
    }

    /**
     * 设置左孩子
     * @param targetNode
     */
    public void setLeftChild(BinaryTreeNode targetNode){
        this.setChild(LEFT_CHILD, targetNode);
    }

    /**
     * 设置右孩子
     * @param targetNode
     */
    public void setRightChild(BinaryTreeNode targetNode){
        this.setChild(RIGHT_CHILD, targetNode);
    }

    /**
     * 获取键值
     * @return
     */
    public int getKey(){
        return this.getKey(0);
    }

    /**
     * 获取value
     * @return
     */
    public Object getValue(){
        return this.getValue(0);
    }

    @Override
    public String toString(){
        return "key: " + this.getKey() + ";level: " + this.getLevel() + ";subTreeHeight: " + this.getSubTreeHeight();
        //return ""+getSubTreeHeight();
    }

    public void initGraphField(int width, int height){
        super.initGraphField(width, height, 2);
    }

}

package Node;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class BinaryTreeNode extends Node {
    public static int LEFT_CHILD = 0;
    public static int RIGHT_CHILD = 1;

    //初始化children属性、subTreeHeight
    private void initChildren(){
        children = new ArrayList<Node>(2);
        children.add(null);
        children.add(null);
        this.subTreeHeight = 1;
    }

    /**
     * 构造函数，输入key
     * @param key
     */
    public BinaryTreeNode(int key){
        ArrayList<Integer> keys = new ArrayList<>(1);
        keys.add(key);
        this.setKeys(keys);
        initChildren();
    }

    /**
     * 构造函数， 输入key和value
     * @param key
     * @param object
     */
    public BinaryTreeNode(int key, Object object){
        ArrayList<Integer> keys = new ArrayList<>(1);
        keys.add(key);
        ArrayList<Object> values = new ArrayList<>(1);
        values.add(object);
        this.setKeys(keys);
        this.setValues(values);
        initChildren();
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
        targetNode.setParent(this);
    }

    /**
     * 设置右孩子
     * @param targetNode
     */
    public void setRightChild(BinaryTreeNode targetNode){
        this.setChild(RIGHT_CHILD, targetNode);
        targetNode.setParent(this);
    }

    @Override
    public void initGraphField(int width, int height) {
        double maxLeafNum = Math.pow(2, this.getLevel() + 1);
        double circleRadius = Math.floor((double)width/(2*maxLeafNum));
        circleRadius = circleRadius > 20 ? 20 : circleRadius;
        circleRadius = circleRadius < 10 ? 10 : circleRadius;
        double distance_X = width / maxLeafNum;


        double parentRadius = circleRadius;
        double x;
        double y;
        if(parent != null){
            parentRadius = parent.nodeCircle.getRadius();
            //左节点
            if(this == parent.getChild(LEFT_CHILD)){
                x = parent.nodeCircle.getCenterX() - distance_X;
                y = parent.nodeCircle.getCenterY() + 3 * parentRadius;
            } else{
                x = parent.nodeCircle.getCenterX() + distance_X;
                y = parent.nodeCircle.getCenterY() + 3 * parentRadius;
            }
        } else{
            System.out.println("is the root");
            x = distance_X;
            y = 0;
        }
        this.nodeCircle = new Circle();
        nodeCircle.setCenterX(x);
        nodeCircle.setCenterY(y);
        nodeCircle.setRadius(circleRadius);
//        nodeCircle.setAccessibleText(String.valueOf(getKey()));
        nodeCircle.setAccessibleText(this.toString());
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
    }


}

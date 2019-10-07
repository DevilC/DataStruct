package Node;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class BinaryTreeNode extends Node {
    public static int LEFT_CHILD = 0;
    public static int RIGHT_CHILD = 1;

    private void initChilder(){
        children = new ArrayList<Node>(2);
        children.add(null);
        children.add(null);
    }

    public BinaryTreeNode(int key){
        ArrayList<Integer> keys = new ArrayList<>(1);
        keys.add(key);
        this.setKeys(keys);
        initChilder();
    }


    public BinaryTreeNode(int key, Object object){
        ArrayList<Integer> keys = new ArrayList<>(1);
        keys.add(key);
        ArrayList<Object> values = new ArrayList<>(1);
        values.add(object);
        this.setKeys(keys);
        this.setValues(values);
        initChilder();
    }

    public boolean hasLeft(){
        return !(null == this.getChild(LEFT_CHILD));
    }

    public boolean hasRight(){
        return !(null == this.getChild(RIGHT_CHILD));
    }

    public BinaryTreeNode getLeftChild(){
        return (BinaryTreeNode) getChild(LEFT_CHILD);
    }

    public BinaryTreeNode getRightChild(){
        return (BinaryTreeNode) getChild(RIGHT_CHILD);
    }

    public void setLeftChild(BinaryTreeNode targetNode){
        this.setChild(LEFT_CHILD, targetNode);
        targetNode.setParent(this);
    }

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
        nodeCircle.setAccessibleText(String.valueOf(getKey()));
    }

    public int getKey(){
        return this.getKey(0);
    }


}

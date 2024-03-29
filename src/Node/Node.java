package Node;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Node {
    public Node parent;

    public List<Node> children = new ArrayList<>();

    public Circle nodeCircle = new Circle();

    private List<Integer> keys = new ArrayList<>();

    private int level;

    private List<Object> values = new ArrayList<>();

    //以此节点为根节点的子树高度,初始化为1
    int subTreeHeight = 1;

    /**
     * 更新节点子树高度，高度为最高孩子树高度+1
     */
    public void updateSubTreeHeight(){
        int maxChildTreeHeight = 0;
        for(Node child: children){
            if(child == null){
                continue;
            }
            if(child.subTreeHeight > maxChildTreeHeight){
                maxChildTreeHeight = child.subTreeHeight;
            }
        }
        this.subTreeHeight = maxChildTreeHeight + 1;
    }

    /**
     * 根据parent更新节点深度
     */
    public void updateLevel(){
        if(this.parent != null){
            this.setLevel(this.getParent().getLevel() + 1);
        }
        else{
            this.setLevel(0);
        }
    }

    /**
     * 获取以此节点为根节点的子树高度，初始值为1
     * @return
     */
    public int getSubTreeHeight(){
        return subTreeHeight;
    }

    /**
     *  更新以此节点为根节点的子树高度
     * @param height
     */
    public void setSubTreeHeight(int height){
        subTreeHeight = height;
    }

    /**
     * 初始化画图属性,根据node的level和panel的大小初始化nodeCricle的位置坐标及半径
     * @param width   宽度
     * @param height  高度
     */
    public void initGraphField(int width, int height, int M){
        double maxLeafNum = Math.pow(M, this.getLevel());
        double circleRadius = 20;
        double distance_X = width / (2*maxLeafNum);


        double parentRadius = circleRadius;
        double x = 0;
        double y = 0;
        if(parent != null){
            double middle = (M-1) / 2.0;
            for(int i = 0; i < parent.getChildren().size(); i++){
                if(this == parent.getChild(i)){
                    x = parent.nodeCircle.getCenterX() + (i - middle) * distance_X;
                    y = parent.nodeCircle.getCenterY() + 3 * parentRadius;
                }
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
        nodeCircle.setAccessibleText(this.toString());
    };

    /**
     * 初始化画图属性，缺省panel长宽500*500
     */
    public void initGraphField(){
        initGraphField(500, 500);
    }

    /**
     * 构造函数
     * @param keys 排序key值
     */
    public Node(ArrayList<Integer> keys){
        this.keys = keys;
        initChildren();
    }

    Node(){
        
    }

    Node(int key){
        this.keys.add(key);
        this.values.add(null);
        initChildren();
    }

    Node(int key, Object value){
        this.keys.add(key);
        this.values.add(value);
        initChildren();
    }

    /**
     * 构造函数
     * @param keys  排序key值
     * @param values   承载value值
     */
    Node(ArrayList<Integer> keys, ArrayList<Object> values){
        this.keys = keys;
        this.values = values;
    }

    public List<Node> getChildren(){
        return this.children;
    }

    public void setChildren(List<Node> children){
        this.children = children;
        for(Node child: children){
            if(child != null){
                child.setParent(this); 
            }
        }
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
     * @param loc 子节点位置
     * @param targetNode  目标子节点
     */
    public void setChild(int loc, Node targetNode){
        children.set(loc, targetNode);
        if(targetNode != null){
            targetNode.setParent(this);
        }
        
    }

    /**
     * 设置parent
     * @param targetNode 父节点
     */
    public void setParent(Node targetNode){
        this.parent = targetNode;
    }

    public Node getParent(){
        return parent;
    }

    public boolean replaceChild(Node oldChild, Node newChild){
        for(int i=0; i<children.size(); i++){
            if(children.get(i) == oldChild){
                children.set(i, newChild);
                if(newChild != null){
                    newChild.setParent(this);
                }
                return true;
            }
        }
        return false;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public void setKeys(List<Integer> keys) {
        this.keys = keys;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public int getKey(int loc) {
        return this.keys.get(loc);
    }

    public void setKey(int loc, int key) {
        this.keys.set(loc, key);
    }

    public Object getValue(int loc) {
        return this.values.get(loc);
    }

    public void setValue(int loc, Object value) {
        this.values.set(loc, value);
    }

    /**
     * 是否有非空孩子
     * @return 若有非空孩子，则返回true；否则返回false
     */
    public boolean hasChild(){
        int realChildrenNum = getRealChildrenNum();
        return realChildrenNum > 0;
    }

    /**
     * 获得非空孩子个数
     * @return Integer  非空孩子个数
     */
    public int getRealChildrenNum(){
        List<Node> realChildrenList = this.children.stream().filter(child -> child != null).collect(Collectors.toList());
        return realChildrenList.size();
    }

    /**
     * 是否叶子节点
     * @return 若是叶子节点，则返回true;否则返回false;
     */
    public boolean isLeaf(){
        return !hasChild();
    }

    /**
     * 初始化children为null
     */
    public void initChildren(){
        int sub = keys.size() + 1;
        for(int i=0; i< sub; i++){
            children.add(null);
        }
    }

    /**
     * 获取默认key值
     * @return keys[0]
     */
    public int getKey(){
        return keys.get(0);
    }

    /**
     * 获取默认value,默认为第0个
     * @return values[0]
     */
    public Object getValue(){
        return values.get(0);
    }

    public String toString(){
        return "keys: " + this.keys + "level: " + this.level;
    }
}

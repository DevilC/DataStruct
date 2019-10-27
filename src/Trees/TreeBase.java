package Trees;

import Node.Node;
import Util.KeyExistException;
import Util.NodeGraphInitConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

import javax.management.openmbean.KeyAlreadyExistsException;

public abstract class TreeBase<T extends Node> implements TreeOperation<T> {
    /**
     * 根节点
     */
    T root;

    /**
     * getRoot
     */
    public T getRoot(){
        return this.root;
    }

    /**
     * setRoot
     */
    public void setRoot(T rootNode){
        this.root = rootNode;
    }

    int treeHeight = 0;
    public int getTreeHeight() {
        return treeHeight;
    }

    //广度优先遍历
    public void travelNodesBreathFirst(Consumer<Node> nodeConsumer){
        travelNodesBreathFirst(nodeConsumer, this.getRoot());
    }
    //广度优先遍历
    public void travelNodesBreathFirst(Consumer<Node> nodeConsumer, Node root){
        Queue<Node> rootQueue = new LinkedList<>();
        rootQueue.add(root);

        travelNodesBreathFirst(rootQueue, nodeConsumer, 0);
    }

    public void travelNodesBreathFirst(Queue<Node> nodeQueue, Consumer<Node> nodeConsumer, int level){
        Queue<Node> nextLevelNodeQueue = new LinkedList<>();
        while(!nodeQueue.isEmpty()){
            Node tmpNode = nodeQueue.remove();
            if(tmpNode != null) {
                nodeConsumer.accept(tmpNode);
                nextLevelNodeQueue.addAll(tmpNode.getChildren());
            }
        }
        if(!nextLevelNodeQueue.isEmpty()){
            travelNodesBreathFirst(nextLevelNodeQueue, nodeConsumer, level + 1);
        }
    }

    //深度优先遍历
    public void travelNodesDeepFirst(Consumer<Node> nodeConsumer){
        travelNodesDeepFirst(nodeConsumer, this.getRoot());
    }
    //深度优先遍历
    public void travelNodesDeepFirst(Consumer<Node> nodeConsumer, Node root){
        for(Node child: root.getChildren()){
            if(child != null && child.hasChild()){
                travelNodesDeepFirst(nodeConsumer, child);
            }
            else{
                if(child != null){
                    nodeConsumer.accept(child);
                }
            }
        }
        nodeConsumer.accept(root);
    }

    public T findTargetNode(T addNode) throws KeyExistException {
        return recurrentFindLeaf(addNode, root);
    }

    private T recurrentFindLeaf(T addNode, T targetNode) throws KeyExistException{
        int key = addNode.getKey();
        if(targetNode.getKeys().contains(key)){
            System.out.println("The key is exist, node: " + addNode);
            throw new KeyExistException("The key is exist, node: " + addNode);
        }
        List<Integer> keys = targetNode.getKeys();
        int insertLoc = 0;
        for(insertLoc= 0;insertLoc < keys.size(); insertLoc++){
            if(keys.get(insertLoc) > addNode.getKey()){
                break;
            } 
        }
        if(targetNode.getChild(insertLoc) == null){
            return targetNode;
        } else{
            return recurrentFindLeaf(addNode, (T) targetNode.getChild(insertLoc));
        }
    }

    public void updateHeight(){
        this.treeHeight = root.getSubTreeHeight();
    }

    /**
     * 生成节点中的画图相关属性
     * @param width  画布宽度
     * @param height  画布高度
     */
    public void initNodeGraphField(int width, int height){
        travelNodesBreathFirst(new NodeGraphInitConsumer(width, height), this.root);
    }
}

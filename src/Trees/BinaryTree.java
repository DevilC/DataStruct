package Trees;

import Node.Node;
import Node.BinaryTreeNode;
import Util.NodeGraphInitConsumer;
import Util.NodeTypeErrorException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree extends TreeBase {
    public BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root){
        this.root = root;
    }

    public BinaryTree(){};

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public Node getNode(int key) {
        return new BinaryTreeNode(key);
    };

    @Override
    public int add(Node addNode) throws NodeTypeErrorException {
        int level =  addAndUpdateLevel(addNode, 0);
        /**
         * 更新树深度
         */
        updateLevel(level);
        return level;
    }

    /**
     *  递归添加节点并更新树深度
     * @param addNode 待添加的节点
     * @param level 目前递归深度
     * @return
     * @throws NodeTypeErrorException
     */
    public int addAndUpdateLevel(Node addNode, int level) throws NodeTypeErrorException {
        if(!(addNode instanceof BinaryTreeNode)){
            NodeTypeErrorException exception = new NodeTypeErrorException("Can't add a Node.Node except type Trees.BinaryTreeNode!");
            throw(exception);
        }
        BinaryTreeNode binaryTreeNode = (BinaryTreeNode) addNode;
        if(root == null){
            this.root = binaryTreeNode;
            binaryTreeNode.setLevel(level);
            return level;
        }
        else{
            binaryTreeNode.setLevel(level + 1);
            if(root.getKey() >= binaryTreeNode.getKey()){
                if(!root.hasLeft()){
                    root.setLeftChild(binaryTreeNode);
                    return level + 1;
                }
                else {
                    BinaryTree subTree = new BinaryTree(root.getLeftChild());
                    return subTree.addAndUpdateLevel(addNode, level + 1);
                }
            }else{
                if(!root.hasRight()){
                    root.setRightChild(binaryTreeNode);
                    return level + 1;
                }
                else{
                    BinaryTree subTree = new BinaryTree(root.getRightChild());
                    return subTree.addAndUpdateLevel(addNode, level + 1);
                }
            }
        }
    }

    protected void updateLevel(int level){
        if(level > treeLevel){
            treeLevel = level;
        }
    }

    @Override
    public boolean remove(Node removeNode) {
        return false;
    }

    @Override
    public List<Node> toList() {
        return null;
    }

    @Override
    public void Draw() {

    }

    /**
     * 生成节点中的画图相关属性
     * @param width  画布宽度
     * @param height  画布高度
     */
    public void initNodeGraphField(int width, int height){
        travelNodesBreathFirst(new NodeGraphInitConsumer(width, height));
    }

//    @Override
//    public void printTree() {
//        Queue<BinaryTreeNode> rootQueue = new LinkedList<>();
//        rootQueue.add(this.root);
//        NodePrintConsumer consumer = new NodePrintConsumer();
//        travelNodesBreathFirst(rootQueue, consumer);
//    }

    public void travelNodesBreathFirst(Consumer<Node> nodeConsumer){
        Queue<BinaryTreeNode> rootQueue = new LinkedList<>();
        rootQueue.add(this.root);

        travelNodesBreathFirst(rootQueue, nodeConsumer, 0);
    }

    public void travelNodesBreathFirst(Queue<BinaryTreeNode> nodeQueue, Consumer<Node> nodeConsumer, int level){
        Queue<BinaryTreeNode> nextLevelNodeQueue = new LinkedList<>();
        while(!nodeQueue.isEmpty()){
            BinaryTreeNode tmpNode = nodeQueue.remove();
            nodeConsumer.accept(tmpNode);
            if(tmpNode != null) {
                nextLevelNodeQueue.add(tmpNode.getLeftChild());
                nextLevelNodeQueue.add(tmpNode.getRightChild());
            }
        }
        if(!nextLevelNodeQueue.isEmpty()){
            travelNodesBreathFirst(nextLevelNodeQueue, nodeConsumer, level + 1);
        }
    }
}

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
    public BinaryTreeNode getRoot() {
        return this.root;
    }

    @Override
    public void setRoot(Node root) {
        this.root = (BinaryTreeNode) root;
        updateHeight();
    }

    @Override
    public BinaryTreeNode getNode(int key) {
        return new BinaryTreeNode(key);
    };

    @Override
    public int add(Node addNode) throws NodeTypeErrorException {
        int level =  addAndUpdateLevel(addNode, 0);
        /**
         * 更新树高度
         */
        updateHeight();
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
            if(root.getKey() > binaryTreeNode.getKey()){
                if(!root.hasLeft()){
                    root.setLeftChild(binaryTreeNode);
                    root.updateSubTreeHeight();
                    return level + 1;
                }
                else {
                    BinaryTree subTree = new BinaryTree(root.getLeftChild());
                    int subLevel =  subTree.addAndUpdateLevel(addNode, level + 1);
                    root.updateSubTreeHeight();
                    return subLevel;
                }
            }else{
                if(!root.hasRight()){
                    root.setRightChild(binaryTreeNode);
                    root.updateSubTreeHeight();
                    return level + 1;
                }
                else{
                    BinaryTree subTree = new BinaryTree(root.getRightChild());
                    int subLevel =  subTree.addAndUpdateLevel(addNode, level + 1);
                    root.updateSubTreeHeight();
                    return subLevel;
                }
            }
        }
    }

    protected void updateHeight(){
        this.treeHeight = root.getSubTreeHeight();
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
        travelNodesBreathFirst(new NodeGraphInitConsumer(width, height), this.root);
    }

//    @Override
//    public void printTree() {
//        Queue<BinaryTreeNode> rootQueue = new LinkedList<>();
//        rootQueue.add(this.root);
//        NodePrintConsumer consumer = new NodePrintConsumer();
//        travelNodesBreathFirst(rootQueue, consumer);
//    }
}

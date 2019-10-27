package Trees;

import Node.Node;
import Node.BinaryTreeNode;
import Util.KeyExistException;
import Util.NodeGraphInitConsumer;
import Util.NodeTypeErrorException;
import java.util.List;

public class BinaryTree extends TreeBase<BinaryTreeNode> {
    public BinaryTree(BinaryTreeNode root){
        this.root = root;
    }

    public BinaryTree(){};

    @Override
    public BinaryTreeNode getNode(int key) {
        return new BinaryTreeNode(key);
    };

    @Override
    public int add(BinaryTreeNode addNode) throws NodeTypeErrorException, KeyExistException {
        int level =  addAndUpdateLevel(addNode, 0);
        /**
         * 更新树高度
         */
        updateHeight();
        return level;
    }

    /**
     * 递归添加节点并更新树深度
     * 
     * @param addNode 待添加的节点
     * @param level   目前递归深度
     * @return
     * @throws NodeTypeErrorException
     * @throws KeyExistException
     */
    public int addAndUpdateLevel(Node addNode, int level) throws NodeTypeErrorException, KeyExistException {
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
            if(root.getKey() == binaryTreeNode.getKey()){
                //key已存在，返回添加失败
                System.out.println("Failed to add:" + binaryTreeNode);
                throw new KeyExistException("Failed to add node:" + binaryTreeNode);
            }
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

    @Override
    public boolean remove(BinaryTreeNode removeNode) {
        return false;
    }

    @Override
    public List<BinaryTreeNode> toList() {
        return null;
    }

    @Override
    public void Draw() {

    }

//    @Override
//    public void printTree() {
//        Queue<BinaryTreeNode> rootQueue = new LinkedList<>();
//        rootQueue.add(this.root);
//        NodePrintConsumer consumer = new NodePrintConsumer();
//        travelNodesBreathFirst(rootQueue, consumer);
//    }
}

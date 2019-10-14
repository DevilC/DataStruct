package Trees;

import Node.BinaryTreeNode;
import Node.Node;
import Util.NodeTypeErrorException;
import Util.UpdateNodeLevelConsumer;
import Util.UpdateNodeSubTreeHeightConsumer;

public class BalanceBinaryTree extends BinaryTree {
    public enum RotateType{
        RIGHT_ROTATE, LEFT_ROTATE, RIGHT_LEFT_ROTATE, LEFT_RIGHT_ROTATE, NOT_ROTATE;

        //获取旋转类型
        public static RotateType getRotateType(BalanceBinaryTree tree){
           BinaryTreeNode root = tree.getRoot();
            int leftHeight = root.hasLeft() ? root.getLeftChild().getSubTreeHeight() : 0;
            int rightHeight = root.hasRight() ? root.getRightChild().getSubTreeHeight() : 0;
            if(Math.abs(leftHeight - rightHeight) <= 1){
                return NOT_ROTATE;
            }
            if(leftHeight > rightHeight){
                BinaryTreeNode subRoot = root.getLeftChild();
                int childLeftHeighgt = subRoot.hasLeft() ? subRoot.getLeftChild().getSubTreeHeight() : 0;
                int childRightHeight = subRoot.hasRight() ? subRoot.getRightChild().getSubTreeHeight() : 0;
                if(childLeftHeighgt > childRightHeight){
                    return RIGHT_ROTATE;
                }else{
                    return LEFT_RIGHT_ROTATE;
                }
            } else{
                BinaryTreeNode subRoot = root.getRightChild();
                int childLeftHeighgt = subRoot.hasLeft() ? subRoot.getLeftChild().getSubTreeHeight() : 0;
                int childRightHeight = subRoot.hasRight() ? subRoot.getRightChild().getSubTreeHeight() : 0;
                if(childRightHeight > childLeftHeighgt){
                    return LEFT_ROTATE;
                } else{
                    return RIGHT_LEFT_ROTATE;
                }
            }
        }

        public void doRotate(BalanceBinaryTree tree){
            BinaryTreeNode oldRoot = tree.getRoot();
            BinaryTreeNode newRoot;
            switch(this){
                case RIGHT_ROTATE:
                    newRoot = rightRotate(oldRoot);
                    tree.setRoot(newRoot);
                    break;
                case LEFT_ROTATE:
                    newRoot = leftRotate(oldRoot);
                    tree.setRoot(newRoot);
                    break;
                case RIGHT_LEFT_ROTATE:
                    BinaryTreeNode oldRootRightChild = oldRoot.getRightChild();
                    //先围绕右子节点右旋转
                    newRoot = rightRotate(oldRootRightChild);
                    oldRoot.setRightChild(newRoot);
                    //再围绕树的根节点左旋转
                    newRoot = leftRotate(oldRoot);
                    tree.setRoot(newRoot);
                    break;
                case LEFT_RIGHT_ROTATE:
                    BinaryTreeNode oldRootLeftChild = oldRoot.getLeftChild();
                    //先围绕左子节点左旋转
                    newRoot = leftRotate(oldRootLeftChild);
                    oldRoot.setLeftChild(newRoot);
                    //再围绕树的根节点右旋转
                    newRoot = rightRotate(oldRoot);
                    tree.setRoot(newRoot);
                    break;
                default:
                    break;
            }
        }

        //右旋转，返回根节点
        private BinaryTreeNode rightRotate(BinaryTreeNode oldRoot){
            BinaryTreeNode newRoot = oldRoot.getLeftChild();
            if(oldRoot.getParent() != null){
                //不为最外层根节点,需更新
                oldRoot.getParent().replaceChild(oldRoot, newRoot);
            }
            newRoot.setParent(oldRoot.getParent());
            oldRoot.setLeftChild(newRoot.getRightChild());
            newRoot.setRightChild(oldRoot);
            oldRoot.setParent(newRoot);

            return newRoot;
        }

        //左旋转，返回根节点
        private BinaryTreeNode leftRotate(BinaryTreeNode oldRoot){
            BinaryTreeNode newRoot = oldRoot.getRightChild();
            if(oldRoot.getParent() != null) {
                //不为最外层根节点
                oldRoot.getParent().replaceChild(oldRoot, newRoot);
            }
            newRoot.setParent(oldRoot.getParent());
            oldRoot.setRightChild(newRoot.getLeftChild());
            newRoot.setLeftChild(oldRoot);
            oldRoot.setParent(newRoot);

            return newRoot;
        }
    }

    BalanceBinaryTree(BinaryTreeNode root){
        super(root);
    }

    public BalanceBinaryTree(){};

    @Override
    public int add(Node addNode) throws NodeTypeErrorException {
        int height =  addAndUpdateLevel(addNode, 0);
        reshapeToBalance();
        travelNodesBreathFirst(new UpdateNodeLevelConsumer(), root);
        travelNodesDeepFirst(new UpdateNodeSubTreeHeightConsumer(), root);
        return height;
    }

    /**
     * 添加或删除节点后重新旋转为平衡二叉树
     */
    private void reshapeToBalance(){
        if(root.hasLeft()){
            BalanceBinaryTree leftSubTree = new BalanceBinaryTree(root.getLeftChild());
            if( !leftSubTree.isBalance()){
                leftSubTree.reshapeToBalance();
            }
        }
        if(root.hasRight()){
            BalanceBinaryTree rightSubTree = new BalanceBinaryTree(root.getRightChild());
            if( !rightSubTree.isBalance()){
                rightSubTree.reshapeToBalance();
            }
        }
        if(!this.isBalance()){
            doReshape();
        }
    }

    private void doReshape(){
        RotateType rotateType = RotateType.getRotateType(this);
        rotateType.doRotate(this);
    }

    /**
     * 判断目前树是否平衡二叉树··
     * @return
     */
    public boolean isBalance(){
        int leftHeight = this.root.hasLeft() ? root.getLeftChild().getSubTreeHeight() : 0;
        int rightHeight = this.root.hasRight() ? root.getRightChild().getSubTreeHeight() : 0;
        if(Math.abs(leftHeight - rightHeight) <= 1){
            return true;
        }
        return false;
    }
}

package Trees;

import Node.BinaryTreeNode;
import Node.Node;
import Util.NodeTypeErrorException;

public class BalanceBinaryTree extends BinaryTree {
    BalanceBinaryTree(BinaryTreeNode root){
        super(root);
    }

    @Override
    public int add(Node addNode) throws NodeTypeErrorException {
        int height =  addAndUpdateLevel(addNode, 0);
        /**
         * 更新树深度
         */
        updateHeight();
        return height;
    }

    /**
     * 添加或删除节点后重新旋转为平衡二叉树
     */
    private void reshapeToBalance(){

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

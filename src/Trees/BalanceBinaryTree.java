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
        int level =  addAndUpdateLevel(addNode, 0);
        /**
         * 更新树深度
         */
        updateLevel(level);
        return level;
    }
}

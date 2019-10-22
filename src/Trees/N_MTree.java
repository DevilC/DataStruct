package Trees;
import java.util.List;

import Node.N_MNode;
import Node.Node;
import Util.NodeTypeErrorException;

public class N_MTree extends TreeBase {
    N_MNode root;

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public void setRoot(Node root) {
        this.root = (N_MNode) root;
    }

    @Override
    public Node getNode(int key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int add(Node addNode) throws NodeTypeErrorException {
        if(this.root == null){
            this.setRoot(addNode);
        }
        return 0;
    }

    @Override
    public boolean remove(Node removeNode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Node> toList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void Draw() {
        // TODO Auto-generated method stub

    }
    
    
}
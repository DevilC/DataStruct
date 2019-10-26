package Trees;
import java.util.List;

import Node.N_MNode;
import Util.KeyExistException;
import Util.NodeTypeErrorException;

public class N_MTree extends TreeBase<N_MNode> {
    @Override
    public N_MNode getNode(int key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int add(N_MNode addNode) throws NodeTypeErrorException, KeyExistException {
        if(this.root == null){
            this.setRoot(addNode);
            return 0;
        }
        N_MNode targetNode = findTargetNode(addNode);
        N_MNode insertResult = targetNode.insertKeyAndValue(addNode);
        if(insertResult != null){
            this.root = insertResult;
        } 
        
        return 0;
    }

    @Override
    public boolean remove(N_MNode removeNode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<N_MNode> toList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void Draw() {
        // TODO Auto-generated method stub

    }
    
    
}
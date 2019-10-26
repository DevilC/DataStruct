import Node.N_MNode;
import Trees.N_MTree;
import Util.KeyExistException;
import Util.NodeTypeErrorException;

class TestN_MTree {
    public static void main(String agrs[]) {
        int M = 3;
        int[] input = { 59, 32, 30, 48, 62, 59, 98, 50, 84 };
        N_MTree tree = new N_MTree();
        for (int i = 0; i < 9; i++) {
            N_MNode node = new N_MNode(input[i], M);
            try {
                tree.add(node);
            } catch (NodeTypeErrorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (KeyExistException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
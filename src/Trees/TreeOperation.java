package Trees;

import Node.Node;
import Util.NodeTypeErrorException;

import java.util.List;

public interface TreeOperation {
    /**
     * 获得树的根节点
     * @return
     */
    Node getRoot();

    /**
     * 根据key获得节点
     * @param key
     * @return
     */
    Node getNode(int key);

    /**
     * 添加节点
     * @param addNode
     * @return
     */
    int add(Node addNode) throws NodeTypeErrorException;

    /**
     * 移除节点
     * @param removeNode
     * @return
     */
    boolean remove(Node removeNode);

    /**
     * 把树转换成list
     * @return
     */
     List<Node> toList();

    /**
     * 把树画出来
     */
    void Draw();
}

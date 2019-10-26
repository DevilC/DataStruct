package Trees;

import Node.Node;
import Util.KeyExistException;
import Util.NodeTypeErrorException;

import java.util.List;

public interface TreeOperation<T extends Node> {

    /**
     * 获得树的根节点
     * @return
     */
    T getRoot();

    /**
     * 设置树的根节点
     * @return
     */
    void setRoot(T root);

    /**
     * 根据key获得节点
     * @param key
     * @return
     */
    T getNode(int key);

    /**
     * 添加节点
     * @param addNode
     * @return
     * @throws KeyExistException
     */
    int add(T addNode) throws NodeTypeErrorException, KeyExistException;

    /**
     * 移除节点
     * @param removeNode
     * @return
     */
    boolean remove(T removeNode);

    /**
     * 把树转换成list
     * @return
     */
     List<T> toList();

    /**
     * 把树画出来
     */
    void Draw();

    /**
     * 找到插入的节点
     * @param addNode
     * @return
     */
    T findTargetNode(T addNode);
}

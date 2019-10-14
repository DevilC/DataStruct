package Trees;

import Node.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class TreeBase implements TreeOperation {
    int treeHeight = 0;
    public int getTreeHeight() {
        return treeHeight;
    }

    //广度优先遍历
    public void travelNodesBreathFirst(Consumer<Node> nodeConsumer, Node root){
        Queue<Node> rootQueue = new LinkedList<>();
        rootQueue.add(root);

        travelNodesBreathFirst(rootQueue, nodeConsumer, 0);
    }

    public void travelNodesBreathFirst(Queue<Node> nodeQueue, Consumer<Node> nodeConsumer, int level){
        Queue<Node> nextLevelNodeQueue = new LinkedList<>();
        while(!nodeQueue.isEmpty()){
            Node tmpNode = nodeQueue.remove();
            if(tmpNode != null) {
                nodeConsumer.accept(tmpNode);
                nextLevelNodeQueue.addAll(tmpNode.getChildren());
            }
        }
        if(!nextLevelNodeQueue.isEmpty()){
            travelNodesBreathFirst(nextLevelNodeQueue, nodeConsumer, level + 1);
        }
    }

    //深度优先遍历
    public void travelNodesDeepFirst(Consumer<Node> nodeConsumer, Node root){
        for(Node child: root.getChildren()){
            if(child != null && child.hasChild()){
                travelNodesDeepFirst(nodeConsumer, child);
            }
            else{
                if(child != null){
                    nodeConsumer.accept(child);
                }
            }
        }
        nodeConsumer.accept(root);
    }

}

package Util;

import Node.Node;

import java.util.function.Consumer;

public class NodeGraphInitConsumer implements Consumer<Node> {

    public int width;

    public int height;

    public NodeGraphInitConsumer(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(Node node) {
        if(node != null){
            node.initGraphField(this.width, this.height);
        }
    }

    @Override
    public Consumer<Node> andThen(Consumer<? super Node> after) {
        return null;
    }
}

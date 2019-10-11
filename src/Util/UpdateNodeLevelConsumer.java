package Util;

import Node.Node;

import java.util.function.Consumer;

public class UpdateNodeLevelConsumer implements Consumer<Node> {

    @Override
    public void accept(Node node) {
        node.updateLevel();
    }

    @Override
    public Consumer<Node> andThen(Consumer<? super Node> after) {
        return null;
    }
}

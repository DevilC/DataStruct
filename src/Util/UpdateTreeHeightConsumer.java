package Util;

import Node.Node;

import java.util.function.Consumer;

public class UpdateTreeHeightConsumer implements Consumer<Node> {
    @Override
    public void accept(Node node) {
        node.updateSubTreeHeight();
    }

    @Override
    public Consumer<Node> andThen(Consumer<? super Node> after) {
        return null;
    }
}

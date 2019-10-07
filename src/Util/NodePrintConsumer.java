package Util;

import Node.Node;

import java.util.function.Consumer;

public class NodePrintConsumer implements Consumer<Node> {
    @Override
    public void accept(Node o) {
        if(o == null){
            System.out.print("[ ] ");
        }
        else{
            System.out.print("[" + o.getKeys() + "] ");
        }
    }
}

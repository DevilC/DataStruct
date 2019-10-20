package Util;

import Node.Node;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class PaintingTreeConsumerPanel extends JPanel implements Consumer<Node>{
    private static final long serialVersionUID = -5709042771771991196L;
    
    ArrayList<Node> nodeList = new ArrayList<>();
    Object lock = new Object();

    @Override
    public void paint(Graphics g){
        synchronized (lock) {
            for (Node node : nodeList) {
                Circle circle = node.nodeCircle;
                int center_x = (int) (circle.getCenterX() + circle.getRadius());
                int center_y = (int) (circle.getCenterY() + circle.getRadius());

                if (node.parent != null) {
                    Circle parentCircle = node.parent.nodeCircle;
                    g.drawLine(center_x, center_y, (int) (parentCircle.getCenterX() + parentCircle.getRadius()), (int) (parentCircle.getCenterY() + parentCircle.getRadius()));
                }
                g.setColor(Color.WHITE);
                g.fillOval((int)circle.getCenterX(), (int)circle.getCenterY(), 2 * (int) circle.getRadius(), 2 * (int) circle.getRadius());
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(circle.getAccessibleText()), center_x - 5, center_y);
            }
        }
    }

    @Override
    public void accept(Node node) {
        synchronized (lock) {
            if (node != null && node.nodeCircle != null && !nodeList.contains(node)) {
                nodeList.add(node);
            }
            this.repaint();
        }
    }

    @Override
    public Consumer<Node> andThen(Consumer<? super Node> after) {
        return null;
    }
}

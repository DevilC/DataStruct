import Node.BinaryTreeNode;
import Trees.BalanceBinaryTree;
import Trees.BinaryTree;
import Util.NodeTypeErrorException;
import Util.PaintingTreeConsumerPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NodeTypeErrorException {

//        BinaryTreeNode root = new BinaryTreeNode(55);
//        BinaryTree tree = new BinaryTree(root);
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            int inputKey = scanner.nextInt();
//            BinaryTreeNode addNode = new BinaryTreeNode(inputKey);
//            tree.add(addNode);
//        }

        JFrame jFrame = new JFrame();
        createAndShowGUI(jFrame);

        PaintingTreeConsumerPanel panelConsumer = new PaintingTreeConsumerPanel();

        JScrollPane jsp = new JScrollPane(panelConsumer);
        jFrame.getContentPane().add(jsp);
        jsp.getViewport().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jFrame.repaint();
            }
        });
        Scanner scanner = new Scanner(System.in);
        BalanceBinaryTree tree = new BalanceBinaryTree();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for(int i=0;i<10;i++){
            int random = r.nextInt(100);
            tree.add(new BinaryTreeNode(random));
        }
  //      tree.add(new BinaryTreeNode(11));
//        tree.add(new BinaryTreeNode(9));
//        tree.add(new BinaryTreeNode(10));
//        tree.add(new BinaryTreeNode(7));
//        tree.add(new BinaryTreeNode(6));
//        tree.add(new BinaryTreeNode(11));
//        tree.add(new BinaryTreeNode(10));
        int level = tree.getTreeHeight();
        System.out.println("tree height is: " + level);
        int width = (int)(10 * Math.pow(2, (level + 1))) < 500 ? 500 : (int)(10 * Math.pow(2, (level + 1)));
        int height = 60 * (level+1);
        panelConsumer.setPreferredSize(new Dimension(width, height));
        tree.initNodeGraphField(width, height);
        tree.travelNodesBreathFirst(panelConsumer);
        jFrame.validate();
    }

    public static void createAndShowGUI(JFrame jFrame) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        jFrame.setSize(1000, 500);
        jFrame.setBounds(0, 0, 1000, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 显示窗口
        jFrame.setVisible(true);
    }
}

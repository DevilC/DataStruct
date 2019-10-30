import Node.BinaryTreeNode;
import Trees.BalanceBinaryTree;
import Util.KeyExistException;
import Util.NodeTypeErrorException;
import Util.PaintingTreeConsumerPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Random;

public class TestBalanceTree {
    public static void main(String[] args) {

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
        BalanceBinaryTree tree = new BalanceBinaryTree();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
//        for(int i=0;i<10;i++){
//            int random = r.nextInt(100);
//            System.out.println(random);
//            tree.add(new BinaryTreeNode(random));
//        }
        int[] input = {59, 32, 30, 48, 62, 59, 98, 50, 84};
        for(int i=0; i<9; i++){
            try {
                tree.add(new BinaryTreeNode(input[i]));
            } catch (NodeTypeErrorException | KeyExistException e1) {
                System.out.println("Failed to add key: " + input[i]);
            }
        }
        int level = tree.getTreeHeight();
        int width = (int)(20 * Math.pow(2, (level + 2)));
        int height = 60 * (level+1);
        panelConsumer.setPreferredSize(new Dimension(1000, 500));
        tree.initNodeGraphField(1000, 500);
        tree.travelNodesBreathFirst(panelConsumer, tree.getRoot());
        panelConsumer.removeAll();
        panelConsumer.validate();
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

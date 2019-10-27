import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;

import Node.N_MNode;
import Trees.N_MTree;
import Util.KeyExistException;
import Util.NodeGraphInitConsumer;
import Util.NodeTypeErrorException;
import Util.PaintingTreeConsumerPanel;
import Util.UpdateNodeLevelConsumer;
import Util.UpdateNodeSubTreeHeightConsumer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class TestN_MTree {
    public static void main(String agrs[]) {
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


        int M = 3;
        int[] input = { 59, 32, 30, 48, 62, 98, 50, 84 ,100, 105, 21, 10, 15, 14, 1000, 1042, 108, 182, 234, 235, 542, 128};
        N_MTree tree = new N_MTree();
        for (int i = 0; i < input.length; i++) {
            N_MNode node = new N_MNode(input[i], M);
            try {
                tree.add(node);
            } catch (NodeTypeErrorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (KeyExistException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        tree.travelNodesBreathFirst(new UpdateNodeLevelConsumer());
        tree.travelNodesDeepFirst(new UpdateNodeSubTreeHeightConsumer());
        tree.updateHeight();
        int level = tree.getTreeHeight();
        int width = (int)(20 * Math.pow(M, (level + 1)));
        int height = 60 * (level+1);
        panelConsumer.setPreferredSize(new Dimension(width, height));
        tree.travelNodesBreathFirst(new NodeGraphInitConsumer(width, height));
        tree.travelNodesBreathFirst(panelConsumer);
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
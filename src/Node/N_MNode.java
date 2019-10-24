package Node;

import java.util.ArrayList;
import java.util.List;

import javax.management.ListenerNotFoundException;

public class N_MNode extends Node {
    /**
     * 最大孩子个数
     */
    public int M;

    //若发生reshape，移动的key位置
    public int moveLoc;

    public N_MNode(int key, int M){
        super(key);
        this.M = M;
    }

    public N_MNode(int key, Object value, int M){
        super(key, value);
        this.M = M;
    }

    public boolean addNode(N_MNode targetNode){
        int targetKey = targetNode.getKey();
        if(this.getKeys().contains(targetKey)){
            System.out.println("The key is exist, node:" + targetNode);
            return false;
        }


        return true;
    }
    
    private void insertKeyAndValue(N_MNode targetNode){
        //插入有两种情况，第一种情况为插入的为新节点，children都为空
        //第二种情况是插入的是子节点reshape之后的节点，children不为空
        
        int key = targetNode.getKey();
        Object value = targetNode.getValue();
        //插入的位置,初始化为末端
        int insertLoc = this.getKeys().size();
        for(int i = 0; i < targetNode.getKeys().size(); i++){
            if(this.getKey(i) > key){
                insertLoc = i;
            }
        }
        List<Integer> leftPartKeys = this.getKeys().subList(0, insertLoc - 1);
        List<Integer> rightPartKeys = this.getKeys().subList(insertLoc, this.getKeys().size());
        List<Object> leftPartValues = this.getValues().subList(0, insertLoc - 1 );
        List<Object> rightPartValues = this.getValues().subList(insertLoc, this.getKeys().size());

        List<Integer> newKeys = new ArrayList<>();
        newKeys.addAll(leftPartKeys);newKeys.add(key);newKeys.addAll(rightPartKeys);
        List<Object> newValues = new ArrayList<>();
        newValues.addAll(leftPartValues);newValues.add(value);newValues.addAll(rightPartValues);
        List<Node> newChildren = new ArrayList<>();

        //第一种情况:插入的为新节点,发生在叶子节点,孩子全为null
        if(!targetNode.hasChild()){
            newChildren.addAll(this.getChildren());
            newChildren.add(null);
        } else{//第二种情况：节点向上生长
            List<Node> oldChildren = this.getChildren();
            oldChildren.set(insertLoc, targetNode.getChild(0));
            List<Node> leftPartChildren = oldChildren.subList(0, insertLoc);
            List<Node> rightPartChildren = oldChildren.subList(insertLoc + 1, oldChildren.size());
            newChildren.addAll(leftPartChildren);
            newChildren.add(this.getChild(1));
            newChildren.addAll(rightPartChildren);
        }

        
        if(newKeys.size() == M){
            growUpReshape();
        }
    }


    public void growUpReshape(){

    }

    @Override
    public void initGraphField(int width, int height) {
        // TODO Auto-generated method stub

    }}
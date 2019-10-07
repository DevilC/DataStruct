package Trees;

public abstract class TreeBase implements TreeOperation {
    public int getTreeLevel() {
        return treeLevel;
    }

    int treeLevel = 0;
}

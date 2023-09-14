package spell;

public class Node implements INode {

    private int count;
    private Node[] children = new Node[26];


    // getValue
    // incrementValue
    // getChildren

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void incrementValue() {
    }

    @Override
    public INode[] getChildren() {
        return children;
    }

}

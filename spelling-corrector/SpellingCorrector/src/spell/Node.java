package spell;

public class Node implements INode {

    private int count = 0;
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
        // when you get to the end of a word, increase its count
        count++;
    }

    @Override
    public INode[] getChildren() {
        return children;
    }

}

package spell;

public class Trie implements ITrie{

    private TrieNode root;
    private int wordCount;
    private int nodeCount;

    // add
    // find
    // getWordCount
    // getNodeCount
    // toString - done(?)
    // equals
    // hashcode

    @Override
    public String toString() {

        StringBuilder curWord = new StringBuilder();
        StringBuilder output = new StringBuilder();

        toString_Helper(root, curWord, output);

        return output.toString();

    }

    private void toString_Helper(TrieNode n, StringBuilder curWord, StringBuilder output) {

        if (n.getValue() > 0) {
            output.append(curWord.toString());
            output.append("\n");
        }

        for (int i =0; i < children.length; i++) {
            INode child = n.getChildren()[i];
            if (child != null) {

                char childLetter = (char)('a' + i);
                curWord.append(childLetter);

                toString_Helper(child, curWord, output);

                curWord.deleteCharAt(curWord.size()-1);
            }
        }
    }

    @Override
    public boolean equals(Object o) {

        // is o == null?
        // is o == this?
        // do this and o have the same class?

        Trie d = (Trie)o;

        // do this and d have the same wordCount and nodeCount?

        return equals_Helper(this.root, d.root);

    }

    private boolean equals_Helper(TrieNode n1, TrieNode n2) {

        // Compare n1 and n2 to see if they are the same
            // Do n1 and n2 have the same count?
            // Do n1 and n2 have non-null children in axactly the same indexes?
        // Recurse on the children and compare the child subtrees

    }

    @Override
    public int hashCode() {

        // Combine the following values:
        // 1. wordCount
        // 2. nodeCount
        // 3. The index of each of the root node's non-null children
    }

    @Override
    public void add(String word) {

    }

    @Override
    public INode find(String word) {
        return null;
    }

    @Override
    public int getWordCount() {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }
}

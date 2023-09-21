package spell;

public class Trie implements ITrie{

    private INode root = new Node();
    private int wordCount = 0;
    private int nodeCount = 1;

    @Override
    public String toString() {

        StringBuilder curWord = new StringBuilder();
        StringBuilder output = new StringBuilder();

        toString_Helper(root, curWord, output);

        return output.toString();

    }

    private void toString_Helper(INode n, StringBuilder curWord, StringBuilder output) {

        if (n.getValue() > 0) {
            output.append(curWord.toString());
            output.append("\n");
        }

        for (int i =0; i < n.getChildren().length; i++) {
            INode child = n.getChildren()[i];
            if (child != null) {

                char childLetter = (char)('a' + i);
                curWord.append(childLetter);

                toString_Helper(child, curWord, output);

                curWord.deleteCharAt(curWord.length()-1);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Trie d = (Trie) o;
        if (d.nodeCount != this.nodeCount) {
            return false;
        }
        if (d.wordCount != this.wordCount) {
            return false;
        }
        return equals_Helper(this.root, d.root);
    }

    private boolean equals_Helper(INode n1, INode n2) {
        if (n1.getValue() != n2.getValue()) {
            return false;
        }
        for (int i = 0; i < n1.getChildren().length; i++) {
            INode child1 = n1.getChildren()[i];
            INode child2 = n2.getChildren()[i];
            if (child1 == null && child2 != null) {
                return false;
            }
            if (child2 == null && child1 != null) {
                return false;
            }
            if (child1 != null && child2 != null) {
                if (!equals_Helper(child1, child2)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int index = 0;
        for (int i = 0; i < 25; i++) {
            if (root.getChildren()[i] != null) {
                index = i;
            }
        }
        wordCount = getWordCount();
        nodeCount = getNodeCount();
        return index * wordCount * nodeCount;
    }

    @Override
    public void add(String word) {
        char letter;
        int index;
        INode currentNode = root;

        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            letter = word.charAt(i);
            index = letter - 'a';

            if (currentNode.getChildren()[index] == null) {
                currentNode.getChildren()[index] = new Node();
                nodeCount = nodeCount + 1;
            }
            currentNode = currentNode.getChildren()[index];
        }
        if (currentNode.getValue() == 0) {
            wordCount = wordCount + 1;
        }
        currentNode.incrementValue();
    }

    @Override
    public INode find(String word) {
        char letter;
        int index = 0;
        INode currentNode = root;

        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            letter = word.charAt(i);
            index = letter - 'a';

            if (currentNode.getChildren()[index] == null) {
                return null;
            }
            currentNode = currentNode.getChildren()[index];
        }
        if (currentNode.getValue() > 0) {
            return currentNode;
        }
        return null;
    }

    @Override
    public int getWordCount() {   // count the number of non-zero nodes in the trie
        return wordCount;
    }

    @Override
    public int getNodeCount() {   // count the number of nodes in the trie (including zeros)
        return nodeCount;
    }
}



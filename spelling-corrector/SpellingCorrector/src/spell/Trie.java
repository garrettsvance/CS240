package spell;

public class Trie implements ITrie{

    private INode root = new Node();
    private int wordCount = 0;
    private int nodeCount = 1;

    // add
    // find
    // getWordCount - ez
    // getNodeCount - ez
    // toString - done:tm:
    // equals - pseudo code written
    // hashcode - pseudo code sorta written

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

        // is o == null?
        // is o == this?
        // do this and o have the same class?

        Trie d = (Trie)o;

        // do this and d have the same wordCount and nodeCount?

        return equals_Helper(this.root, d.root);
        //this.root is referring to the trie that you're inside of, its the same as "root"


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

        // convert to lowercase
        // process each character 1 by 1
        // start at the root
        // get the index by doing letter - 'a'

        char letter;
        int index;
        INode currentNode = root;


        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            letter = word.charAt(i);
            index = letter - 'a';

            if (currentNode.getChildren()[index] == null) {
                currentNode.getChildren()[index] = new Node();
                currentNode = currentNode.getChildren()[index];
                nodeCount = nodeCount + 1;
            }
        }
        wordCount = wordCount + 1;


    }

    @Override
    public INode find(String word) {
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



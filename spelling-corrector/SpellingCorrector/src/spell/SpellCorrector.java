package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector {

    Trie dictionary = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        File file = new File(dictionaryFileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            dictionary.add(scanner.next());
        }
        scanner.close();
        suggestedWords.clear();
        suggestedSecond.clear();
    }

    Set<String> suggestedWords = new HashSet();
    Set<String> suggestedSecond = new HashSet();

    @Override
    public String suggestSimilarWord(String inputWord) {

        inputWord = inputWord.toLowerCase();

        if (dictionary.find(inputWord) != null) {
            //System.out.println("Found in Dictionary");
            return inputWord;
        } // first iteration
        //System.out.println("First Iteration");
        deletionSpellings(inputWord, suggestedWords);
        transpositionSpellings(inputWord, suggestedWords);
        alterationSpellings(inputWord, suggestedWords);
        insertionSpellings(inputWord, suggestedWords);

        //check if the first iteration matched
        String bestString = "";
        int bestCount = 0;
        for (String word : suggestedWords) {
            //System.out.println("SuggestedWords 1st len: " + suggestedWords.size());
            INode node = dictionary.find(word);
            if (node != null) {
                //compare edit distance
                //compare frequency (nodeCount?)
                //compare alphabetical order
                if (bestCount == node.getValue()) { //alpha
                    //sort alphabetically
                    // google how to alphabetically sort in java
                    int compare = bestString.compareTo(word);
                    if (compare > 0) {
                        bestString = word;
                    }
                }
                if (bestCount < node.getValue()) { // frequency check
                    bestCount = node.getValue();
                    bestString = word;
                }
            }
        }
        if (!bestString.equals("")) {
            return bestString;
        }

        // begin second iteration
        //System.out.println("Second Iteration");
        for (String word : suggestedWords) { //probable cause of infinite loop
            deletionSpellings(word, suggestedSecond);
            transpositionSpellings(word, suggestedSecond);
            alterationSpellings(word, suggestedSecond);
            insertionSpellings(word, suggestedSecond);
        }
        // second check
        bestString = "";
        bestCount = 0;
        for (String word : suggestedSecond) {
            INode node = dictionary.find(word);
            if (node != null) {
                //compare edit distance
                //compare frequency (nodeCount?)
                //compare alphabetical order
                if (bestCount == node.getValue()) { //alpha
                    //sort alphabetically
                    // google how to alphabetically sort in java
                    int compare = bestString.compareTo(word);
                    if (compare > 0) {
                        bestString = word;
                    }
                }
                if (bestCount < node.getValue()) { // frequency check
                    bestCount = node.getValue();
                    bestString = word;
                }
            }
        }
        if (!bestString.equals("")) {
            return bestString;
        } else {
            return null;
        }
    }

    private void deletionSpellings(String inputWord, Set set) {
        for (int i = 0; i < inputWord.length(); i++) {
            StringBuilder temp = new StringBuilder(inputWord);
            temp = temp.deleteCharAt(i);
            set.add(temp.toString());
        }
    }

    private void transpositionSpellings(String inputWord, Set set) {
        for (int i = 1; i < inputWord.length(); i++) {
            StringBuilder temp = new StringBuilder(inputWord);
            char tempChar = inputWord.charAt(i);
            temp.setCharAt(i, inputWord.charAt(i - 1));
            temp.setCharAt(i - 1,tempChar);
            set.add(temp.toString());
        }
    }

    private void alterationSpellings(String inputWord, Set set) {
        for (int i = 0; i < inputWord.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder temp = new StringBuilder(inputWord);
                temp.setCharAt(i, letter);
                set.add(temp.toString());
            }
        }
    }

    private void insertionSpellings(String inputWord, Set set) {
        for (int i = 0; i <= inputWord.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder temp = new StringBuilder(inputWord);
                temp.insert(i, letter);
                set.add(temp.toString());
            }
        }
    }
}

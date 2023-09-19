package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector {

    Trie dictionary;

    // TODO: constructors?

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        File file = new File(dictionaryFileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            dictionary.add(scanner.next());
        }
        scanner.close();
        suggestedWords.clear();
    }

    Set<String> suggestedWords = new HashSet();

    @Override
    public String suggestSimilarWord(String inputWord) {

        inputWord = inputWord.toLowerCase();
        // check if the word is already in the dictionary
        if (dictionary.find(inputWord) != null) {
            return inputWord;
        } // first iteration
        deletionSpellings(inputWord);
        transpositionSpellings(inputWord);
        alterationSpellings(inputWord);
        insertionSpellings(inputWord);

        //check if the first iteration matched
        String bestString = "";
        int bestCount = 0;
        for (String word : suggestedWords) {
            INode node = dictionary.find(word);
            if (node != null) {
                //compare edit distance
                //compare frequency (nodeCount?)
                //compare alphabetical order
                if (bestCount == node.getValue()) { //alpha
                    //sort alphabetically
                    // google how to alphabetically sort in java

                }
                if (bestCount < node.getValue()) { // frequency check
                    bestCount = node.getValue();
                    bestString = word;
                }
            }
        }

        //begin second iteration
            for (String word : suggestedWords) {
                deletionSpellings(word);
                transpositionSpellings(word);
                alterationSpellings(word);
                insertionSpellings(word);
            }

            for (String word : suggestedWords) {
                //TODO
            }
            return null;
    }

    private void deletionSpellings(String inputWord) {
        for (int i = 0; i < inputWord.length(); i++) {
            StringBuilder temp = new StringBuilder(inputWord);
            temp = temp.deleteCharAt(i);
            suggestedWords.add(temp.toString());
        }
    }

    private void transpositionSpellings(String inputWord) {
        for (int i = 1; i < inputWord.length(); i++) {
            StringBuilder temp = new StringBuilder(inputWord);
            char tempChar = inputWord.charAt(i);
            temp.setCharAt(i, inputWord.charAt(i - 1));
            temp.setCharAt(i - 1,tempChar);
            suggestedWords.add(temp.toString());
        }
    }

    private void alterationSpellings(String inputWord) {
        for (int i = 0; i < inputWord.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder temp = new StringBuilder(inputWord);
                temp.setCharAt(i, letter);
                suggestedWords.add(temp.toString());
            }
        }
    }

    private void insertionSpellings(String inputWord) {
        for (int i = 0; i <= inputWord.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder temp = new StringBuilder(inputWord);
                temp.insert(i, letter);
                suggestedWords.add(temp.toString());
            }
        }
    }
}

package spell;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector {

    Trie dictionary;

    // TODO: constructors?

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

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
        if (suggestedWords.contains(inputWord)) { //will this work with multiple?
            //TODO: suggested word
        } else { //begin second iteration
            for (String word : suggestedWords) {
                deletionSpellings(word);
                transpositionSpellings(word);
                alterationSpellings(word);
                insertionSpellings(word);
            }
        }
        if (suggestedWords.contains(inputWord)) {
           //TODO: suggested word
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

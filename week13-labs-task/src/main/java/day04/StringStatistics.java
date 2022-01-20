package day04;

import java.util.*;

public class StringStatistics {

    private final List<Character> vowels = new ArrayList<>(Arrays.asList('a','e','i','o','u'));

    public Map<Character, Integer> getNumberOfVowels (String text) {
        text = text.toLowerCase();
        char[] characters = text.toCharArray();
        Arrays.sort(characters);
        Map<Character, Integer> numberOfVowels = new HashMap<>();
        countVowels(characters, numberOfVowels);
        return numberOfVowels;
    }

    private void countVowels(char[] characters, Map<Character, Integer> numberOfVowels) {
        for (char c : characters) {
            if (numberOfVowels.containsKey(c) && vowels.contains(c)) {
                numberOfVowels.put(c, numberOfVowels.get(c) + 1);
            } else if (!numberOfVowels.containsKey(c) && vowels.contains(c)) {
                numberOfVowels.put(c, 1);
            }
        }
    }

    public static void main(String[] args) {
        StringStatistics stat = new StringStatistics();

        System.out.println(stat.getNumberOfVowels("This is a text in screen!"));
    }
}

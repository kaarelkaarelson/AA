package coding_tasks;

import java.util.*;

import static java.util.Arrays.*;
import static java.util.Collections.sort;

class Result {

    /*
     * Complete the 'getSearchResults' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY words
     *  2. STRING_ARRAY queries
     */

    public static List<List<String>> getSearchResults(List<String> words, List<String> queries) {
        List<List<String>> anagrams = new ArrayList<>(); // 2D result list.

        int queriesLenght = queries.size(); // Saving the lengths as constants for efficiency.
        int wordsLenght = words.size();

        // Iterate over all possible pairs between queries and words.
        for (int i = 0; i < queriesLenght; i++) {
            List<String> queryAnagrams = new ArrayList<>();
            anagrams.add(queryAnagrams);

            String query = queries.get(i);
            int queryLength = query.length();
            char[] queryChars = query.toCharArray();
            Arrays.sort(queryChars); // Sorting the char array to compare it with words.

            for (int j = 0; j < wordsLenght; j++) {

                // Two words cannot be anagrams if they don't have the same lenght.
                if (queryLength != words.get(j).length()) continue;

                String word = words.get(j);
                char[] wordChars = word.toCharArray();
                Arrays.sort(wordChars);

                // Compare if alphabetically sorted arrays contain exactly the same chars.
                if (Arrays.equals(queryChars, wordChars)) {
                    queryAnagrams.add(word);
                }
            }

            // Sort the current anagram list alphabetically
            if (queryAnagrams.size() > 1) {
                Collections.sort(queryAnagrams, new Comparator<String>() {
                    public int compare(String s1, String s2) {
                        return s1.compareTo(s2);
                    }
                });
            }
        }

        return anagrams;
    }

}


package FuzzySearch;

/**
 * @ author Leah Peschel
 * A class computing string similarity of two strings by calculating the edit distance
 */

public class EditDistance {

    /**
  //   * @param queryWord - char input from FuzzyHelper containing a fuzzy query word
  //   * @param indexedWord - char input from FuzzyHelper containing a word of the inverted index
     * @return distanceValue - returns an int describing the string similarity of both @param
     * @description - computes the minimum number of edits required to transform @param word1 to @param word2
     * covered transformational operations: insertion, deletion or/ and replacement
     * of any character at any position of the word
     */

    // https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/EditDistance.java

    public int getEditDistance(char[] queryWord, char[] indexedWord) {

        char[] longerWord;
        char[] shorterWord;

        if (queryWord.length < indexedWord.length) {
            longerWord = indexedWord;
            shorterWord = queryWord;
        } else {
            longerWord = queryWord;
            shorterWord = indexedWord;
        }

        int distanceValue[][] = new int[longerWord.length + 1][shorterWord.length + 1];


        for (int i = 0; i < distanceValue.length; i++) {
            distanceValue[i][0] = i;

        }

        for (int i = 1; i <= longerWord.length; i++) {
            for (int j = 1; j <= shorterWord.length; j++) {
                if (longerWord[i - 1] == shorterWord[j - 1]) {
                    distanceValue[i][j] = distanceValue[i - 1][j - 1];
                } else {
                    distanceValue[i][j] = 1 + min(distanceValue[i - 1][j - 1], distanceValue[i - 1][j], distanceValue[i][j - 1]);
                }
            }
        }
        return distanceValue[longerWord.length][shorterWord.length];

    }

    /**
     * @return the smallest value of three int input values.
     * @descritption computes the smallest value of three int in full matrix, respectively of a two-dimensional array,
     * when iterating over matrix in order to compute edit distance
    **/

    public int min(int a, int b, int c) {
        int l = Math.min(a, b);
        return Math.min(l, c);
    }

}


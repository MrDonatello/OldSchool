package net.thumbtack.school.base;

import java.util.Locale;

public class StringOperations {


    public static int getSummaryLength(String[] strings) {

        int sum = 0;
        for (String string : strings) {
            sum += string.length();
        }
        return sum;
    }

    public static String getFirstAndLastLetterString(String string) {

        return string.substring(0, 1) + string.substring(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {

        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {

        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {

        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {

        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {

        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {

        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {

        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {

        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {

        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {

        return string1 + string2;
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {

        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {

        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {

        int tmp = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : string1.toCharArray()) {
            if (c == string2.charAt(tmp)) {
                stringBuilder.append(c);
            } else {
                break;
            }
            tmp++;
            if (tmp >= string2.length()) {
                break;
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isPalindrome(String string) {

        boolean palindrome = true;
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - i - 1)) {
                palindrome = false;
                break;
            }
        }
        return palindrome;
    }

    public static boolean isPalindromeIgnoreCase(String string) {

        boolean palindrome = true;
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.toUpperCase().charAt(i) != string.toUpperCase().charAt(string.length() - i - 1)) {
                palindrome = false;
                break;
            }
        }
        return palindrome;
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {

        String palindrome = "";
        for (String string : strings) {
            if (isPalindromeIgnoreCase(string)) {
                if (string.length() > palindrome.length()) {
                    palindrome = string;
                }
            }
        }
        return palindrome;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {

        boolean substring = false;
        if (index + length <= string1.length() && index + length <= string2.length()) {
            if (string1.substring(index, index + length).equals(string2.substring(index, index + length))) {
                substring = true;
            }
        }
        return substring;
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2) {

        return string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2) {

        return string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {

        return isPalindromeIgnoreCase(string.replace(" ", ""));
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {

        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            if (i != array.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(String.format(Locale.ENGLISH, "%.2f", array[i]));
            if (i != array.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            if (i != array.length - 1) {
                str.append(",");
            }
        }
        return str;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(String.format(Locale.ENGLISH, "%.2f", array[i]));
            if (i != array.length - 1) {
                str.append(",");
            }
        }
        return str;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {

        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < positions.length; i++) {
            stringBuilder.delete(positions[i] - i, positions[i] - i + 1);
        }
        return stringBuilder;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {

        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < positions.length; i++) {
            stringBuilder.insert(positions[i] + i, characters[i]);
        }
        return stringBuilder;
    }
}

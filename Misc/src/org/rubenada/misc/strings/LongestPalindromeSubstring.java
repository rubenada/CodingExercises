package org.rubenada.misc.strings;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Given a String, return its longest palindromic substring (we'll consider palindrome substring must have at least 2 characters)
 */
public class LongestPalindromeSubstring {

    public static void main(String args[]) {
        String s1 = "ABCAXDDXAXX";
        String s2 = "ABCDE";
        String s3 = "ABCDEEDCBA";
        String s4 = "ABBA";
        String s5 = "ASDFVV";
        System.out.println("getLongestPalindromeSubstring(" + s1 + ")= " + getLongestPalindromeSubstring(s1));
        System.out.println("getLongestPalindromeSubstring(" + s2 + ")= " + getLongestPalindromeSubstring(s2));
        System.out.println("getLongestPalindromeSubstring(" + s3 + ")= " + getLongestPalindromeSubstring(s3));
        System.out.println("getLongestPalindromeSubstring(" + s4 + ")= " + getLongestPalindromeSubstring(s4));
        System.out.println("getLongestPalindromeSubstring(" + s5 + ")= " + getLongestPalindromeSubstring(s5));
    }

    public static String getLongestPalindromeSubstring (String s) {
        if (s == null)
            return null;

        if (s.length() <= 1)
            return null;

        if (isPalindrome(s))
            return s;

        for (int i=s.length()-1; i>=2; i--) {
            for (int j=0; j+i<=s.length();j++) {
                String subStr = s.substring(j,j+i);
                if (isPalindrome(subStr))
                    return subStr;
            }
        }

        return null;
    }

    private static boolean isPalindrome(String s) {
        for (int i=0; i<s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i))
                return false;
        }
        return true;
    }
}

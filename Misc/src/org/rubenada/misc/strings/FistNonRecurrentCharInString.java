package org.rubenada.misc.strings;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Given a String, return the first non-recurrent (i.e. appears only once) character in it; (or null if all characters are recurrent)
 */
public class FistNonRecurrentCharInString {

    public static void main(String args[]) {
        String s1 = "ABCAXDDXXX";
        String s2 = "ABCDE";
        String s3 = "ABCDEEDCBA";
        System.out.println("getFirstNonRecurrentChar(" + s1 + ")= " + getFirstNonRecurrentChar(s1));
        System.out.println("getFirstNonRecurrentChar(" + s2 + ")= " + getFirstNonRecurrentChar(s2));
        System.out.println("getFirstNonRecurrentChar(" + s3 + ")= " + getFirstNonRecurrentChar(s3));
    }

    public static Character getFirstNonRecurrentChar (String s) {
        if (s == null)
            return null;

        Set<Character> oneOccurrenceSet = new LinkedHashSet<>(); // LinkedHashSet to keep track of insertion order
        Set<Character> severalOccurrencesSet = new HashSet<>();

        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!severalOccurrencesSet.contains(c)) {
                if (oneOccurrenceSet.contains(c)) {
                    oneOccurrenceSet.remove(c);
                    severalOccurrencesSet.add(c);
                }
                else
                    oneOccurrenceSet.add(c);
            }
            // else --> character already in severalOccurrencesSet: no-op
        }

        if (oneOccurrenceSet.isEmpty())
            return null;

        return oneOccurrenceSet.iterator().next();
    }
}

package org.rubenada.misc.arrays;


/**
 * Given a 2D matrix of characters, check if a certain work can be found
 */
public class FindWordInMatrix {

    enum Direction {
        UP,
        UP_LEFT,
        UP_RIGHT,
        RIGHT,
        LEFT,
        DOWN,
        DOWN_LEFT,
        DOWN_RIGHT
    }

    public static boolean findWord (char[][] array, String word) {
        if (word == null || word.length() == 0)
            return false;

        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++){
                if (array[i][j]==word.charAt(0)) {
                    if (word.length() == 1)
                        return true;

                    if (i>=word.length()-1 && findWord(array, i-1, j, Direction.UP, word.substring(1)))
                        return true;
                    if (i>=word.length()-1 && j>=word.length()-1 && findWord(array, i-1, j-1, Direction.UP_LEFT, word.substring(1)))
                        return true;
                    if (i>=word.length()-1 && array[i].length-j>=word.length() && findWord(array, i-1, j+1, Direction.UP_RIGHT, word.substring(1)))
                        return true;
                    if (j>=word.length()-1 && findWord(array, i, j-1, Direction.LEFT, word.substring(1)))
                        return true;
                    if (array[i].length-j>=word.length() && findWord(array, i, j+1, Direction.RIGHT, word.substring(1)))
                        return true;
                    if (array.length-i>=word.length() && findWord(array, i+1, j, Direction.DOWN, word.substring(1)))
                        return true;
                    if (array.length-i>=word.length() && j>=word.length()-1 && findWord(array, i+1, j-1, Direction.DOWN_LEFT, word.substring(1)))
                        return true;
                    if (array.length-i>=word.length() && array[i].length-j>=word.length() && findWord(array, i+1, j+1, Direction.DOWN_RIGHT, word.substring(1)))
                        return true;
                }
            }
        }

        return false;
    }

    private static boolean findWord (char[][] array, int i, int j, Direction direction, String word) {
        if (word.length() == 0)
            return true;

        if (i<0 || i>=array.length)
            return false;
        if (j<0 || j>=array[i].length)
            return false;

        if (array[i][j]==word.charAt(0)) {
            switch (direction) {
                case UP:
                    return findWord(array, i-1, j, direction, word.substring(1));
                case UP_LEFT:
                    return findWord(array, i-1, j-1, direction, word.substring(1));
                case UP_RIGHT:
                    return findWord(array, i-1, j+1, direction, word.substring(1));
                case LEFT:
                    return findWord(array, i, j-1, direction, word.substring(1));
                case RIGHT:
                    return findWord(array, i, j+1, direction, word.substring(1));
                case DOWN:
                    return findWord(array, i+1, j, direction, word.substring(1));
                case DOWN_LEFT:
                    return findWord(array, i+1, j-1, direction, word.substring(1));
                case DOWN_RIGHT:
                    return findWord(array, i+1, j+1, direction, word.substring(1));
            }
        }
        return false;
    }

    public static void main(String args[]) {
        char[][] array = {{'e', 'm', 'x', 'g', 'g', 'a', 'e'},
                          {'v', 'c', 'c', 'd', 'e', 'f', 'a'},
                          {'e', 'r', 'a', 'a', 'a', 's', 's'},
                          {'e', 'f', 'a', 'g', 'x', 'm', 'e'},
                          {'x', 'a', 'g', 'g', 'm', 'k', 'u'},
                          {'s', 'a', 'a', 'a', 'a', 'a', 'e'},
                          {'m', 'm', 'm', 'x', 'e', 's', 'x'},
                          {'e', 'e', 'z', 'e', 'x', 's', 'e'},};

        String word = "game";
        System.out.println("findWord " + word + " = " + findWord(array, word));

    }

}

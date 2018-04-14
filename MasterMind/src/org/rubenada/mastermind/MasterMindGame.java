package org.rubenada.mastermind;

import java.util.Random;

/**
 * Class representing a MasterMind game
 * see https://en.wikipedia.org/wiki/Mastermind_(board_game)
 */
public class MasterMindGame {

    // valid colors: rouge (R), jaune (J), bleu (B), orange (O), vert (V), noir (N)
    static final String COLORS = "RJBOVN";

    static final int DEFAULT_MAX_ATTEMPTS = 10;
    static final int SOLUTION_LENGTH = 4;

    private final int maxAttempts;
    private int attempts;
    private String solution;

    private static Random random = new Random();

    private GAME_STATE gameState;
    public enum GAME_STATE {
        STARTED,
        WON,
        OVER
    }

    /**
     * Constructor: creates the master mind game and starts it
     */
    public MasterMindGame() {
        this.maxAttempts = DEFAULT_MAX_ATTEMPTS;
        startGame();
    }

    /**
     * Method to (re)start the game
     */
    public void startGame() {
        attempts = 0;
        solution = getRandomSolution();
        gameState = GAME_STATE.STARTED;
    }

    /**
     * Checks a certain guess against the solution
     * @param guess String containing the guess to be checked
     * @return a {@link MasterMindResult} containing the result of the guess
     * @throws MasterMindInvalidStateException if the game is in a state where no more guesses are allowed (i.e. WON or OVER)
     * @throws MasterMindInvalidArgumentException if the guess is not valid (null, wrong length, unknown characters)
     */
    public MasterMindResult checkGuess(String guess) throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        if (gameState != GAME_STATE.STARTED) // game state does not allow a new guess
            throw new MasterMindInvalidStateException();

        if (!validateInput(guess))
            throw new MasterMindInvalidArgumentException();

        attempts++;
        int[] res = compareGuessAndSolution(guess, solution);
        int corrects = res[0];
        int almostCorrects = res[1];

        // check if game is won
        if (corrects == SOLUTION_LENGTH) {
            gameState = GAME_STATE.WON;
            return new MasterMindResult(maxAttempts, attempts, corrects, almostCorrects, gameState);
        }

        // this was the last attempt
        if (attempts == maxAttempts)
            gameState = GAME_STATE.OVER;

        return new MasterMindResult(maxAttempts, attempts, corrects, almostCorrects, gameState);
    }

    /**
     * Checks a certain guess against a solution
     * @param guess String containing the guess
     * @param solution String containing the solution to be discovered
     * @return an array containing two integers: in the first position the number of correct colors, in the second the number of almost correct ones
     */
    static int[] compareGuessAndSolution(String guess, String solution) {
        int corrects=0; // right color and position
        int almostCorrects = 0; // right color, wrong position
        int[] frequencies = new int[COLORS.length()];

        // count corrects and calculate frequencies
        for (int i=0; i<SOLUTION_LENGTH; i++) {
            char guessChar = guess.charAt(i);
            char solutionChar = solution.charAt(i);
            if (guessChar == solutionChar)
                corrects++;
            else
                frequencies[COLORS.indexOf(solutionChar)]++;
        }

        // count almost corrects
        for (int i=0; i<SOLUTION_LENGTH; i++) {
            char guessChar = guess.charAt(i);
            char solutionChar = solution.charAt(i);
            if ((guessChar != solutionChar) && frequencies[COLORS.indexOf(guessChar)] > 0 ) {
                almostCorrects++;
                frequencies[COLORS.indexOf(guessChar)]--;
            }
        }

        return new int[]{corrects, almostCorrects};
    }

    /**
     * Validates if a certain input is a valid guess
     * @param guess String to be checked
     * @return true if the String is valid, false otherwise
     */
    static boolean validateInput(String guess) {
        if (guess == null)
            return false;
        if (guess.length() != SOLUTION_LENGTH)
            return false;
        for (int i=0; i<guess.length(); i++) {
            if (COLORS.indexOf(guess.charAt(i)) == -1)
                return false;
        }
        return true;
    }

    /**
     * @return a random solution for the game
     */
    static String getRandomSolution() {
        StringBuilder solution = new StringBuilder();

        for (int i=0; i<SOLUTION_LENGTH; i++)
            solution.append(COLORS.charAt(getRandom(0,COLORS.length())));

        return solution.toString();
    }

    /**
     * @return random number in between low (inclusive) and high (exclusive)
     */
    private static int getRandom(int low, int high) {
        return random.nextInt(high - low) + low;
    }

    /**
     * @return the current game state
     */
    public GAME_STATE getGameState() {
        return gameState;
    }

    // package-access methods, only for unit tests
    void setSolution(String solution){
        this.solution = solution;
    }
    void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    void setGameState(GAME_STATE gameState) {
        this.gameState = gameState;
    }
}

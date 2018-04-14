package org.rubenada.mastermind;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.rubenada.mastermind.MasterMindGame.COLORS;
import static org.rubenada.mastermind.MasterMindGame.DEFAULT_MAX_ATTEMPTS;
import static org.rubenada.mastermind.MasterMindGame.SOLUTION_LENGTH;
import org.rubenada.mastermind.MasterMindGame.GAME_STATE;

public class MasterMindGameTest {

    @Test
    public void getRandomSolutionTest() {
        for (int count=0; count<100; count++) {
            String solution = MasterMindGame.getRandomSolution();
            assertNotNull(solution);
            assertEquals(solution.length(), SOLUTION_LENGTH);
            for (int i=0; i<solution.length(); i++)
                assertTrue(COLORS.indexOf(solution.charAt(i)) != -1);
        }
    }

    @Test
    public void validateInputTest() {
        assertFalse(MasterMindGame.validateInput(null));
        assertFalse(MasterMindGame.validateInput(""));
        assertFalse(MasterMindGame.validateInput("RRR"));
        assertFalse(MasterMindGame.validateInput("bnbn"));
        assertFalse(MasterMindGame.validateInput("OOOOO"));
        assertFalse(MasterMindGame.validateInput("rbon"));
        assertFalse(MasterMindGame.validateInput("ABCDEF"));
        assertFalse(MasterMindGame.validateInput("W!&^()RV%&&"));

        // test some fixed valid combinations plus some random ones
        assertTrue(MasterMindGame.validateInput("RRRR"));
        assertTrue(MasterMindGame.validateInput("BNBN"));
        assertTrue(MasterMindGame.validateInput("OOJJ"));
        assertTrue(MasterMindGame.validateInput("RVNJ"));
        assertTrue(MasterMindGame.validateInput("JRVO"));
        assertTrue(MasterMindGame.validateInput("BRJO"));
        assertTrue(MasterMindGame.validateInput("NNVV"));
        assertTrue(MasterMindGame.validateInput("VNOB"));
        assertTrue(MasterMindGame.validateInput("JOBV"));
        assertTrue(MasterMindGame.validateInput("NNOB"));
        for (int count=0; count<100; count++)
            assertTrue(MasterMindGame.validateInput(MasterMindGame.getRandomSolution()));
    }

    @Test
    public void compareGuessAndSolutionTest() {
        int[] res;
        res = MasterMindGame.compareGuessAndSolution("OOOO", "OOOO");
        assertEquals(4, res[0]);
        assertEquals(0, res[1]);

        res = MasterMindGame.compareGuessAndSolution("BRJO", "BRJO");
        assertEquals(4, res[0]);
        assertEquals(0, res[1]);

        res = MasterMindGame.compareGuessAndSolution("RJBO", "NNVV");
        assertEquals(0, res[0]);
        assertEquals(0, res[1]);

        res = MasterMindGame.compareGuessAndSolution("VNOB", "BVNO");
        assertEquals(0, res[0]);
        assertEquals(4, res[1]);

        res = MasterMindGame.compareGuessAndSolution("VNOB", "BVRO");
        assertEquals(0, res[0]);
        assertEquals(3, res[1]);

        res = MasterMindGame.compareGuessAndSolution("ROJR", "RJOB");
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);

        res = MasterMindGame.compareGuessAndSolution("RRNN", "RRRN");
        assertEquals(3, res[0]);
        assertEquals(0, res[1]);

        res = MasterMindGame.compareGuessAndSolution("ORJV", "RROO");
        assertEquals(1, res[0]);
        assertEquals(1, res[1]);

        res = MasterMindGame.compareGuessAndSolution("ORJV", "ORVJ");
        assertEquals(2, res[0]);
        assertEquals(2, res[1]);
    }

    @Test
    public void checkGuessTest() throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        MasterMindGame game = new MasterMindGame();
        assertEquals(GAME_STATE.STARTED, game.getGameState());

        String solution = "BJNO";
        String notSolution = "JBOO";
        game.setSolution(solution);
        MasterMindResult result;
        for (int i=1; i<DEFAULT_MAX_ATTEMPTS; i++) { // perform DEFAULT_MAX_ATTEMPTS-1 wrong attempts
            result = game.checkGuess(notSolution);
            assertEquals(i, result.getAttempts());
            assertEquals(DEFAULT_MAX_ATTEMPTS, result.getMaxAttempts());
            assertEquals(1, result.getCorrects());
            assertEquals(2, result.getAlmostCorrects());
            assertEquals(GAME_STATE.STARTED, result.getGameState());
            assertEquals(GAME_STATE.STARTED, game.getGameState());
        }
        result = game.checkGuess(notSolution);  // perform the last wrong attempts
        assertEquals(DEFAULT_MAX_ATTEMPTS, result.getAttempts());
        assertEquals(DEFAULT_MAX_ATTEMPTS, result.getMaxAttempts());
        assertEquals(GAME_STATE.OVER, result.getGameState());
        assertEquals(GAME_STATE.OVER, game.getGameState());
        assertEquals(1, result.getCorrects());
        assertEquals(2, result.getAlmostCorrects());

        game.startGame(); // re-start the game and perform one correct attempt
        assertEquals(GAME_STATE.STARTED, game.getGameState());
        game.setSolution(solution);
        result = game.checkGuess(solution);
        assertEquals(1, result.getAttempts());
        assertEquals(DEFAULT_MAX_ATTEMPTS, result.getMaxAttempts());
        assertEquals(GAME_STATE.WON, result.getGameState());
        assertEquals(GAME_STATE.WON, game.getGameState());
        assertEquals(4, result.getCorrects());
        assertEquals(0, result.getAlmostCorrects());
    }

    @Test(expected = MasterMindInvalidArgumentException.class)
    public void checkGuessTest_invalidArgumentNull() throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        MasterMindGame game = new MasterMindGame();
        game.checkGuess(null);
    }

    @Test(expected = MasterMindInvalidArgumentException.class)
    public void checkGuessTest_invalidArgumentEmpty() throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        MasterMindGame game = new MasterMindGame();
        game.checkGuess("");
    }

    @Test(expected = MasterMindInvalidArgumentException.class)
    public void checkGuessTest_invalidArgumentWrong() throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        MasterMindGame game = new MasterMindGame();
        game.checkGuess("QWERTY");
    }

    @Test(expected = MasterMindInvalidStateException.class)
    public void checkGuessTest_invalidStateOver() throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        MasterMindGame game = new MasterMindGame();
        game.setGameState(GAME_STATE.OVER);
        game.checkGuess("BNOJ");
    }

    @Test(expected = MasterMindInvalidStateException.class)
    public void checkGuessTest_invalidStateWon() throws MasterMindInvalidStateException, MasterMindInvalidArgumentException {
        MasterMindGame game = new MasterMindGame();
        game.setGameState(GAME_STATE.WON);
        game.checkGuess("BNOJ");
    }
}
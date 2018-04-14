package org.rubenada.mastermind;

/**
 * Auxiliary class that represents the result of checking a guess in MasterMindGame
 */
public class MasterMindResult {
    private final int maxAttempts;    // number of max attempts that can be tried
    private final int attempts;       // number of attempts that have been used
    private final int corrects;       // number of correct colors (right color and position)
    private final int almostCorrects; // number of almost correct colors (right color, wrong position)
    private final MasterMindGame.GAME_STATE gameState; // game state

    public MasterMindResult(int maxAttempts, int attempts, int corrects, int almostCorrects, MasterMindGame.GAME_STATE gameState) {
        this.maxAttempts = maxAttempts;
        this.attempts = attempts;
        this.corrects = corrects;
        this.almostCorrects = almostCorrects;
        this.gameState = gameState;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getCorrects() {
        return corrects;
    }

    public int getAlmostCorrects() {
        return almostCorrects;
    }

    public MasterMindGame.GAME_STATE getGameState() {
        return gameState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(corrects).append(" | ").append(almostCorrects).append(" | ").append(attempts).append("/").append(maxAttempts);
        switch (gameState) {
            case WON:
                sb.append(" CONGRATULATIONS, YOU HAVE WON ");
                break;
            case OVER:
                sb.append(" GAME OVER ");
                break;
        }
        return sb.toString();
    }
}

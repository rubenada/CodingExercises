package org.rubenada.main;

import org.rubenada.mastermind.MasterMindGame;
import org.rubenada.mastermind.MasterMindInvalidArgumentException;
import org.rubenada.mastermind.MasterMindInvalidStateException;
import org.rubenada.mastermind.MasterMindResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class with an infinite loop to play MasterMind until the user quits
 */
public class MasterMindMain {
    public static void main (String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
        MasterMindGame game = new MasterMindGame();

        System.out.println("--------- Welcome to MasterMind ---------");
        while (true) {
            try {
                System.out.println("Enter your guess of 4 colors: rouge (R), jaune (J), bleu (B), orange (O), vert (V), noir (N) [Q to quit]...");
                input = in.readLine();
                if ("Q".equalsIgnoreCase(input))
                    return;

                MasterMindResult result = game.checkGuess(input);
                System.out.println(input + ": " + result.toString());
                if (result.getGameState() != MasterMindGame.GAME_STATE.STARTED) { // re-start when game is won or over
                    game.startGame();
                    System.out.println("--------- New Game ---------");
                }

            } catch (MasterMindInvalidArgumentException e) {
                System.out.println("Please insert a valid sequence of colors");
            } catch (MasterMindInvalidStateException e) {
                System.out.println("Error, game is an invalid state"); // should not happen
            } catch (IOException e) {
                System.out.println("Error while processing the input");
            }
        }
    }
}

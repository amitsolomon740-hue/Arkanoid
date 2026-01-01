// 216700930 Amit Solomon

import Game.Game;

/**
 * The Ass3Game class contains the main method to run the game.
 * This class is responsible for initializing and starting the game.
 */
public class Ass5Game {
    /**
     * The main method to start the game.
     * Creates a new Game.Game, initializes it, and runs the game loop.
     *
     * @param args command-line arguments (not used in this game).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();

    }
}

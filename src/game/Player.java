package game;

import javafx.scene.control.TextField;

public interface Player {
    /**
     * Takes in the TextField to see if the move is allowed to be made
     * @param textField the box of where the player wants to play
     * @return returns true if it is a valid move false if not
     */
    boolean makeMove(TextField textField);

    /**
     * Easy way to return name in a readable format
     * @return the name of the player based on the class
     */
    String toString();
}

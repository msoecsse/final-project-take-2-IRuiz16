package game;

import javafx.scene.control.TextField;

public interface Player {
    boolean makeMove(TextField textField);
    String toString();
}

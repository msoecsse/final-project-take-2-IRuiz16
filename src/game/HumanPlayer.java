package game;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HumanPlayer implements Player{
    private final String piece;

    public HumanPlayer(String piece){
        this.piece = piece;
    }
    @Override
    public boolean makeMove(TextField textField) {
        return textField.getText().isEmpty();
    }
    public String getPiece(){
        return piece;
    }

    @Override
    public String toString(){
        return "Player 1";
    }
}

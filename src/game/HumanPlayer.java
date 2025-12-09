package game;

import javafx.scene.control.TextField;

public class HumanPlayer implements Player{
    private final String piece;

    public HumanPlayer(String piece){
        this.piece = piece;
    }
    @Override
    public boolean makeMove(TextField textField) {
        return textField.getText().isEmpty();
    }
    @Override
    public String toString(){
        return "Player 1";
    }
    public String getPiece(){
        return piece;
    }
}

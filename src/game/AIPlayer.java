package game;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AIPlayer implements Player{
    private String piece;
    private GridPane pane;

    public AIPlayer(String piece, GridPane pane){
        this.piece = piece;
        this.pane = pane;
    }

    @Override
    public boolean makeMove(TextField textField) {
        int randomMove = (int) (Math.random() * 9) + 1;
        //place this in one of the boxes 1-9

        return false;
    }

    @Override
    public String getPiece() {
        return piece;
    }
}

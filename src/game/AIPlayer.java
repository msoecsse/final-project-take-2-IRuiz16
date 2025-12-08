package game;

import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AIPlayer implements Player{
    private String piece;
    private GridPane pane;
    private GameProxy proxy;


    public AIPlayer(String piece, GridPane pane, GameProxy proxy){
        this.piece = piece;
        this.pane = pane;
        this.proxy = proxy;
    }

    @Override
    public boolean makeMove(TextField textField) {
        boolean played = false;
        if(textField.getText().isEmpty()){
            proxy.mark(textField, piece);
            played = true;
        }
        return played;
    }
    //need logic that will justify how the aiPlayer will make its move

    @Override
    public String getPiece() {
        return piece;
    }
}

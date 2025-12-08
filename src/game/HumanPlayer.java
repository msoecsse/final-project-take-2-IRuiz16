package game;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HumanPlayer implements Player{
    private String piece;
    private GameProxy proxy;
    private GridPane pane;

    public HumanPlayer(String piece, GridPane pane, GameProxy proxy){
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
    @Override
    public String getPiece(){
        return piece;
    }
}

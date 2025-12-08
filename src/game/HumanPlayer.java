package game;

import javafx.scene.control.TextField;

public class HumanPlayer implements Player{
    private final String piece;
    private final GameProxy proxy;

    public HumanPlayer(String piece, GameProxy proxy){
        this.piece = piece;
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

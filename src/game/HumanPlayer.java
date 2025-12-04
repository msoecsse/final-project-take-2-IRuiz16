package game;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HumanPlayer implements Player{
    private String piece;
    private GameProxy proxy;
    private GridPane pane;

    public HumanPlayer(String piece, GridPane pane){
        this.piece = piece;
        this.pane = pane;
        this.proxy = new GameProxy(600, 600, pane);

    }
    @Override
    public boolean makeMove(TextField textField) {
        boolean played = false;
        GameController controller = proxy.getGameController();
        if(textField.getText().isEmpty()){
            controller.mark(controller.getMouseEvent()); //mark doesn't return anything but there is a getCoordinates method that should work
            played = true;
        }
        System.out.println("WORKED?");
        //move was made so now need to validate it - send back to proxy
        return played;
    }
    @Override
    public String getPiece(){
        return piece;
    }
}

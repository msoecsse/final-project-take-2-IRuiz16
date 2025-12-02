package game;

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
    public boolean makeMove() {
        GameController controller = proxy.getGameController();
        controller.mark(controller.getMouseEvent()); //mark() returns grid points (col, row)
        System.out.println("WORKED?");
        //move was made so now need to validate it - send back to proxy
        return true;
    }
    @Override
    public String getPiece(){
        return piece;
    }
}

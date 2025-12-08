package game;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

public class GameController {
    @FXML
    private GridPane gridPane;

    private GameProxy gameProxy;

    @FXML
    public void initialize(){
        gameProxy = new GameProxy(gridPane);
        gameProxy.start();
    }
    @FXML
    public void mark(MouseEvent event){
        if(gameProxy != null){
            gameProxy.handleHumanPlay(event);
        }
    }
}

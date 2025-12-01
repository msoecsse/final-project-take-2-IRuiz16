package game;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

public class GameController {

    @FXML
    private TextField topLeft;
    @FXML
    private TextField topMiddle;
    @FXML
    private TextField topRight;
    @FXML
    private TextField middleLeft;
    @FXML
    private TextField middleMiddle;
    @FXML
    private TextField middleRight;
    @FXML
    private TextField bottomLeft;
    @FXML
    private TextField bottomMiddle;
    @FXML
    private TextField bottomRight;
    @FXML
    private GridPane gridPane;

    private GameProxy gameProxy;

    @FXML
    public void initialize(){
        gameProxy = new GameProxy(600, 600, gridPane);
        gameProxy.popUp(); // allows user to pick x or o

    }
    @FXML
    public void mark(MouseEvent event){
        System.out.println("boo");
    }

}

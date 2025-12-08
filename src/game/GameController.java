package game;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

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
        gameProxy = new GameProxy(600, 600, gridPane, this);
        gameProxy.start();
    }
    @FXML
    public void mark(MouseEvent event){
        if(gameProxy != null){
            gameProxy.handleMark(event);
        }
    }

    //check if box is empty
    public boolean emptyBox(TextField textField){
        return textField.getText().isEmpty();
    }
}

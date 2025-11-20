package game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public void mark(){
        System.out.println("BOO");
    }
}

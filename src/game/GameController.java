package game;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
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
    private MouseEvent event;
    private Point2D coordinates;

    @FXML
    public void initialize(){
        gameProxy = new GameProxy(600, 600, gridPane);
        gameProxy.start();
    }
    @FXML
    public void mark(MouseEvent event){
        this.event = event;
        System.out.println("boo");

        Node clicked = (Node) event.getSource();

        Integer col = GridPane.getColumnIndex(clicked) == null ? 0 : GridPane.getColumnIndex(clicked);
        Integer row = GridPane.getRowIndex(clicked) == null ? 0 : GridPane.getRowIndex(clicked);

        coordinates = new Point2D(col, row);
    }

    public boolean canMakeMove(Point2D coordinates){
        return coordinates == null;
    }

    public Point2D getCoordinate(){
     return coordinates;
    }

    public MouseEvent getMouseEvent(){
        return event;
    }

    protected GameProxy getGameProxy() {
        return gameProxy;
    }
}

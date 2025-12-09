package game;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class GameController {
    @FXML
    private GridPane gridPane;
    private GameProxy gameProxy;
    private String playerChoice;
    private String player2Choice;

    @FXML
    public void initialize(){
        gameProxy = new GameProxy(gridPane, this);
        startPopUp();
        gameProxy.start();

    }
    @FXML
    public void mark(MouseEvent event){
        if(gameProxy != null){
            TextField clicked = (TextField) event.getSource();
            gameProxy.makeMove(clicked);
        }
    }

    public String getPlayerChoice(){
        return playerChoice;
    }

    public String getPlayer2Choice(){
        return player2Choice;
    }

    private void startPopUp(){
        //get this choice and set as players choice
        Dialog<ButtonType> popup = new Dialog<>();
        ButtonType x = new ButtonType("X");
        ButtonType o = new ButtonType("O");
        popup.setTitle("Player 1: Pick X or O");

        popup.getDialogPane().getButtonTypes().addAll(x, o);
        Optional<ButtonType> choice = popup.showAndWait();

        if(choice.isPresent()){
            ButtonType button = choice.get();
            playerChoice = button.getText();
            player2Choice = playerChoice.equals("X") ? "O" : "X";
        }
    }
}


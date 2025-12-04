package game;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Optional;

//will hold the logic to all of the game interacts with the player and the controller to figure what needs to be done
public class GameProxy {
    private Player humanPlayer;
    private Player aiplayer;
    private GameController gameController;
    private int height;
    private int width;
    private GridPane gamePane;
    private String playerChoice;
    private String player2Choice;

    public GameProxy(int height, int width, GridPane gamePane){ //idk if we need height and width yet but doesn't hurt to have
        this.height = height;
        this.width = width;
        this.gamePane = gamePane;
        this.gameController = new GameController();
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
    private void message(String message){
        PauseTransition pause = new PauseTransition(Duration.millis(1));
        pause.setOnFinished(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MESSAGE");
            alert.setContentText(message);
            alert.show();
        });
        pause.play();
    }

    public void start(){
        startPopUp();
        this.humanPlayer = new HumanPlayer(playerChoice, gamePane);
        this.aiplayer = new AIPlayer(player2Choice, gamePane);
        //does the logic for the player to make a move
        if(humanPlayer.getPiece().equalsIgnoreCase("x")){
            message("Player 1 starts");
            makeMove(humanPlayer);
        } else{
            message("AI player starts");
            makeMove(aiplayer);
        }
    }

    private void makeMove(Player player){
        for (Node node : gamePane.getChildren()) {
            node.setOnMouseClicked(event -> {
                TextField clicked = (TextField) event.getSource();
                if(clicked.getText().isEmpty()){
                    player.makeMove(clicked);
                    //after move has been validated and made display the change onto the box
                    //will need to get the coordinates and (maybe) have a boolean where it moveMade true/false
                    //idk just have something that will go to the controller to update it
                } else{
                    message("Can't make move!\nBox already has been played!");
                }
            });
        }
    }

    public GameController getGameController(){
        return gameController;
    }

    public Pane getGamePane(){
        return gamePane;
    }
}

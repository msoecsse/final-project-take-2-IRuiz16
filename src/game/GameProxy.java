package game;

import javafx.animation.PauseTransition;
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
    }

    private void popUp(){
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
        popUp();
        this.humanPlayer = new HumanPlayer(playerChoice, gamePane);
        this.aiplayer = new AIPlayer(player2Choice, gamePane);
        message("Player 1 starts");

    }

    public GameController getGameController(){
        return gameController;
    }

    public boolean validMove(TextField box) {
        return !box.getText().isEmpty();
    }

    public Pane getGamePane(){
        return gamePane;
    }
    /**
     * Validates the players turn
     * @return
     */
    protected boolean turn(){
        //want it to keep flopping back and forth
        //need a method or variable that keeps track who went last
        return false;
    }

}

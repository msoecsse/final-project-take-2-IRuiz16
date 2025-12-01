package game;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Optional;

//will hold the logic to all of the game interacts with the player and the controller to figure what needs to be done
public class GameProxy {
    private Player player;
    private Player aiPlayer;
    private GameController gameController;
    private int height;
    private int width;
    private Pane gamePane;
    private String playerChoice;
    private String aiChoice;

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
        popup.setTitle("Pick X or O");

        popup.getDialogPane().getButtonTypes().addAll(x, o);
        Optional<ButtonType> choice = popup.showAndWait();

        if(choice.isPresent()){
            ButtonType button = choice.get();
            playerChoice = button.getText();
            aiChoice = playerChoice.equals("X") ? "O" : "X";
        }
    }

    public void start(){
        popUp();
        this.player = new HumanPlayer(playerChoice);
        this.aiPlayer = new AIPlayer(aiChoice);
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

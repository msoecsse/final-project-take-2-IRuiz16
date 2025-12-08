package game;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Optional;

public class GameProxy {
    private Player humanPlayer;
    private Player aiplayer;
    private GameController gameController;
    private int height;
    private int width;
    private GridPane gridPane;
    private String playerChoice;
    private String player2Choice;
    private boolean turn;

    public GameProxy(int height, int width, GridPane gamePane, GameController gameController){
        this.height = height;
        this.width = width;
        this.gridPane = gamePane;
        this.gameController = gameController;
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
        this.humanPlayer = new HumanPlayer(playerChoice, gridPane, this);
        this.aiplayer = new AIPlayer(player2Choice, gridPane, this);
        //does the logic for the player to make a move
        if(humanPlayer.getPiece().equalsIgnoreCase("x")){
            message("Player 1 starts");
        } else{
            //if false player takes a turn
            message("AI player starts");
        }
        //once we have it going we can keep playing
    }


    public void handleMark(MouseEvent event){
        TextField clicked = (TextField) event.getSource();
        if(clicked.getText().isEmpty()){
            if(turn && humanPlayer != null){
                boolean played = humanPlayer.makeMove(clicked);
                if(played){
                    turn = false;
                    // TODO: After move, check for win, then switch to AI turn
                    checkWin(clicked);
                }
            } else if(!turn && aiplayer != null){
                boolean played = aiplayer.makeMove(clicked);
                if(played){
                    turn = true; // Switch turn back to player
                }
            } else {
                message("Not your turn!");
            }
        } else{
            message("Can't make move!\nBox already has been played!");
        }
    }

    public void mark(TextField textField, String piece){
        textField.setText(piece);
    }
    private String[][] getBoard(GridPane gridPane) {
        String[][] board = new String[3][3];

        for (Node node : gridPane.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);

            int row = (r == null) ? 0 : r;
            int col = (c == null) ? 0 : c;

            TextField cell = (TextField) node;
            board[row][col] = cell.getText();  // "" if empty, "X" or "O"
        }

        return board;
    }
    private void checkWin(TextField textField){
        //need to check if there are 3 boxes in a row to win
        //can iterate through each box in the gridpane
        String[][] board = getBoard(gridPane);

        if()

    }
    public GameController getGameController(){
        return gameController;
    }
}

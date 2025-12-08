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
        boolean isWinner = false;
        String winner = "";
        do {
            TextField clicked = (TextField) event.getSource();
            if (clicked.getText().isEmpty()) {
                if (turn && humanPlayer != null) {
                    boolean played = humanPlayer.makeMove(clicked);
                    if (played) {
                        turn = false;
                        // TODO: After move, check for win, then switch to AI turn
                        if (checkWin(clicked)) {
                            isWinner = true;
                            winner = "You";
                            message(winner + " is the winner");
                        }
                    }
                } else if (!turn && aiplayer != null) {
                    boolean played = aiplayer.makeMove(clicked);
                    if (played) {
                        turn = true; // Switch turn back to player
                        if (checkWin(clicked)) {
                            isWinner = true;
                            winner = "AIPlayer";
                            message(winner + " is the winner");
                        }
                    }
                } else {
                    message("Not your turn!");
                }
            } else {
                message("Can't make move!\nBox already has been played!");
            }
        }while(isWinner);
    }

    public void mark(TextField textField, String piece){
        textField.setText(piece);
    }
    //makes the board into a 2d array
    private String[][] getBoard(GridPane gridPane) {
        String[][] board = new String[3][3];

        for (Node node : gridPane.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);

            int row = (r == null) ? 0 : r;
            int col = (c == null) ? 0 : c;

            TextField cell = (TextField) node;
            board[row][col] = cell.getText();
        }
        return board;
    }
    private boolean checkWin(TextField textField){
        //need to check if there are 3 boxes in a row to win
        //can iterate through each box in the gridpane
        String[][] board = getBoard(gridPane);
        boolean win = false;

        if((!board[0][0].isEmpty() && board[0][0].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[2][2])) ||
                (!board[2][0].isEmpty() && board[2][0].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[0][2]))){
            //check diagonals
            win = true;
        } else if((!board[0][0].isEmpty() && board[0][0].equalsIgnoreCase(board[1][0]) && board[1][0].equalsIgnoreCase(board[2][0])) ||
                (!board[0][1].isEmpty() && board[0][1].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[2][1])) ||
                (!board[0][2].isEmpty() && board[0][2].equalsIgnoreCase(board[1][2]) && board[1][2].equalsIgnoreCase(board[2][2]))){
            //check columns
            win = true;
        } else if((!board[0][0].isEmpty() && board[0][0].equalsIgnoreCase(board[0][1]) && board[0][1].equalsIgnoreCase(board[0][2])) ||
                (!board[1][0].isEmpty() && board[1][0].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[1][2])) ||
                (!board[2][0].isEmpty() && board[2][0].equalsIgnoreCase(board[2][1]) && board[2][1].equalsIgnoreCase(board[2][2]))){
            //check rows
            win = true;
        }
        return win;

    }
    public GameController getGameController(){
        return gameController;
    }
}

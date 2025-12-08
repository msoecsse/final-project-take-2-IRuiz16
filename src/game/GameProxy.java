package game;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Optional;

public class GameProxy {
    private Player humanPlayer;
    private Player aiplayer;
    private final GridPane gridPane;
    private String playerChoice;
    private String player2Choice;
    private boolean turn;
    private boolean winner;

    public GameProxy(GridPane gamePane){
        this.gridPane = gamePane;
    }

    public void start(){
        startPopUp();
        this.humanPlayer = new HumanPlayer(playerChoice, this);
        this.aiplayer = new AIPlayer(player2Choice, gridPane, this);
        if(humanPlayer.getPiece().equalsIgnoreCase("x")){
            message("Player 1 starts");
            turn = true;
        } else{
            message("AI player starts");
            turn = false;
            AiPlay();
        }
    }

    public void handleHumanPlay(MouseEvent event) {
        TextField clicked = (TextField) event.getSource();
        if(!clicked.getText().isEmpty()){
            message("Can't make move!\nBox already has been played!");
        }

        boolean played = humanPlayer.makeMove(clicked);
        if(clicked.getText().isEmpty()) {
            if(turn && played) {
                turn = false;
                if (checkWin()) {
                    message("You are the winner");
                }
            } else {
                message("Not your turn!");
            }
        }
        AiPlay();
    }

    private void AiPlay(){
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            boolean play = aiplayer.makeMove(null);
            if(play){
                turn = true;
                if(checkWin()){
                    message("AiPlayer is the winner");
                }
            }
        });
        pause.play();
    }

    public void mark(TextField textField, String piece){
        textField.setText(piece);
    }

    public String[][] getBoard(GridPane gridPane) {
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

    private boolean checkWin(){
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
}

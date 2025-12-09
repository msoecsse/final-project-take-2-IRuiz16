package game;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class GameProxy implements Player{
    private final GridPane gridPane;
    private final GameController gameController;
    private Player humanPlayer;
    private Player aiplayer;
    private Player winner;
    private boolean turn;
    private boolean done;
    private int iterations;

    public GameProxy(GridPane gamePane, GameController gameController){
        this.gridPane = gamePane;
        this.gameController = gameController;
        this.done = false;
        this.iterations = 0;
    }

    public void start(){
        this.humanPlayer = new HumanPlayer(gameController.getPlayerChoice());
        this.aiplayer = new AIPlayer(gameController.getPlayer2Choice(), gridPane);
        if(gameController.getPlayerChoice().equalsIgnoreCase("x")){
            message("Player 1 starts");
            turn = true;
        } else{
            message("AI player starts");
            turn = false;
            aiPlay();
        }
    }

    @Override
    public boolean makeMove(TextField textField) {
        if(!done){
            if(turn){
                if(!textField.getText().isEmpty()){
                    message("Can't make move!\nBox already has been played!");
                }
                boolean played = humanPlayer.makeMove(textField);
                if(played){
                    turn = false;
                    mark(textField, ((HumanPlayer)humanPlayer).getPiece());

                    if(checkWin(humanPlayer, iterations++)){
                        message(winner.toString() + " is the winner");
                        done = true;
                    }
                    if(!done){
                        aiPlay();
                    }
                }
                return played;
            } else {
                TextField aiBox = ((AIPlayer) aiplayer).getAiBox();

                boolean played = aiplayer.makeMove(aiBox);
                if(played){
                    turn = true;
                    mark(aiBox, ((AIPlayer)aiplayer).getPiece());
                    if(checkWin(aiplayer, iterations++)){
                        message(winner.toString() + " is the winner");
                    }
                }
                return played;
            }
        }
        return done;
    }
    
    private void aiPlay(){
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        if(!done && !turn){
            pause.setOnFinished(e -> {
                makeMove(null);
            });
            pause.play();
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
            board[row][col] = cell.getText();
        }
        return board;
    }

    private boolean checkWin(Player player, int n){
        String[][] board = getBoard(gridPane);
        boolean win = false;
        if (n < 8) {
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
        } else{
            message("It is a draw");
            done = true;
        }
        if(win){
            winner = player;
            done = true;
        }
        return win;
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

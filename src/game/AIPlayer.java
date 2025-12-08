package game;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer implements Player{
    private final String piece;
    private final  GridPane pane;
    private final GameProxy proxy;


    public AIPlayer(String piece, GridPane pane, GameProxy proxy){
        this.piece = piece;
        this.pane = pane;
        this.proxy = proxy;
    }

    @Override
    public boolean makeMove(TextField textField) {
        boolean played = false;
        textField = getAiBox();
        // Get a random empty box for AI to play
        if(textField.getText().isEmpty()){
            proxy.mark(textField, piece);
            played = true;
        }
        return played;
    }

    @Override
    public String getPiece() {
        return piece;
    }

    public TextField getAiBox(){
        List<TextField> emptyBoxes = new ArrayList<>();
        
        for (Node node : pane.getChildren()) {
            if (node instanceof TextField) {
                TextField field = (TextField) node;
                if (field.getText().isEmpty()) {
                    emptyBoxes.add(field);
                }
            }
        }
        
        if (emptyBoxes.isEmpty()) {
            return null;
        }
        
        int randomIndex = (int) (Math.random() * emptyBoxes.size());
        return emptyBoxes.get(randomIndex);
    }
}

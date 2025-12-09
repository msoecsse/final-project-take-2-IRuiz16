package game;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer implements Player{
    private final String piece;
    private final GridPane gridPane;

    public AIPlayer(String piece, GridPane gridPane){
        this.piece = piece;
        this.gridPane = gridPane;
    }

    @Override
    public boolean makeMove(TextField textField) {
        boolean played = false;
        if(textField == null){
            textField = getAiBox();
        }
        if(textField != null && textField.getText().isEmpty()){
            played = true;
        }
        return played;
    }

    @Override
    public String toString(){
        return "AI Player";
    }

    public String getPiece(){
        return piece;
    }

    /**
     * Helps choose where the bot will be playing its piece
     * @return returns the TextField that was empty
     */
    public TextField getAiBox(){
        List<TextField> emptyBoxes = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
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

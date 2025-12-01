package game;

public class HumanPlayer implements Player{
    private String piece;

    public HumanPlayer(String piece){
        this.piece = piece;
    }
    @Override
    public void makeMove() {

    }
    @Override
    public String getPiece(){
        return piece;
    }
}

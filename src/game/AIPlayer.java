package game;

public class AIPlayer implements Player{
    private String piece;

    public AIPlayer(String piece){
        this.piece = piece;
    }
    @Override
    public void makeMove() {
        //have it be random for now
    }
    @Override
    public String getPiece(){
        return piece;
    }
}

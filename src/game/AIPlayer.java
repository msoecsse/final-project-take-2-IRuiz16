package game;

public class AIPlayer implements Player{
    private String xo;

    public AIPlayer(String xo){
        this.xo = xo;
    }
    @Override
    public void makeMove() {
        //have it be random for now
    }
}

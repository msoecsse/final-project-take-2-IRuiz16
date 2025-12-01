package game;

public interface Player {
    /**
     * Sends the move of what hte player wants to do sow we
     * want the box the player is placing in and we need
     * to send it to the proxy from the proxy we want to
     * verify it can be made otherwise display that move was invalid
     */
    void makeMove();

    /**
     * Get the piece of the player
     * @return the piece of the player
     */
    String getPiece();
}

package boardgame;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board){
        this.board = board;
        this.position = null;
    }

    public boolean[][] possibleMoves(){
        return new boolean[1][2];
    }

    public boolean possibleMove(Position position){
        return false;
    }

    public boolean isThereAnyPossibleMove(){
        return false;
    }

    protected Board getBoard(){
        return board;
    }
}

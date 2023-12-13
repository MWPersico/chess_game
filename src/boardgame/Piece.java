package boardgame;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board){
        this.board = board;
        this.position = null;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        for(boolean[] row : possibleMoves()){
            for(boolean move : row){
                if(move)return true;
            }
        }
        return false;
    }

    protected Board getBoard(){
        return board;
    }
}

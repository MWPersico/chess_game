package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public class ChessPiece extends Piece{
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color){
        super(board);
        this.color = color;
    }

    public ChessPosition getChessPosition(){
        return null;//     
    }

    protected boolean isThereOpponentPiece(Position position){
        return false;//
    }

    protected void increaseMoveCount(){
        moveCount++;
    }

    protected void decreaseMoveCount(){
        moveCount--;
    }

    public Color getColor(){
        return color;
    }
}

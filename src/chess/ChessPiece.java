package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
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
        ChessPiece piece = (ChessPiece) getBoard().getPiece(position);
        if(getBoard().thereIsAPiece(position) && piece.getColor() != this.color){
            return true;
        }
        return false;
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

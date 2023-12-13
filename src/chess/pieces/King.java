package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves(){
        return new boolean[1][1];//
    }
    
    //Retorna apenas uma letra para ser renderizado pela UI
    @Override
    public String toString(){return "K";}
}

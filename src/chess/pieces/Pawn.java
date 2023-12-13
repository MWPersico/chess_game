package chess.pieces;

import java.util.Arrays;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        for(int i=0; i<moves.length; i++){
            for(int j=0; j<moves[i].length; j++){
                moves[i][j] = true;
            }
        }
        return moves;
    }
 
    @Override
    public String toString(){return "P";}
}

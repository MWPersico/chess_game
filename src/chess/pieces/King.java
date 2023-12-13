package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        // Percorre as posições da superior-esquerda até inferior-direita do rei validando posições
        p.setValues(position.getRow()-1, position.getColumn()-1);
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3; j++){
                if(getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))){
                    possibleMoves[p.getRow()][p.getColumn()] = true;
                }
                p.setColumn(p.getColumn()+1);
            }
            p.setValues(p.getRow()+1,position.getColumn()-1);
        }
        return possibleMoves;
    }
    
    //Retorna apenas uma letra para ser renderizado pela UI
    @Override
    public String toString(){return "K";}
}

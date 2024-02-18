package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;
import chess.ChessMatch;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        // Percorre as posições da superior-esquerda até inferior-direita do rei
        // validando posições
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
                    possibleMoves[p.getRow()][p.getColumn()] = true;
                }
                p.setColumn(p.getColumn() + 1);
            }
            p.setValues(p.getRow() + 1, position.getColumn() - 1);
        }

        // Special move: Castling
        if(!chessMatch.getCheck()){
            // King side castling (short castling)
            Position rookPosition = new Position(position.getRow(), position.getColumn()+3);
            if(getMoveCount() == 0 && testRookCastling(rookPosition)){
                Position firstRight = new Position(position.getRow(), position.getColumn()+1);
                Position secondRight = new Position(position.getRow(), position.getColumn()+2);

                if(getBoard().getPiece(firstRight) == null && getBoard().getPiece(secondRight) == null){
                    possibleMoves[position.getRow()][position.getColumn()+2] = true;
                }
            }

            // Queen side castling (long castling)
            rookPosition = new Position(position.getRow(), position.getColumn()-4);
            if(getMoveCount() == 0 && testRookCastling(rookPosition)){
                Position firstLeft = new Position(position.getRow(), position.getColumn()-1);
                Position secondLeft = new Position(position.getRow(), position.getColumn()-2);
                Position thirdLeft = new Position(position.getRow(), position.getColumn()-3);

                if(getBoard().getPiece(firstLeft) == null && getBoard().getPiece(secondLeft) == null && getBoard().getPiece(thirdLeft) == null){
                    possibleMoves[position.getRow()][position.getColumn()-2] = true;
                }
            }
        }

        return possibleMoves;
    }

    public boolean testRookCastling(Position position){        
        ChessPiece rook = (ChessPiece) getBoard().getPiece(position);
        boolean isValidRook = rook != null && rook instanceof Rook && rook.getColor() == getColor();
        if(isValidRook){
            return rook.getMoveCount() == 0;
        }
        return false;
    }

    // Retorna apenas uma letra para ser renderizado pela UI
    @Override
    public String toString() {
        return "K";
    }
}

package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;
import chess.ChessMatch;

public class Pawn extends ChessPiece {
    ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        // Inverte a direção dos movimentos caso a peça seja preta (baseado na perpectiva da peça)
        int direction = getColor() == Color.BLACK ? -1 : 1;

        // first move (whites)
        if ((position.getRow() == 6) && (getColor() == Color.WHITE)) {
            possibleMoves[position.getRow() - 2][position.getColumn()] = true;
        }
        // first move (blacks)
        if ((position.getRow() == 1) && (getColor() == Color.BLACK)) {
            possibleMoves[position.getRow() + 2][position.getColumn()] = true;
        }
        // north
        p.setValues(position.getRow() - direction, position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        // northwest
        p.setValues(position.getRow() - direction, position.getColumn() - direction);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        // northeast
        p.setValues(position.getRow() - direction, position.getColumn() + direction);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }

        // Special Move: En Passant

        direction = getColor() == Color.WHITE ? 1 : -1;

        if(chessMatch.getEnPassantVulnerable() != null && (position.getRow()==3 || position.getRow()==4)){
            Position left = new Position(position.getRow(), position.getColumn()-direction);
            Position right = new Position (position.getRow(), position.getColumn()+direction);
            // En passant left
            if(getBoard().positionExists(left)){
                if(isThereOpponentPiece(left) && getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable()){
                    // North West
                    possibleMoves[left.getRow()-direction][left.getColumn()] = true;
                }
            }
            // En passant right
            if(getBoard().positionExists(right)){
                if(isThereOpponentPiece(right) && getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable()){
                    // North East
                    possibleMoves[right.getRow()-direction][right.getColumn()] = true;
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public String toString() {
        return "P";
    }
}

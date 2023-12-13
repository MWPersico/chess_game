package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        // Inverte a direção dos movimentos caso a peça seja preta
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
        return possibleMoves;
    }

    @Override
    public String toString() {
        return "P";
    }
}

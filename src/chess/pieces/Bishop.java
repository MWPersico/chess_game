package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        // northwest
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = canMove(p);
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        // northeast
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = canMove(p);
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        // southwest
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = canMove(p);
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        // southeast
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = canMove(p);
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        return possibleMoves;
    }

    public boolean canMove(Position position) {
        return !getBoard().thereIsAPiece(position) || isThereOpponentPiece(position);
    }

    @Override
    public String toString() {
        return "B";
    }
}

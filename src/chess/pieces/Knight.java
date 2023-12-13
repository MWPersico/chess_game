package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        // Verifica as posições verticais do cavalo
        // starts in north north-west and ends in south south-east
        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                if (canMove(p)) {
                    possibleMoves[p.getRow()][p.getColumn()] = true;
                }
                p.setColumn(p.getColumn() + 2);
            }
            p.setValues(position.getRow() + 2, position.getColumn() - 1);
        }

        // Verifica as posições horizontais do cavalo
        // starts in west north-west and ends in east south-east
        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                if (canMove(p)) {
                    possibleMoves[p.getRow()][p.getColumn()] = true;
                }
                p.setColumn(p.getColumn() + 4);
            }
            p.setValues(position.getRow() + 1, position.getColumn() - 2);
        }
        return possibleMoves;
    }

    public boolean canMove(Position position) {
        if (getBoard().positionExists(position)) {
            if (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "N";
    }

}

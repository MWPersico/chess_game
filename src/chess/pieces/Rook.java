package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        // Reference position
        Position p = new Position(0, 0);

        // Verifica posições verticalmente

        // Position Above
        p.setValues(position.getRow() - 1, position.getColumn());
        // Enquanto a posição existir e não estiver vaga
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }

        // Position Below
        p.setValues(position.getRow() + 1, position.getColumn());
        // Enquanto a posição existir e não estiver vaga
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }

        // Verifica posições horizontalmente

        // Left Position
        p.setValues(position.getRow(), position.getColumn() - 1);
        // Enquanto a posição existir e não estiver vaga
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }

        // Right Position
        p.setValues(position.getRow(), position.getColumn() + 1);
        // Enquanto a posição existir e não estiver vaga
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        // Verifica se há uma peça inimiga na extremidade
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            possibleMoves[p.getRow()][p.getColumn()] = true;
        }
        return possibleMoves;
    }

    @Override
    public String toString() {
        return "R";
    }
}

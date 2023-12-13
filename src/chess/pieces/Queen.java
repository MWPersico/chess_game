package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        Boolean[][] possibleMoves = new Boolean[getBoard().getRows()][getBoard().getColumns()];
        boolean[][] primitivePossibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        for (int i = 0; i < possibleMoves.length; i++) {
            for (int j = 0; j < possibleMoves.length; j++) {
                possibleMoves[i][j] = false;
            }
        }

        Position p = new Position(0, 0);

        // importado da torre
        rookMovement(possibleMoves, p);

        // importado do bispo
        bishopMovement(possibleMoves, p);

        // Casting
        for (int i = 0; i < possibleMoves.length; i++) {
            for (int j = 0; j < possibleMoves.length; j++) {
                primitivePossibleMoves[i][j] = (boolean) possibleMoves[i][j];
            }
        }
        return primitivePossibleMoves;
    }

    public void rookMovement(Boolean[][] possibleMoves, Position p) {
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
    }

    public void bishopMovement(Boolean[][] possibleMoves, Position p) {
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
    }

    public boolean canMove(Position position) {
        return !getBoard().thereIsAPiece(position) || isThereOpponentPiece(position);
    }

    @Override
    public String toString() {
        return "Q";
    }
}

package chess;

import boardgame.Position;

public class ChessPosition {
    private int row;
    private char column;

    public ChessPosition(char column, int row) {
        if (row < 1 || row > 8 || column < 'a' || column > 'h') {
            throw new ChessException(
                    String.format("Error defining chess position: The position %c%d does not exist.", column, row));
        }
        this.row = row;
        this.column = column;
    }

    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
    }

    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}

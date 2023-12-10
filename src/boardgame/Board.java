package boardgame;

public class Board {
    private int rows;
    private int columns;
    Piece[][] pieces;

    public Board(int rows, int columns){
        if(rows<1 || columns<1){
            throw new BoardException("Error creating board: The board must have at least 1 row and 1 column.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece getPiece(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException(String.format("Error getting pieces: The position %d,%d does not exists.", row, column));
        }
        return pieces[row][column];
    }
    public Piece getPiece(Position position){
        return getPiece(position.getRow(), position.getColumn());
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException(String.format("Error placing pieces: There is alredy a piece on position %s.", position));
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position){
        if(!positionExists(position)){
            throw new BoardException(String.format("Error removing pieces: There is not a piece on position %s.", position));
        }
        if(getPiece(position) == null) return null;
        Piece piece = getPiece(position);
        piece.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return piece;
    }

    private boolean positionExists(int row, int column){
        return row>=0 && row<rows && column>=0 && column<columns;
    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException(String.format("Error handling pieces: The position %s does not exists.", position));
        }
        return getPiece(position)!= null;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece[][] getPieces(){
        return pieces;
    }
}

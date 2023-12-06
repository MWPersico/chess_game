package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private Board board;

    public ChessMatch(){
        this.board = new Board(8,8);
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i=0; i<pieces.length; i++){
            for(int j=0; j<pieces[i].length;j++){
                pieces[i][j] = (ChessPiece)board.getPiece(i, j);
            }
        }
        return pieces;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        return new boolean[1][1];//
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        return new ChessPiece(board, Color.BLACK);//
    }

    public ChessPiece replacePromotedPiece(String type){
        return new ChessPiece(board, Color.WHITE);
    }

    public void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        //PLace pawns
        for(char i='a'; i<='h'; i++){
            placeNewPiece(i, 7, new Pawn(board, Color.BLACK));
            placeNewPiece(i, 2, new Pawn(board, Color.WHITE));
        }

        //Place kings
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('e', 8, new King(board, Color.BLACK));

        //Place queens
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));

        //Place rooks
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        //Place knights
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));

        //Place bishops
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));

        
    }
}
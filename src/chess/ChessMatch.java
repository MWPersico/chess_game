package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Pawn;

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
                pieces[i][j] = (ChessPiece)board.piece(i, j);
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

    private void initialSetup(){
        //PLace pawns
        for(int i=0; i<8; i++){
            board.placePiece(new Pawn(board, Color.WHITE), new Position(6, i));
        }
    }
}

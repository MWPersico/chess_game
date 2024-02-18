package chess;

import java.util.ArrayList;
import java.util.List;
import boardgame.Board;
import boardgame.Piece;
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
    private List<Piece> piecesOnTheBoard;
    private List<Piece> capturedPieces;


    public ChessMatch() {
        board = new Board(8, 8);
        piecesOnTheBoard = new ArrayList<>();
        capturedPieces = new ArrayList<>();
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return pieces;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);
        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You cannot put your king in check!");
        }

        check = testCheck(opponent(currentPlayer)) ? true : false;

        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }else{
            nextTurn();
        }
        return (ChessPiece) capturedPiece;
    }

    public void validateSourcePosition(Position sourcePosition) {
        if (!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException(String.format("Error performing move: There is no piece at %s.",
            ChessPosition.fromPosition(sourcePosition)));
        }
        if(((ChessPiece)board.getPiece(sourcePosition)).getColor() != currentPlayer){
            throw new ChessException("The choosen piece is no yours!");
        }
        if (!board.getPiece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException(
                    String.format("Error performing move: There is no possible moves for the piece at %s.",
                            ChessPosition.fromPosition(sourcePosition)));
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public boolean[][] possibleMoves(ChessPosition position) {
        validateSourcePosition(position.toPosition());
        return board.getPiece(position.toPosition()).possibleMoves();
    }

    public void validateTargetPosition(Position sourcePosition, Position targetPosition) {
        ChessPiece piece = (ChessPiece) board.getPiece(sourcePosition);
        boolean[][] possibleMoves = piece.possibleMoves();
        if (!possibleMoves[targetPosition.getRow()][targetPosition.getColumn()]) {
            throw new ChessException(String.format("Error performing move: Invalid move!"));
        }
    }

    public Piece makeMove(Position source, Position target) {
        // Realiza o movimento no tabuleiro e retorna a peça que foi atacada
        Piece sourcePiece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(sourcePiece, target);
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    };

    public ChessPiece replacePromotedPiece(String type) {
        return new Bishop(board, Color.WHITE);// teste
    }

    public void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private boolean testCheck(Color player){
        Position kingPosition = king(player).getChessPosition().toPosition();
        List<Piece> opponentPieces = filterPieces(opponent(player));
        for(Piece piece : opponentPieces){
            if(piece.possibleMoves()[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            };
        }
        return false;
    }

    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }

        List<Piece> filteredPieces = filterPieces(color);
        for(Piece piece : filteredPieces){
            boolean[][] possibleMoves = piece.possibleMoves();

            if(!piece.isThereAnyPossibleMove())continue;

            for(int i=0; i<possibleMoves.length; i++){
                for(int j=0; j<possibleMoves[i].length; j++){
                    if(possibleMoves[i][j]){
                        Position source = ((ChessPiece)piece).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean inCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if(!inCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void initialSetup() {
        // PLace pawns
        for (char i = 'a'; i <= 'h'; i++) {
            placeNewPiece(i, 7, new Pawn(board, Color.BLACK));
            placeNewPiece(i, 2, new Pawn(board, Color.WHITE));
        }

        // Place kings
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('e', 8, new King(board, Color.BLACK));

        // Place queens
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));

        // Place rooks
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        // Place knights
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));

        // Place bishops
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));


        // Reservado para tabuleiros de teste: TODO REMOVER

    }

    public int getTurn() {
        return turn;
    }

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    private Color opponent(Color player){
        return (player == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private List<Piece> filterPieces(Color color){
        List<Piece> filteredPieces = piecesOnTheBoard
        .stream()
        .filter(piece->((ChessPiece)piece).getColor() == color)
        .toList();
        return filteredPieces;
    }

    private ChessPiece king(Color color){
        List<Piece> list = filterPieces(color);
        for(Piece piece : list){
            if(piece instanceof King){
                return (ChessPiece) piece;
            }
        }
        throw new IllegalStateException(String.format("There is no %s king on the board!", color));
    }
}
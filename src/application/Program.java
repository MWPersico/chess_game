package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();
        while (true) {
            try {
                UI.clearScreen();
                sc.nextLine();
                UI.printChessMatch(chessMatch, capturedPieces);
                System.out.print("Posição para mover: ");
                ChessPosition source = UI.readChessPosition(sc);

                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), chessMatch.possibleMoves(source));

                System.out.print("Posição de destino: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null){
                    capturedPieces.add(capturedPiece);
                }
            } catch (InputMismatchException ex) {
                System.out.print(ex.getMessage());
                UI.delay(2);
            } catch (ChessException ex) {
                System.out.print(ex.getMessage());
                UI.delay(4);
            }

        }
    }
}

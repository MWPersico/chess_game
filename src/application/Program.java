package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while(true){
           // UI.clearScreen();
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Gostaria de performar um movimento? ");
            String input = sc.next();
            if(input.charAt(0) == 's'){
                System.out.print("Posição para mover: ");
                ChessPosition source = UI.readChessPosition(sc);
                System.out.print("Posição de destino: ");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if(capturedPiece != null)System.out.printf("\nPeça em %s capturada!\n", target);
            }else{
                System.out.println("Obrigado por jogar!!");
                break;
            }
            
        }
    }
}

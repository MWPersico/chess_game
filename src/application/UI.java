package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

    // Códigos de cores para colorir caracteres e fundo no terminal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void printChessMatch(ChessMatch match, List<ChessPiece> capturedPieces) {
        printBoard(match.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println();
        System.out.println("Turn: " + match.getTurn());
        
        if(!match.getCheckMate()){
            System.out.println("Waiting player: " + match.getCurrentPlayer());
            if(match.getCheck()){
                System.out.println("CHECK!!\n");
            }
        }else{
            System.out.printf("\nCHECKMATE! %s player wins.\n", match.getCurrentPlayer());
        }
        
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String input = sc.next();
            return new ChessPosition(input.charAt(0), Integer.parseInt(String.valueOf(input.charAt(1))));
        } catch (RuntimeException ex) {
            throw new InputMismatchException("Error reading chess position: Valid values are from a1 to h8.");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print(ANSI_GREEN + (8 - i) + " " + ANSI_RESET);
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.print(ANSI_GREEN + "  a b c d e f g h" + ANSI_RESET);
        System.out.println("");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print(ANSI_GREEN + (8 - i) + " " + ANSI_RESET);
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.print(ANSI_GREEN + "  a b c d e f g h" + ANSI_RESET);
        System.out.println("");
    }

    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printPiece(ChessPiece piece, boolean isPossibleMove) {
        if (isPossibleMove) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        printPiece(piece);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void delay(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> white = captured
                .stream()
                .filter(piece -> piece.getColor() == Color.WHITE)
                .toList();
        List<ChessPiece> black = captured
                .stream()
                .filter(piece -> piece.getColor() == Color.BLACK)
                .toList();
        System.out.println("Captured pieces: ");
        System.out.print("White pieces: ");
        System.out.print(ANSI_WHITE);
        System.out.println(white);
        System.out.print(ANSI_RESET);

        System.out.print("Black pieces: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(black);
        System.out.print(ANSI_RESET);
    }
}

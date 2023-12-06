package application;

import chess.ChessPiece;
import chess.Color;

public class UI {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printBoard(ChessPiece[][] pieces){
        for(int i=0;i<pieces.length; i++){
            System.out.print(ANSI_WHITE+(8-i)+" ");
            for(int j=0;j<pieces[i].length; j++){
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece){
        if(piece==null){
            System.out.print("-");
        }else{
            if(piece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE+piece);
            }else{
                System.out.print(ANSI_YELLOW+piece);
            }
        }
        System.out.print(" ");
    }

    public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

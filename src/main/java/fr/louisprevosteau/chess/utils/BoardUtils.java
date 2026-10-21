package fr.louisprevosteau.chess.utils;


public class BoardUtils {

    private static final int BOARD_SIZE = 8;

    public static boolean isInsideBoard(int row, int column) {
        return row >= 0
                && row < BOARD_SIZE
                && column >= 0
                && column < BOARD_SIZE;
    }
}

package utils;

import game.Position;

/**
 * Created by hiroshi on 21/04/17.
 */
public class BoardUtils {

    public static boolean willPieceBeOutOfBounds(int[][] board, int[][] piece, Position position) {
        int boardWidth = board.length;
        int boardHeight = board[0].length;
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                int bx = position.getX() + x;
                int by = position.getY() + y;
                if (piece[x][y] != 0 && (bx < 0 || by < 0 || bx >= boardWidth || by >= boardHeight)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean willCollide(int[][] board, int[][] piece, Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0 && board[position.getX() + x][position.getY() + y] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void placePiece(int[][] board, int[][] piece, Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0) {
                    board[position.getX() + x][position.getY() + y] = piece[x][y];
                }
            }
        }
    }

    public static void removePiece(int[][] board, int[][] piece, Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0) {
                    board[position.getX() + x][position.getY() + y] = 0;
                }
            }

        }
    }

    private static void removeRow(int[][] board, int row) {
        int boardWidth = board.length;

        for (int y = row; y > 0; y--) {
            for (int x = 0; x < boardWidth; x++) {
                board[x][y] = board[x][y - 1];
            }
        }

        for (int x = 0; x < boardWidth; x++) {
            board[x][0] = 0;
        }
    }

    public static int removeFilledRows(int[][] board) {
        int boardWidth = board.length;
        int boardHeight = board[0].length;
        int removedRows = 0;

        for (int y = 0; y < boardHeight; y++) {
            boolean filledRow = true;
            for (int x = 0; x < boardWidth; x++) {
                if (board[x][y] == 0) {
                    filledRow = false;
                    break;
                }
            }

            if (filledRow) {
                removeRow(board, y);
                removedRows++;
            }
        }

        return removedRows;
    }
}

/**
 * Created by hiroshi on 19/04/17.
 */
public class TetrisUtils {

    public static void horizontalSwap(int[][] piece) {
        int length = piece.length;
        int halfLength = length / 2;

        for (int x = 0; x < halfLength; x++) {
            for (int y = 0; y < length; y++) {
                int tmp = piece[x][y];
                piece[x][y] = piece[length - 1 - x][y];
                piece[length - 1 - x][y] = tmp;
            }
        }
    }

    public static void rotateClockwise(int[][] piece) {
        int length = piece.length;
        int rounds = length / 2;

        for (int r = 0; r < rounds; r++) {
            for (int c = r; c < length - 1 - r; c++) {
                int tmp = piece[r][length - 1 - c];
                piece[r][length - 1 - c] = piece[length - 1 - c][length - 1 - r];
                piece[length - 1 - c][length - 1 - r] = piece[length - 1 - r][c];
                piece[length - 1 - r][c] = piece[c][r];
                piece[c][r] = tmp;
            }
        }
    }

    public static void rotateAntiClockwise(int[][] piece) {
        int length = piece.length;
        int rounds = length / 2;

        for (int r = 0; r < rounds; r++) {
            for (int c = r; c < length - 1 - r; c++) {
                int tmp = piece[c][r];
                piece[c][r] = piece[length - 1 - r][c];
                piece[length - 1 - r][c] = piece[length - 1 - c][length - 1 - r];
                piece[length - 1 - c][length - 1 - r] = piece[r][length - 1 - c];
                piece[r][length - 1 - c] = tmp;
            }
        }
    }

    public static boolean willBeOutOfBoard(int boardWidth, int boardHeight, int[][] piece, Position position) {
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

    public static void putPieceOnTheBoard(int[][] board, int[][] piece, Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0) {
                    board[position.getX() + x][position.getY() + y] = piece[x][y];
                }
            }

        }
    }

    public static void removePieceFromTheBoard(int[][] board, int[][] piece, Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0) {
                    board[position.getX() + x][position.getY() + y] = 0;
                }
            }

        }
    }


}

import java.util.Random;

/**
 * Created by hiroshi on 21/04/17.
 */
public class PieceUtils {

    public static int[][] createBlockPiece() {
        return new int[][]{
                {1, 1},
                {1, 1},
        };
    }

    public static int[][] createStickPiece() {
        return new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        };
    }

    public static int[][] createSnakePiece() {
        return new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
        };
    }

    public static int[][] createCornerPiece() {
        return new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 1},
        };
    }

    public static int[][] createTetrisPiece() {
        return new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0},
        };
    }

    public static int[][] randomChoose(Random random) {
        switch (random.nextInt(5)) {
            case 0:
                return createBlockPiece();
            case 1:
                return createStickPiece();
            case 2:
                return createSnakePiece();
            case 3:
                return createCornerPiece();
            case 4:
                return createTetrisPiece();
            default:
                return null;
        }
    }

    public static void addColor(int[][] piece, Random random) {
        int length = piece.length;
        int color = random.nextInt();

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                piece[x][y] *= color;
            }
        }
    }

    public static void transpose(int[][] piece) {
        int length = piece.length;

        for (int x = 1; x < length; x++) {
            for (int y = 0; y < x; y++) {
                int tmp = piece[x][y];
                piece[x][y] = piece[y][x];
                piece[y][x] = tmp;
            }
        }
    }

    public static void swapHorizontally(int[][] piece) {
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

    public static String asString(int[][] piece) {
        int length = piece.length;
        String s = "";

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                s += ((piece[x][y] != 0) ? "X" : ".");
            }
            s += "\n\r";
        }
        return s;
    }

}

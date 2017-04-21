import java.util.Random;

/**
 * Created by hiroshi on 19/04/17.
 */
public abstract class TetrisGame {

    protected enum GameState {
        STOPPED, DROPPING, SLIDING
    }

    private GameState gameState;

    private int boardWidth;
    private int boardHeight;

    private int[][] board;

    private int[][] piece;
    private Position position;

    public TetrisGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.board = new int[boardWidth][boardHeight];
        gameState = GameState.STOPPED;
    }

    public abstract void drawScreen();

    public void startGame() {
        gameState = GameState.DROPPING;
        while (!GameState.STOPPED.equals(gameState)) {
            long time = System.currentTimeMillis();
            run();
            drawScreen();
            while (System.currentTimeMillis() - time < 100) {
            }
        }
    }

    public void moveLeft() {
        move(new Position(position.getX() - 1, position.getY()));
    }

    public void moveRight() {
        move(new Position(position.getX() + 1, position.getY()));
    }

    public void rotateClockwise() {
        rotatePieceClockwise();
    }

    public void rotateAntiClockwise() {
        rotatePieceAntiClockwise();
    }

    private void run() {
//        System.out.println(this.getClass().getName().concat(".run()"));

        switch (gameState) {
            case STOPPED:
                break;
            case DROPPING:
                choosePieceToDrop();
                if (willPieceCollide(position)) {
                    gameState = GameState.STOPPED;
                } else {
                    placePieceOnTheBoard(position);
                    gameState = GameState.SLIDING;
                }
                break;
            case SLIDING:
                boolean moved = move(new Position(position.getX(), position.getY() + 1));
                if (!moved) {
                    gameState = GameState.DROPPING;
                }
                break;
        }
    }

    private void choosePieceToDrop() {
        switch (new Random().nextInt(5)) {
            case 0:
                piece = createBlockPiece();
                break;
            case 1:
                piece = createStickPiece();
                break;
            case 2:
                piece = createSnakePiece();
                break;
            case 3:
                piece = createOctoganPiece();
                break;
            case 4:
                piece = createTetrisPiece();
                break;
        }

        position = new Position((boardWidth - piece.length) / 2, 0);
    }


    private boolean move(Position nextPosition) {
        if (willPieceBeOutOfBoard(nextPosition)) {
            return false;
        }

        removePieceFromTheBoard(position);

        if (willPieceCollide(nextPosition)) {
            placePieceOnTheBoard(position);
            return false;
        } else {
            placePieceOnTheBoard(nextPosition);
            position = nextPosition;
            return true;
        }
    }

    private boolean willPieceBeOutOfBoard(Position position) {
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

    private boolean willPieceCollide(Position position) {
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

    private void placePieceOnTheBoard(Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0) {
                    board[position.getX() + x][position.getY() + y] = piece[x][y];
                }
            }

        }
    }

    private void removePieceFromTheBoard(Position position) {
        int length = piece.length;

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                if (piece[x][y] != 0) {
                    board[position.getX() + x][position.getY() + y] = 0;
                }
            }

        }
    }

    private void swapPieceHorizontally() {
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

    private void rotatePieceClockwise() {
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

    private void rotatePieceAntiClockwise() {
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

    private int[][] createBlockPiece() {
        return new int[][]{
                {1, 1},
                {1, 1},
        };
    }

    private int[][] createStickPiece() {
        return new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        };
    }

    private int[][] createSnakePiece() {
        return new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
        };
    }

    private int[][] createOctoganPiece() {
        return new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 1},
        };
    }

    private int[][] createTetrisPiece() {
        return new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0},
        };
    }


    public int[][] getBoard() {
        return board;
    }

}

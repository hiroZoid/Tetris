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


    private int[][] blockPiece = {
            {1, 1},
            {1, 1},
    };

    private int[][] stickPiece = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
    };

    private int[][] snakePiece = {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0},
    };

    private int[][] octoganPiece = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1},
    };

    private int[][] tetrisPiece = {
            {0, 0, 0},
            {1, 1, 1},
            {0, 1, 0},
    };

    public TetrisGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.board = new int[boardWidth][boardHeight];
        gameState = GameState.STOPPED;
    }

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

    public void choosePieceToDrop() {
        switch (new Random().nextInt(5)) {
            case 0:
                piece = blockPiece;
                break;
            case 1:
                piece = stickPiece;
                break;
            case 2:
                piece = snakePiece;
                break;
            case 3:
                piece = octoganPiece;
                break;
            case 4:
                piece = tetrisPiece;
                break;
        }

        position = new Position((boardWidth - piece.length) / 2, 0);
    }


    public int[][] getBoard() {
        return board;
    }

    public void run() {
//        System.out.println(this.getClass().getName().concat(".run()"));

        switch (gameState) {
            case STOPPED:
                break;
            case DROPPING:
                choosePieceToDrop();
                if (TetrisUtils.willCollide(board, piece, position)) {
                    gameState = GameState.STOPPED;
                } else {
                    TetrisUtils.putPieceOnTheBoard(board, piece, position);
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

    private boolean move(Position nextPosition) {
        if (TetrisUtils.willBeOutOfBoard(boardWidth, boardHeight, piece, nextPosition)) {
            return false;
        }

        TetrisUtils.removePieceFromTheBoard(board, piece, position);

        if (TetrisUtils.willCollide(board, piece, nextPosition)) {
            TetrisUtils.putPieceOnTheBoard(board, piece, position);
            return false;
        } else {
            TetrisUtils.putPieceOnTheBoard(board, piece, nextPosition);
            position = nextPosition;
            return true;
        }
    }

    public void moveLeft() {
        move(new Position(position.getX() - 1, position.getY()));
    }

    public void moveRight() {
        move(new Position(position.getX() + 1, position.getY()));
    }

    public abstract void drawScreen();
}

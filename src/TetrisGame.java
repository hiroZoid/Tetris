import java.util.Random;
import java.util.Vector;

/**
 * Created by hiroshi on 19/04/17.
 */
public class TetrisGame {

    private enum GameState {
        STOPPED, DROPPING, SLIDING
    }

    private GameState gameState;

    private TetrisPainter tetrisPainter;
    private int[][] board;
    private int[][] piece;
    private Position position;
    private Vector<UserInput> userInputs;

    public TetrisGame(int boardWidth, int boardHeight, TetrisPainter tetrisPainter) {
        this.tetrisPainter = tetrisPainter;
        this.board = new int[boardWidth][boardHeight];
        this.userInputs = new Vector<>();
        this.gameState = GameState.STOPPED;
    }

    public void startGame() {
        gameState = GameState.DROPPING;
        while (gameState != GameState.STOPPED) {
            long time = System.currentTimeMillis();
            automaticGameFlow();
            tetrisPainter.paintTetris(board);
            while (System.currentTimeMillis() - time < 1000) {
                boolean forcePieceSlide = userDependantGameFlow();
                if (forcePieceSlide) {
                    break;
                }
            }
        }
    }

    private void automaticGameFlow() {
        switch (gameState) {
            case STOPPED:
                break;
            case DROPPING:
                piece = PieceUtils.randomChoose(new Random());
                position = new Position((getBoardWidth() - piece.length) / 2, 0);
                if (BoardUtils.willCollide(board, piece, position)) {
                    gameState = GameState.STOPPED;
                } else {
                    BoardUtils.placePiece(board, piece, position);
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

    private boolean userDependantGameFlow() {
        boolean forcePieceSlide = false;

        if (gameState == GameState.SLIDING && !userInputs.isEmpty()) {
            switch (userInputs.remove(0)) {
                case MOVE_LEFT:
                    move(new Position(position.getX() - 1, position.getY()));
                    break;
                case MOVE_RIGTH:
                    move(new Position(position.getX() + 1, position.getY()));
                    break;
                case MOVE_DOWN:
                    forcePieceSlide = true;
                    break;
                case ROTATE_CLOCKWISE:
                    rotatePiece(false);
                    break;
                case ROTATE_ANTICLOCKWISE:
                    rotatePiece(true);
                    break;
            }
            tetrisPainter.paintTetris(board);
        }

        return forcePieceSlide;
    }

    private boolean move(Position nextPosition) {
        if (BoardUtils.willPieceBeOutOfBounds(board, piece, nextPosition)) {
            return false;
        }

        BoardUtils.removePiece(board, piece, position);

        if (BoardUtils.willCollide(board, piece, nextPosition)) {
            BoardUtils.placePiece(board, piece, position);
            return false;
        } else {
            BoardUtils.placePiece(board, piece, nextPosition);
            position = nextPosition;
            return true;
        }
    }

    private boolean rotatePiece(boolean antiClockwise) {
        boolean rotated = true;

        BoardUtils.removePiece(board, piece, position);

        if (antiClockwise) {
            PieceUtils.rotateAntiClockwise(piece);
        } else {
            PieceUtils.rotateClockwise(piece);
        }

        if (BoardUtils.willPieceBeOutOfBounds(board, piece, position) || BoardUtils.willCollide(board, piece, position)) {
            if (antiClockwise) {
                PieceUtils.rotateClockwise(piece);
            } else {
                PieceUtils.rotateAntiClockwise(piece);
            }
            rotated = false;
        }

        BoardUtils.placePiece(board, piece, position);

        return rotated;
    }

    private int getBoardWidth() {
        return board.length;
    }

    public void appendUserInput(UserInput userInput) {
        if (gameState == GameState.SLIDING) {
            userInputs.add(userInput);
        }
    }
}

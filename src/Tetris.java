import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris implements KeyListener {

    private JFrame jFrame;
    private TetrisJPanel tetrisJPanel;

    private TetrisGame tetrisGame;

    public Tetris(int boardWidth, int boardHeight) {

        tetrisJPanel = new TetrisJPanel();
        tetrisJPanel.addKeyListener(this);
        tetrisJPanel.setFocusable(true);
        tetrisJPanel.setDoubleBuffered(true);

        jFrame = new JFrame("Tetris");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 400);
        jFrame.setContentPane(tetrisJPanel);

        tetrisGame = new TetrisGame(boardWidth, boardHeight, tetrisJPanel);
    }

    public void run() {
        jFrame.setVisible(true);
        tetrisGame.startGame();
    }


    public static void main(String[] args) {

        Tetris tetris = new Tetris(40, 40);
        tetris.run();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                tetrisGame.appendUserInput(UserInput.ROTATE_ANTICLOCKWISE);
                break;
            case KeyEvent.VK_DOWN:
                tetrisGame.appendUserInput(UserInput.ROTATE_CLOCKWISE);
                break;
            case KeyEvent.VK_LEFT:
                tetrisGame.appendUserInput(UserInput.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                tetrisGame.appendUserInput(UserInput.MOVE_RIGTH);
                break;
            case KeyEvent.VK_SPACE:
                tetrisGame.appendUserInput(UserInput.MOVE_DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

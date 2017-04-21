import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris implements KeyListener {

    private int boardWidth;
    private int boardHeight;

    private JFrame jFrame;
    private JPanel jPanel;

    private TetrisGame tetrisGame;

    public Tetris(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        tetrisGame = new TetrisGame(boardWidth, boardHeight) {
            @Override
            public void drawScreen() {
                jPanel.repaint();
            }
        };

        jPanel = new TetrisJPanel(tetrisGame.getBoard(), boardWidth, boardHeight);
        jPanel.addKeyListener(this);
        jPanel.setFocusable(true);

        jFrame = new JFrame("Tetris");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 400);
        jFrame.setContentPane(jPanel);
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
                tetrisGame.rotateClockwise();
                break;
            case KeyEvent.VK_DOWN:
                tetrisGame.rotateAntiClockwise();
                break;
            case KeyEvent.VK_LEFT:
                tetrisGame.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                tetrisGame.moveRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

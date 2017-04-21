package gui;

import game.TetrisGame;
import game.TetrisScoreBoard;
import game.UserInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris {

    private JLabel jLabelScoreBoard;
    private JFrame jFrameMainWindow;
    private TetrisJPanel tetrisJPanel;

    private TetrisGame tetrisGame;

    public Tetris(int boardWidth, int boardHeight) {

        tetrisJPanel = new TetrisJPanel();
        tetrisJPanel.setFocusable(true);
        tetrisJPanel.setDoubleBuffered(true);
        tetrisJPanel.addKeyListener(new KeyListener() {
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
                        tetrisGame.appendUserInput(UserInput.MOVE_RIGHT);
                        break;
                    case KeyEvent.VK_SPACE:
                        tetrisGame.appendUserInput(UserInput.MOVE_DOWN);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        jLabelScoreBoard = new JLabel();

        jFrameMainWindow = new JFrame("gui.Tetris");
        jFrameMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameMainWindow.setSize(boardWidth * 35, boardHeight * 35);
        jFrameMainWindow.setLayout(new BorderLayout());
        jFrameMainWindow.getContentPane().add(jLabelScoreBoard, BorderLayout.NORTH);
        jFrameMainWindow.getContentPane().add(tetrisJPanel, BorderLayout.CENTER);

        tetrisGame = new TetrisGame(boardWidth, boardHeight, tetrisJPanel, new TetrisScoreBoard() {
            @Override
            public void showScore(int score) {
                jLabelScoreBoard.setText("Score: " + score);
            }

            @Override
            public void showGameOver(int finalScore) {
                jLabelScoreBoard.setText("Game over. Final score: " + finalScore);
            }
        });
    }

    public void run() {
        jFrameMainWindow.setVisible(true);
        tetrisGame.startGame(1000, 10);
    }


    public static void main(String[] args) {

        Tetris tetris = new Tetris(10, 22);
        tetris.run();

    }


}

package gui;

import game.TetrisPainter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by hiroshi on 19/04/17.
 */
public class TetrisJPanel extends JPanel implements TetrisPainter {

    private int[][] board = null;
    private boolean showFilledRows = false;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (board == null)
            return;

        int boardWidth = board.length;
        int boardHeight = board[0].length;

        int boxWidth = this.getWidth() / boardWidth;
        int boxHeight = this.getHeight() / boardHeight;

        int xGap = (this.getWidth() - boxWidth * boardWidth) / 2;
        int yGap = (this.getHeight() - boxHeight * boardHeight) / 2;

        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                if (this.board[x][y] == 0) {
                    g.setColor(Color.BLACK);
                } else {
                    Random random = new Random(this.board[x][y]);
                    int red = 128 + random.nextInt(128);
                    int green = 128 + random.nextInt(128);
                    int blue = 128 + random.nextInt(128);
                    g.setColor(new Color(red, green, blue));
                }
                g.fillRect(x * boxWidth + xGap, y * boxHeight + yGap, boxWidth, boxHeight);
            }
        }

        if (!showFilledRows)
            return;

        g.setColor(new Color(255, 255, 255, 128));
        int thickness = boxHeight / 3;
        for (int y = 0; y < boardHeight; y++) {
            boolean filledRow = true;
            for (int x = 0; x < boardWidth; x++) {
                if (board[x][y] == 0) {
                    filledRow = false;
                    break;
                }
            }
            if (filledRow) {
                g.fillRect(xGap, y * boxHeight + yGap, boxWidth * boardWidth, boxHeight);

                int frX = xGap + thickness;
                int frY = y * boxHeight + yGap + thickness;
                int frWidth = boxWidth * boardWidth - thickness * 2;
                int frHeight = boxHeight - thickness * 2;
                g.fillRect(frX, frY, frWidth, frHeight);
            }
        }

    }

    @Override
    public void paintTetris(int[][] board, boolean showFilledRows) {
        this.board = board;
        this.showFilledRows = showFilledRows;
        this.repaint();
    }
}

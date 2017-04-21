import javax.swing.*;
import java.awt.*;

/**
 * Created by hiroshi on 19/04/17.
 */
public class TetrisJPanel extends JPanel implements TetrisPainter {

    protected int[][] board = null;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (board != null) {
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
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x * boxWidth + xGap, y * boxHeight + yGap, boxWidth - 1, boxHeight - 1);
                }
            }
        }
    }

    @Override
    public void paintTetris(int[][] board) {
        this.board = board;
        this.repaint();
    }
}

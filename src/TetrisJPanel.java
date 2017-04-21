import javax.swing.*;
import java.awt.*;

/**
 * Created by hiroshi on 19/04/17.
 */
public class TetrisJPanel extends JPanel {

    protected int[][] board;
    protected int boardWidth;
    protected int boardHeight;

    public TetrisJPanel(int[][] board, int boardWidth, int boardHeight) {
        this.board = board;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int boxWidth = this.getWidth() / this.boardWidth;
        int boxHeight = this.getHeight() / this.boardHeight;

        int xGap = (this.getWidth() - boxWidth * this.boardWidth) / 2;
        int yGap = (this.getHeight() - boxHeight * this.boardHeight) / 2;

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

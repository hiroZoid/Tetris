import javax.swing.*;

public class Tetris {

    protected int boardWidth;
    protected int boardHeight;

    protected JFrame jFrame;
    protected JPanel jPanel;

    protected TetrisGame tetrisGame;

    public Tetris(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        tetrisGame = new TetrisGame(boardWidth, boardHeight) {
            @Override
            public void drawScreen() {
                jPanel.repaint();
//                for (int x = 0; x < boardWidth; x++) {
//                    for (int y = 0; y < boardHeight; y++) {
//                        System.out.print(tetrisGame.getBoard()[x][y]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
        };

        jPanel = new TetrisJPanel(tetrisGame.getBoard(), boardWidth, boardHeight);

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
}

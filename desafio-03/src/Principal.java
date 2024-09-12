import javax.swing.JFrame;
import java.awt.Dimension;

public class Principal {
    public static final int WIDTH_SCREEN = 640;
    public static final int HEIGHT_SCREEN = 480;
    public JFrame window;
    public Game game;

    public Principal() {
        this.window = new JFrame("Game 2D");
        this.game = new Game();
        game.setPreferredSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));
        window.getContentPane().add(game);
        window.setLocation(100, 100);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
    }

    public static void main(String[] args) {
        new Principal();
    }
}

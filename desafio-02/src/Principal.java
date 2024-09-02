import javax.swing.JFrame;
import java.awt.Dimension;

public class Principal {
    public static final int LARGURA_TELA = 640;
    public static final int ALTURA_TELA = 480;

    public Principal() {
        JFrame window = new JFrame("Game 2D");
        Game game = new Game();
        game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        window.getContentPane().add(game);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(100, 100);
        window.setVisible(true);
        window.pack();
    }

    public static void main(String[] args) {
        new Principal();
    }

}

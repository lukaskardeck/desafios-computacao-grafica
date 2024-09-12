import java.awt.Dimension;

import javax.swing.JFrame;

public class Principal{

    public static final int LARGURA_TELA = 640;
    public static final int ALTURA_TELA = 480;

    public Principal(){
        JFrame janela = new JFrame();
        Game game = new Game();
        game.setPreferredSize(new Dimension(LARGURA_TELA,ALTURA_TELA));
        janela.getContentPane().add(game);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocation(100,100);
        janela.setVisible(true);
        janela.pack();
    }

    public static void main(String[] args) {
        new Principal();
    }
}
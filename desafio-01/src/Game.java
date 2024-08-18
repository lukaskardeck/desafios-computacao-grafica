import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Game extends JPanel {

    public static final int MENOR_RAIO = 5;
    public static final int MAIOR_RAIO = 50;
    public static final int NUM_ESFERAS = 10;

    public Game() {
        setFocusable(true);
        setLayout(null);
    }

    // MÉTODO SOBRESCRITO ---------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);    // Define o fundo do "Panel" como um cinza claro

        // Realiza um laço de repetição para desenhar n esferas no "Panel"
        for (int i = 0; i < NUM_ESFERAS; i++) { 
            // Faz com que a cor a ser utilizada no desenho seja escolhida de maneira randomica
            g.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
    
            // Diametro randomico, no intervalo entre o menor e maior raio definidos
            int diametro = (new Random().nextInt(MAIOR_RAIO - MENOR_RAIO + 1) + MENOR_RAIO) * 2;

            // Posições horizontal e vertical de cada esfera, definidas de maneira randomica e não permitindo ultrapassar os limites do Panel
            int posX = new Random().nextInt(Principal.LARGURA_TELA - diametro);
            int posY = new Random().nextInt(Principal.ALTURA_TELA - diametro);

            // Desenha uma esfera na tela
            g.fillOval(posX, posY, diametro, diametro);
        }
    }
}

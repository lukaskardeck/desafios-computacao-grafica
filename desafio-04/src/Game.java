import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JPanel;

public class Game extends JPanel {
    public static final int NUM_BOLAS = 10;

    public Random random = new Random();
    public ArrayList<Bola> listaBolas;

    public Game() {
        setFocusable(true);
        setLayout(null);

        this.listaBolas = new ArrayList<>();

        for (int i = 0; i < NUM_BOLAS; i++) {
            Bola bola = new Bola();

            bola.radius = random.nextInt(21) + 20;

            bola.posX = random.nextInt(Principal.LARGURA_TELA - bola.radius);
            bola.posY = random.nextInt(Principal.ALTURA_TELA - bola.radius);

            int sentidoVelX, sentidoVelY;

            do {
                sentidoVelX = random.nextInt(3) - 1;
            } while (sentidoVelX == 0);

            do {
                sentidoVelY = random.nextInt(3) - 1;
            } while (sentidoVelY == 0);

            bola.velX *= sentidoVelX;
            bola.velY *= sentidoVelY;
            
            listaBolas.add(bola);
        }

        new Thread(() -> gameloop()).start();
    }

    // GAMELOOP-----------------------------------------------------------------
    public void gameloop() {
        while (true) {
            handlerEvents();
            update();
            render();
            try {
                Thread.sleep(17);
            } catch (Exception e) {
            }
        }
    }

    public void handlerEvents() {}

    public void update() {
        for (int i = 0; i < listaBolas.size(); i++) {
            Bola bola = listaBolas.get(i);
            bola.update();
            bola.changeRadius();
            bola.changeColor();
        }

        testeColisoes();
    }

    public void render() {
        repaint();
    }

    // MÉTODOS -----------------------------------------------------------------
    public void testeColisoes() {
        for (int i = 0; i < listaBolas.size(); i++) {
            Bola bola = listaBolas.get(i);

            if (bola.posX - bola.radius < 0) { // colisão à esquerda
                bola.posX += 1;
                bola.velX *= -1;

            } else if (bola.posX + (bola.radius * 2) > Principal.LARGURA_TELA + bola.radius) { // colisão à direita
                bola.posX -= 1;
                bola.velX *= -1;
            }
            if (bola.posY - bola.radius < 0) { // colisão acima
                bola.posY += 1;
                bola.velY *= -1;
            } else if (bola.posY + (bola.radius * 2) > Principal.ALTURA_TELA + bola.radius) { // colisão abaixo
                bola.posY -= 1;
                bola.velY *= -1;
            }
        }
    }

    // MÉTODO SOBRESCRITO ------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.lightGray);
        for (int i = 0; i < listaBolas.size(); i++) {
            Bola bola = listaBolas.get(i);
            g.setColor(bola.color);
            g.fillOval(bola.posX - bola.radius, bola.posY - bola.radius, bola.radius * 2, bola.radius * 2);
        }
    }
}

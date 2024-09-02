import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Game extends JPanel {
    public static final int NUM_SQUARES = 15;

    private Random random = new Random();
    private ArrayList<Square> squares;

    public Game() {
        setFocusable(true);
        setLayout(null);

        this.squares = new ArrayList<Square>();

        int sideRandom, velXRandom, velYRandom, angleRotationRandom;
        Color colorRandom;

        for (int i = 0; i < NUM_SQUARES; i++) {
            sideRandom = random.nextInt(86) + 15;

            do {
                velXRandom = random.nextInt(5) - 2;
            } while (velXRandom == 0);

            do {
                velYRandom = random.nextInt(5) - 2;
            } while (velYRandom == 0);

            do {
                angleRotationRandom = random.nextInt(11) - 5;
            } while (angleRotationRandom == 0);

            colorRandom = new Color(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256));

            Square square = new Square();
            square.editSquare(
                    sideRandom,
                    velXRandom,
                    velYRandom,
                    angleRotationRandom,
                    colorRandom);
            squares.add(square);
        }

        new Thread(() -> gameloop()).start();
    }

    // GAMELOOP ------------------------
    public void gameloop() {
        while (true) {
            handlerEvents();
            update();
            render();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
            }
        }

    }

    public void handlerEvents() {
    }

    public void update() {
        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            square.move();
            square.rotate();
        }

        checkCollision();
    }

    public void render() {
        repaint();
    }

    // MÉTODOS --------------------------
    public void checkCollision() {
        for (int i = 0; i < squares.size(); i++) {
            Square sq = squares.get(i);

            // Checando colisão na horizontal
            if (sq.posX < 0 || (sq.posX + sq.side) > Principal.LARGURA_TELA) {
                sq.velX *= -1; // Inverte sentido do movimento horizontal
                sq.angleRotation *= -1; // Inverte o angulo de rotação
            }

            // Checando colisão na vertical
            if (sq.posY < 0 || (sq.posY + sq.side) > Principal.ALTURA_TELA) {
                sq.velY *= -1; // Inverte sentido do movimento vertical
                sq.angleRotation *= -1; // Inverte o angulo de rotação
            }
        }
    }

    // MÉTODO SOBRESCRITO ---------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);

        Graphics2D g2D = (Graphics2D) g;
        for (Square square : squares) {
            // Configura a rotação em torno do centro do quadrado
            AffineTransform at = AffineTransform.getRotateInstance(
                    square.initialAngle,
                    square.posX + square.side / 2,
                    square.posY + square.side / 2);

            g2D.setTransform(at);
            g2D.setColor(square.color);
            g2D.fillRect(square.posX, square.posY, square.side, square.side);
        }
        g2D.dispose();
    }
}

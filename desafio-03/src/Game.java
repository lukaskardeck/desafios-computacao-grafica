import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

public class Game extends JPanel{
    public static final int NUM_BALLS = 16;

    public Stack<Ball> stackBalls;
    public ArrayList<Ball> listBalls;
    
    public Game() {
        setLayout(null);
        setFocusable(true);

        stackBalls = new Stack<>();
        listBalls = new ArrayList<>();

        for (int i = 0; i < NUM_BALLS; i++) {
            stackBalls.push(new Ball());
        }

        listBalls.add(stackBalls.pop());

        new Thread(() -> gameloop()).start();
    }

    // GAMELOOP -------------------------
    public void gameloop() {
        while (true) {
            handlerEvents();
            update();
            render();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {}
        }
    }

    public void handlerEvents() {}

    public void update() {
        for (int i = 0; i < listBalls.size(); i++) {
            listBalls.get(i).move();
        }
        checkCollision();
    }

    public void render() {
        repaint();
    }

    // MÉTODOS --------------------------
    public void checkCollision() {
        for (int i = 0; i < listBalls.size(); i++) {
            Ball ball = listBalls.get(i);
            if (ball.posX < 0 || (ball.posX + ball.diameter) > Principal.WIDTH_SCREEN) {
                ball.velX *= -1;
                if (!stackBalls.isEmpty()) {
                    addBallsInGame((ball.posX - (ball.velX * -1)), ball.posY, ball.velX, ball.velY * -1);
                }
            }
    
            if (ball.posY < 0 || (ball.posY + ball.diameter) > Principal.HEIGHT_SCREEN) {
                ball.velY *= -1;
                if (!stackBalls.isEmpty()) {
                    addBallsInGame(ball.posX , (ball.posY - (ball.velY * -1)), ball.velX * -1, ball.velY);
                }
            }
        }
    }

    public void addBallsInGame(int posX, int posY, int velX, int velY) {
        Ball ball = stackBalls.pop();
        ball.posX = posX;
        ball.posY = posY;
        ball.velX = velX;
        ball.velY = velY;
        listBalls.add(ball);
    }


    // MÉTODOS SOBRESCRITOS -------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.green);
        g.setColor(Color.magenta);
        for (int i = 0; i < listBalls.size(); i++) {
            Ball ball = listBalls.get(i);
            g.fillOval(ball.posX, ball.posY, ball.diameter, ball.diameter);
        }
    }
}

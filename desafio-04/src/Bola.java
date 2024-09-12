import java.awt.Color;

public class Bola {
    public int posX;
    public int posY;
    public int velX;
    public int velY;
    public int radius;
    public Color color; // usado para desenhar a cor da bola
    public int r, g, b; // usado como componentes da cor

    // Flags para fazer o ciclo de mudança de raio e cor
    public boolean restartColor = true, restartRadius = true;

    public Bola() {
        r = 255;
        g = 0;
        b = 0;
        color = new Color(r, g, b);
        radius = 30;
        posX = 300;
        posY = 300;
        velX = 3;
        velY = 3;
    }

    public void update() {
        posX += velX;
        posY += velY;
    }

    public void changeRadius() {
        if (radius > 20 && restartRadius) {
            radius -= 1;
        } else {
            radius += 1;
            restartRadius = false;
            if (radius >= 40)
                restartRadius = true;
        }
    }

    public void changeColor() {

        if (r > 0 && g < 255 && restartColor) {
            r -= 5;
            g += 5;
        } else if (g > 0 && b < 255) {
            g -= 5;
            b += 5;
        } else if (r < 255 && b > 0) {
            b -= 5;
            r += 5;
            restartColor = false;
            if (r == 255 && b == 0)
                restartColor = true;
        }

        color = new Color(r, g, b);
    }
}

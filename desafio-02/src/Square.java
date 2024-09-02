import java.awt.Color;

public class Square {
    public int posX;
    public int posY;
    public int side;
    public double initialAngle;
    public double angleRotation;
    public int velX;
    public int velY;
    public Color color;

    public Square() {
        this.side = 100;
        this.posX = (Principal.LARGURA_TELA / 2) - (this.side / 2);
        this.posY = (Principal.ALTURA_TELA / 2) - (this.side / 2);
        this.angleRotation = 5.0;
        this.initialAngle = 0.0;
        this.velX = 2;
        this.velY = 2;
        this.color = Color.red;
    }

    public void editSquare(int side, int velX, int velY, int angleRotation, Color color) {
        this.side = side;
        this.velX = velX;
        this.velY = velY;
        this.angleRotation = angleRotation;
        this.color = color;
    }

    public void move() {
        posX += velX;
        posY += velY;
    }

    public void rotate() {
        initialAngle += Math.toRadians(angleRotation);
    }
}

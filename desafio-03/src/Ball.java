public class Ball {
    public int posX;
    public int posY;
    public int radio;
    public int diameter;
    public int velX;
    public int velY;

    public Ball() {
        posX = 250;
        posY = 300;
        radio = 25;
        diameter = radio * 2;
        velX = velY = 5;
    }

    public void move() {
        posX += velX;
        posY += velY;
    }
}

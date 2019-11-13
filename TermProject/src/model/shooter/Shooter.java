package model.shooter;

import controller.Main;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Shooter extends GameFigure {

    public static final int STATE_ALIVE = 0;
    public static final int STATE_FALLING = 1;
    public static int UNIT_MOVE_FALLING = 5;
    public static int BASE_SIZE = 20;
    public static final int BARREL_LEN = 20;
    public static final int UNIT_MOVE = 10; // 10 pixels by 4 arrow keys
    public static int PLAYER_LIVES = 3;
    int state;
    public Rectangle2D.Float base;
    public Line2D.Float barrel;
    Color color;
    ShooterAnimStrategy animStrategy;

    private static int playerScore = 0;

    public Shooter(int x, int y) {
        super(x, y);
        base = new Rectangle2D.Float(x - BASE_SIZE / 2, y - BASE_SIZE / 2, BASE_SIZE, BASE_SIZE);
        barrel = new Line2D.Float(x, y, x, y - BARREL_LEN);
        color = Color.cyan;
        state = STATE_ALIVE;
        animStrategy = new ShooterAnimIdle(this);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(7)); //thickness of the line
       // g2.fill(base);
        g2.draw(barrel);
        g2.draw(base);
    }

    @Override
    public void update() {
        double rad = Math.cos(180);
        float barrel_y = -90;
        float barrel_x = (float) (BARREL_LEN * Math.cos(rad));

        barrel.x1 = super.location.x;
        barrel.y1 = super.location.y;
        barrel.x2 = super.location.x;
        barrel.y2 = super.location.y;

        base.x = location.x - BASE_SIZE / 2;
        base.y = location.y - BASE_SIZE / 2;

        updateState();
        animStrategy.animate();
    }

    private void updateState() {
        if (hitCount > 0 && state == STATE_ALIVE) {
            state = STATE_FALLING;
            Shooter.PLAYER_LIVES--;
            Main.updatePlayerLifeLabel();
        } else if (state == STATE_FALLING) {
            animStrategy = new ShooterAnimFalling(this);
        }
        if(Shooter.PLAYER_LIVES <= 0)
            Main.endGame();

    }

    public static void updateScore(int score){
        playerScore += score;
    }

    public static int getPlayerScore(){
        return playerScore;
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}

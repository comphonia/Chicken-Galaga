package model.shooter;

import controller.Main;
import controller.state.ShooterAliveState;
import controller.state.ShooterFallingState;
import controller.state.ShooterLifeState;
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
    private int effectLifetime = 0;
    private String effect = "";
    public ShooterLifeState state;
    public Rectangle2D.Float base;
    public Line2D.Float barrel;
    Color color;
    private ShooterAnimStrategy animStrategy;

    private static int playerScore = 0;

    public Shooter(int x, int y) {
        super(x, y - 17);
        base = new Rectangle2D.Float(x - BASE_SIZE / 2, y - BASE_SIZE / 2, BASE_SIZE, BASE_SIZE);
        barrel = new Line2D.Float(x, y, x, y - BARREL_LEN);
        color = Color.cyan;
        state = new ShooterAliveState();
        effect = "shield";
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
        effectLifetime++;
        if (effectLifetime <= 100) {
            doEffect(effect);
        } else {
            removeEffect();
        }
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
        if (hitCount > 0 && getState() == STATE_ALIVE) {
            state.doState(this);
            setState(new ShooterFallingState());
        } else if (getState() == STATE_FALLING) {
            state.doState(this);
            animStrategy = new ShooterAnimFalling(this);
        }
        if (Shooter.PLAYER_LIVES <= 0)
            Main.endGame();
    }

    public void setState(ShooterLifeState state) {
        this.state = state;
    }

    private int getState() {
        if (state instanceof ShooterAliveState)
            return STATE_ALIVE;
        return STATE_FALLING;
    }

    private boolean removeFlag = false;

    public void doEffect(String effect) {
        if (!removeFlag && !effect.isEmpty()) {
            removeFlag = true;
            this.effect = effect;
            effectLifetime = 0;
            if (effect.equals("shield")) {
                addShield();
            } else if (effect.equals("life")) {
                addLife();
            }
        }
    }


    private void removeEffect() {
        if (removeFlag) {
            removeFlag = false;
            color = Color.cyan;
            hitCount = 0;
            this.effect = "";
        }
    }

    private void addShield() {
        color = Color.YELLOW;
        hitCount = -9999;
    }

    private void addLife() {
        Shooter.PLAYER_LIVES++;
        Main.updatePlayerLifeLabel();
    }

    public static void updateScore(int score) {
        playerScore += score;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    @Override
    public int getCollisionRadius() {
        return (int) (20 / 2 * 0.75);
    }
}

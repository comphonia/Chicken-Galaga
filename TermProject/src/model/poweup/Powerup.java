package model.poweup;

import controller.Main;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Point2D;

public class Powerup extends GameFigure {

    public static final int UNIT_MOVE = 5;
    public static final int MAX_DROPPING_SIZE = 30;

    public static final int STATE_COOKING = 0;
    public static final int STATE_DROPPING = 1;
    public static final int STATE_EXPLODING = 2;
    public static final int STATE_DONE = 3;

    int size = 15;
    Point2D.Float target; // where mouse was pressed
    public Color color;
    public String effect;
    public int state;
    PowerupAnimStrategy animStrategy;

    public Powerup(int type) {
        super.location.x = Main.win.canvas.width/2f;
        super.location.y = 0;
        target = new Point2D.Float(Main.win.canvas.width/2f, 90);
        color = type == 0 ? Color.YELLOW : Color.CYAN;
        effect = type == 0 ? "shield" : "life";
        state = STATE_DROPPING;
        animStrategy = new PowerupAnimFalling(this);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5)); // thickness of the line
        g2.fillOval((int) super.location.x - size / 2, (int) super.location.y - size / 2, size, size);
    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate();
    }

    private void updateState() {
        if (state == STATE_DROPPING) {
            if (hitCount > 100 || target.distance(location) >= Main.win.canvas.height + 100) {
                state = STATE_DONE;
            }
        } else if (state == STATE_DONE) {
            super.done = true;
        }
    }

    @Override
    public int getCollisionRadius() {
        return size / 2;
    }
}

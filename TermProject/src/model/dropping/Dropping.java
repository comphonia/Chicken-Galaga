package model.dropping;

import controller.Main;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Point2D;

public class Dropping extends GameFigure {

    public static final int UNIT_MOVE = 5;
    public static final int INIT_MISSILE_SIZE = 5;
    public static final int MAX_DROPPING_SIZE = 30;

    public static final int STATE_COOKING = 0;
    public static final int STATE_DROPPING = 1;
    public static final int STATE_EXPLODING = 2;
    public static final int STATE_DONE = 3;

    int size = INIT_MISSILE_SIZE;
    Point2D.Float target; // where mouse was pressed
    public Color color;
    public int state;
    DroppingAnimStrategy animStrategy;

    public Dropping(int tx, int ty) {
        super.location.x = tx;
        super.location.y = ty;
        target = new Point2D.Float(tx, 90);
        color = new Color(0f, 0f, 0f, 0f);
        state = STATE_COOKING;
        animStrategy = new DroppingAnimDrop(this);
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
        if(state == STATE_COOKING){

        }
        else if(state== STATE_DROPPING) {
            if (hitCount>0 || target.distance(location) >= Main.win.canvas.height) {
                state = STATE_EXPLODING;
                animStrategy = new DroppingAnimExploding(this);
            }
        }else if(state ==STATE_EXPLODING){
                if(size >= MAX_DROPPING_SIZE){
                    state=STATE_DONE;
                }
            }else if(state == STATE_DONE){
                super.done = true;
            }
        }

    @Override
    public int getCollisionRadius() {
        return size/2;
    }
}

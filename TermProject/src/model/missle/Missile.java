package model.missle;

import controller.Main;
import model.GameFigure;
import model.shooter.Shooter;

import java.awt.*;
import java.awt.geom.Point2D;

public class Missile extends GameFigure {

    public static final int UNIT_MOVE = 5;
    public static final int INIT_MISSILE_SIZE = 5;
    public static final int MAX_MISSILE_SIZE = 30;

    public static final int STATE_SHOOTING = 0;
    public static final int STATE_EXPLODING = 1;
    public static final int STATE_DONE = 2;

    int size = INIT_MISSILE_SIZE;
    Point2D.Float target; // where mouse was pressed
    Color color;
    int state;
    MissileAnimStrategy animStrategy;

    public Missile(int tx, int ty) {
        Shooter shooter = (Shooter) Main.gameData.fixedObject.get(0);
        super.location.x = shooter.barrel.x2;
        super.location.y = shooter.barrel.y2;
        target = new Point2D.Float(shooter.base.x, -90);
        color = Color.RED;
        state =STATE_SHOOTING;
        animStrategy = new MissileAnimShooting(this);
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
        if(state==STATE_SHOOTING) {
            if (hitCount>0 || target.distance(location) <= 3.0) {
                state = STATE_EXPLODING;
                animStrategy = new MissileAnimExploding(this);
            }
        }else if(state ==STATE_EXPLODING){
                if(size >= MAX_MISSILE_SIZE){
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

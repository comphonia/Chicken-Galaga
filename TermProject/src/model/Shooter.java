package model;

import controller.Main;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Shooter extends GameFigure {

    public final int BASE_SIZE = 20;
    public final int BARREL_LEN = 20;
    public static final int UNIT_MOVE = 10; // 10 pixels by 4 arrow keys
    public Rectangle2D.Float base;
    public Line2D.Float barrel;

    public Shooter(int x, int y){
        super(x,y);
        base = new Rectangle2D.Float(x-BASE_SIZE/2, y-BASE_SIZE/2,BASE_SIZE,BASE_SIZE);
        barrel = new Line2D.Float(x,y,x,y-BARREL_LEN);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(7)); //thickness of the line
        g2.draw(barrel);
        g2.draw(base);
    }

    @Override
    public void update() {
        MousePointer mousePointer = (MousePointer) Main.gameData.fixedObject.get(Main.INDEX_MOUSE_POINTER);
        float tx = mousePointer.location.x;
        float ty = mousePointer.location.y;
        double rad = Math.atan2(ty - super.location.y, tx - super.location.x);
        float barrel_y = (float)(BARREL_LEN * Math.sin(rad));
        float barrel_x = (float)(BARREL_LEN * Math.cos(rad));

        barrel.x1 = super.location.x;
        barrel.y1 = super.location.y;
        barrel.x2 = super.location.x+barrel_x;
        barrel.y2 = super.location.y+barrel_y;

        base.x = location.x - BASE_SIZE /2;
        base.y = location.y - BASE_SIZE /2;

    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}

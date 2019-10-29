package model.ufo;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class UFO extends GameFigure implements Subject {

    public static int UNIT_MOVE = 5;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING =0;
    public static final int STATE_FALLING =1;
    public static final int STATE_DONE =2;

    int size = 40;
    int width,height;
    boolean movinRight = true;
    int state;
    Color color;
    UFOAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public UFO(int x, int y){
        super(x,y);
        width = size;
        height = size /2;
        state=STATE_FLYING;
        color = Color.white;
        animStrategy = new UFOAnimFlying(this);
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1)); //thickness of the line
        g2.fillOval((int)location.x - width/2,(int)location.y - height/2,width,height);
    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate();
    }

    private void updateState() {
        if(state == STATE_FLYING){
            if(hitCount>0){
                state = STATE_FALLING;
                animStrategy = new UFOAnimFalling(this);
            }
        }else if(state == STATE_FALLING){
            if(location.y >= Main.win.canvas.height){
                state=STATE_DONE;
            }
        }else if(state == STATE_DONE){
            super.done  =true;
            notifyEvent();; // UFO died
        }
    }

    @Override
    public int getCollisionRadius() {
      return (int)(size/2*0.75);
    }

    @Override
    public void attachListener(Observer o) {
        listeners.add(o);
    }

    @Override
    public void detachListener(Observer o) {
    listeners.remove(o);
    }

    @Override
    public void notifyEvent() {
        for(var o: listeners){
            o.eventReceived();
        }
    }
}

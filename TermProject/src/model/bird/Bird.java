package model.bird;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class Bird extends GameFigure implements Subject {

    public static int UNIT_MOVE = 5;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING =0;
    public static final int STATE_ENTRY =3;
    public static final int STATE_FALLING =1;
    public static final int STATE_DONE =2;
    public static int AVAILABLE_UNITS = 0;

    int size = 40;
    int width,height;
    int destinationx, destinationy;
    boolean movinUp = true;
    boolean movingToLocation = true;
    int state;
    Color color;
    BirdAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public Bird(int x, int y, int a, int b){
        super(x,y);
        destinationx = a;
        destinationy = b;
        width = size;
        height = size /2;
        state=STATE_ENTRY;
        color = Color.white;
        animStrategy = new BirdAnimFlying(this);
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
        if(state == STATE_ENTRY){
            animStrategy = new BirdAnimEntry(this);
            if(location.x >= destinationx && location.y >= destinationy){
                state = STATE_FLYING;
            }
        }
        else if(state == STATE_FLYING){
            animStrategy = new BirdAnimFlying(this);
            if(hitCount>0){
                state = STATE_FALLING;
                animStrategy = new BirdAnimFalling(this);
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

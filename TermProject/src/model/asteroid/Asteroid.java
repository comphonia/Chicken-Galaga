package model.asteroid;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends GameFigure implements Subject {

    public static int UNIT_MOVE = 5;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING =0;
    public static final int STATE_ENTRY =3;
    public static final int STATE_FALLING =1;
    public static final int STATE_DONE =2;
    public static int AVAILABLE_UNITS = 0;

    int size = 40;
    int smallSize = 20;
    int width,height;
    int destinationx, destinationy;
    boolean movingRight = true;
    boolean movingToLocation = true;
    int state;
    Color color;
    AsteroidAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public Asteroid(int x, int y, int a, int b,int size){
        super(x,y);
        destinationx = a;
        destinationy = b;
        width = size;
        height = size;
        state=STATE_ENTRY;
        color = Color.GRAY;
        animStrategy = new AsteroidAnimFlying(this);
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

    private boolean firstHit, secondHit;
    private void updateState() {
        if(state == STATE_ENTRY){
            animStrategy = new AsteroidAnimEntry(this);
            if(location.x >= destinationx && location.y >= destinationy){
                state = STATE_FLYING;
            }
        }
        else if(state == STATE_FLYING){
            animStrategy = new AsteroidAnimFlying(this);
            if(hitCount>0) {
                if(!firstHit) {
                    Main.updateScoreLabel(5);
                    firstHit = true;
                }
                if(width > smallSize || height > smallSize){
                    width-=smallSize;
                    height-=smallSize;
                }
            }
            if(hitCount>1){
                if(!secondHit) {
                    Main.updateScoreLabel(10);
                    secondHit = true;
                }
                state = STATE_FALLING;
                animStrategy = new AsteroidAnimFalling(this);
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

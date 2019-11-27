package model.dropping;

import controller.Main;

import java.awt.*;

public class DroppingAnimDrop implements DroppingAnimStrategy {
    Dropping context;
    private int tempTime=0;
    private float saveX, saveY;
    public DroppingAnimDrop(Dropping context){
        this.context = context;
        saveX = context.location.x;
        saveY = context.location.y;
    }

    @Override
    public void animate() {
        if(Main.playedTime > tempTime+200) {
            context.color = Color.GREEN;
            context.location.y -= context.UNIT_MOVE * Math.sin(-90);
        }
        if(context.location.y >= Main.win.canvas.height + 150){
            tempTime = Main.playedTime;
            context.color = new Color(0f, 0f, 0f, 0f);
            context.location.x = saveX;
            context.location.y = saveY;
            context.state = context.STATE_COOKING;
        }
    }
}

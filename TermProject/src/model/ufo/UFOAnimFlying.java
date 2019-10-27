package model.ufo;

import controller.Main;

public class UFOAnimFlying implements UFOAnimStrategy {

    UFO context;

    public UFOAnimFlying(UFO context){
        this.context = context;
    }

    @Override
    public void animate() {
        if(context.location.x >= Main.win.canvas.width){
            context.movinRight = false;
        }else if(context.location.x <= 0){
            context.movinRight = true;
        }
        if(context.movinRight) context.location.x += context.UNIT_MOVE;
        else context.location.x -=context.UNIT_MOVE;
    }
}

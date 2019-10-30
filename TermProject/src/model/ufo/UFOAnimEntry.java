package model.ufo;

import controller.Main;

public class UFOAnimEntry implements UFOAnimStrategy {

    UFO context;

    public UFOAnimEntry(UFO context){
        this.context = context;
    }

    @Override
    public void animate() {
        if(context.location.x <= context.destinationx){
            context.location.x += context.UNIT_MOVE;
        }
        if(context.location.y <= context.destinationy){
            context.location.y += context.UNIT_MOVE;
        }
    }
}

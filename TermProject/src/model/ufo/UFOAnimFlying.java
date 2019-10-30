package model.ufo;

public class UFOAnimFlying implements UFOAnimStrategy {

    UFO context;
    int newDestination;

    public UFOAnimFlying(UFO context){
        this.context = context;
    }

    @Override
    public void animate() {
        if(context.location.y >= context.destinationy +10){
            context.movinUp = false;
        }else if(context.location.y <= context.destinationy - 10){
            context.movinUp = true;
        }
        if(context.movinUp) context.location.y += context.UNIT_MOVE/2;
        else context.location.y -=context.UNIT_MOVE/2;
    }
}

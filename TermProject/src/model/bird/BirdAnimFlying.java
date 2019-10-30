package model.bird;

public class BirdAnimFlying implements BirdAnimStrategy {

    Bird context;
    int newDestination;

    public BirdAnimFlying(Bird context){
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

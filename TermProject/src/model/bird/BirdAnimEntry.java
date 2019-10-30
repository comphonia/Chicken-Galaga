package model.bird;

public class BirdAnimEntry implements BirdAnimStrategy {

    Bird context;

    public BirdAnimEntry(Bird context){
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

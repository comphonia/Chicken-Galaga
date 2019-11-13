package model.dropping;

import java.awt.*;

public class DroppingAnimExploding implements DroppingAnimStrategy {

    Dropping context;

    public DroppingAnimExploding(Dropping context){
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.YELLOW;
        ++context.size;
    }
}

package model;

import java.util.ArrayList;

public class GameData {

    public ArrayList<GameFigure> fixedObject = new ArrayList<GameFigure>();
    public ArrayList<GameFigure> friendObject = new ArrayList<GameFigure>();
    public ArrayList<GameFigure> enemyObject = new ArrayList<GameFigure>();

    public void update(){

        ArrayList<GameFigure> remove = new ArrayList<>();
        for (var fig: fixedObject){
            if(fig.done) remove.add(fig);
            else fig.update();
        }
        fixedObject.removeAll(remove);

        remove.clear();
        for (var fig: friendObject){
            if(fig.done) remove.add(fig);
            else fig.update();
        }
        friendObject.removeAll(remove);

        remove.clear();
        for (var fig: enemyObject){
            if(fig.done) remove.add(fig);
            else fig.update();
        }
        enemyObject.removeAll(remove);
    }

    public void clear() {
        fixedObject.clear();
        friendObject.clear();
        enemyObject.clear();
    }
}

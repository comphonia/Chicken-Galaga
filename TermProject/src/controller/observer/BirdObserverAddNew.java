package controller.observer;

import controller.InputEvent;
import controller.Main;

public class BirdObserverAddNew implements Observer{
    @Override
    public void eventReceived() {
      //  System.out.println("UFO died");
        //Main.addUFOwithListener(100,200);
        InputEvent event = new InputEvent();
        event.event = new BirdCreateEvent("UFO",100,100);
        event.type = InputEvent.UFO_CREATE;
        Main.playerInputEventQueue.queue.add(event);
    }
}

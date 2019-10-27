package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyEventListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e){

        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = inputEvent.KEY_PRESSED;
        Main.playerInputEventQueue.queue.add(inputEvent);

    }
}

package controller;

import controller.observer.UFOCreateEvent;
import model.missle.Missile;
import model.MousePointer;
import model.Shooter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlayerInputEventQueue {

    public LinkedList<InputEvent> queue = new LinkedList<InputEvent>();

    public void processInputEvents() {
        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();

            switch (inputEvent.type) {
                case InputEvent.MOUSE_PRESSED:
                    MouseEvent e = (MouseEvent) inputEvent.event;
                    Missile m = new Missile(e.getX(), e.getY());
                    Main.gameData.friendObject.add(m);
                    break;
                case InputEvent.MOUSE_MOVED:
                    MousePointer mp = (MousePointer) Main.gameData.fixedObject.get(0);
                    MouseEvent me = (MouseEvent) inputEvent.event;
                    mp.location.x = me.getX();
                    mp.location.y = me.getY();
                    break;
                case InputEvent.KEY_PRESSED:
                    var shooter = Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            shooter.location.y -= Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_DOWN:
                            shooter.location.y += Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_LEFT:
                            shooter.location.x -= Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_RIGHT:
                            shooter.location.x += Shooter.UNIT_MOVE;
                            break;
                    }
                    break;
                    case InputEvent.UFO_CREATE:
                        UFOCreateEvent ue = (UFOCreateEvent) inputEvent.event;
                        Main.addUFOwithListener(ue.getX(),ue.getY());
            }
        }
    }
}

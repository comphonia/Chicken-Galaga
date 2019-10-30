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
                case InputEvent.KEY_PRESSED:
                    var shooter = Main.gameData.fixedObject.get(0);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                if( ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    Missile m = new Missile(0, 0);
                    Main.gameData.friendObject.add(m);
                }
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if(shooter.location.y > 0 + Shooter.BASE_SIZE - 2) {
                                shooter.location.y -= Shooter.UNIT_MOVE;
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if(shooter.location.y < Main.win.canvas.height - Shooter.BASE_SIZE) {
                                shooter.location.y += Shooter.UNIT_MOVE;
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if(shooter.location.x > 0 + Shooter.BASE_SIZE/2){
                                   shooter.location.x -= Shooter.UNIT_MOVE;
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if(shooter.location.x < Main.win.canvas.width - Shooter.BASE_SIZE) {
                                shooter.location.x += Shooter.UNIT_MOVE;
                            }
                            break;
                    }
                    break;
                    case InputEvent.UFO_CREATE:
                        UFOCreateEvent ue = (UFOCreateEvent) inputEvent.event;
                      //  Main.addUFOwithListener(ue.getX(),ue.getY());
            }
        }
    }
}

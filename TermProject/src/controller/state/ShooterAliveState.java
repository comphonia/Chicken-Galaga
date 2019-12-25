package controller.state;

import controller.Main;
import model.shooter.Shooter;

public class ShooterAliveState implements ShooterLifeState {
    @Override
    public void doState(Shooter context) {
        Shooter.PLAYER_LIVES--;
        Main.updatePlayerLifeLabel();
    }
}

package controller.state;

import controller.Main;
import model.shooter.Shooter;
import model.shooter.ShooterAnimFalling;

public class ShooterFallingState implements ShooterLifeState {
    @Override
    public void doState(Shooter context) {
        if (context.location.y >= Main.win.canvas.height + 150) {
            Main.spawnShooter();
            if (Shooter.PLAYER_LIVES <= 0)
                Main.running = false;
        }
    }
}

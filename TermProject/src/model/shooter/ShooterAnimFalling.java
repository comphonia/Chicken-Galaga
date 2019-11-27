package model.shooter;

import controller.Main;

import java.awt.*;

public class ShooterAnimFalling implements ShooterAnimStrategy {
    Shooter context;

    public ShooterAnimFalling(Shooter context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;
        if (context.location.y >= Main.win.canvas.height + 150) {
            Main.spawnShooter();
            if (Shooter.PLAYER_LIVES <= 0)
                Main.running = false;
        }
    }
}
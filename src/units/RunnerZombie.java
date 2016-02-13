package units;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import main.Background;
import main.ProjectileHolder;
import weapons.RunnerZombieWeapon;

public class RunnerZombie extends GenericZombie
{
    public RunnerZombie(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 6;
        range = 70;
        health = 60;
        weapon = new RunnerZombieWeapon(this, target, projectileHolder, false);
        color1 = new Color(76, 91, 118);
        color2 = new Color(110, 95, 62);
        maxHealth = health;
    }

    @Override
    public void moveAway(Rectangle rect)
    {
        if (!isDying)
        {
            shouldMoveUp = true;
            super.moveAway(rect);
        }
    }
}

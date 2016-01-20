package zombiehill;

import java.awt.Color;
import java.awt.Point;

public class ZombieCommando extends GenericZombie
{
    public ZombieCommando(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 6;
        range = 350;
        health = 240;
        weapon = new ZombiePistol(this, target, projectileHolder, false);
        color1 = new Color(90, 75, 42);
        color2 = new Color(110, 95, 62);
        maxHealth = health;
    }
    
    @Override
    protected void chooseMove()
    {
        shouldMoveUp = true;
        super.chooseMove();
    }
}
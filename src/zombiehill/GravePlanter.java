package zombiehill;

import java.awt.Color;
import java.awt.Point;

public class GravePlanter extends GenericZombie
{
    public GravePlanter(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 2;
        range = Integer.MAX_VALUE;
        health = 240;
        weapon = new GravePlanterWeapon(this, target, projectileHolder, false);
        color1 = new Color(83, 129, 55);
        color2 = new Color(110, 95, 62);
        maxHealth = health;
    }
    
    @Override
    protected boolean shouldAttack()
    {
        if (unitPoint.x <= -Main.screenWidth * 3 / 2 || unitPoint.x >= Main.screenWidth * 3 / 2)
        {
            return false;
        }
        return super.shouldAttack();
    }
}
package projectiles;

import java.awt.Point;

import units.GenericUnit;

public class InfinitePenProjectile extends GenericPenProjectile
{
    public InfinitePenProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
    }

    @Override
    public boolean damageReduce()
    {
        return false;
    }
}

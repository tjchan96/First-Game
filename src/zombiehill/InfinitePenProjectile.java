package zombiehill;

import java.awt.Point;

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

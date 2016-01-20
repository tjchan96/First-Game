package zombiehill;

import java.awt.Point;

public class GenericPenProjectile extends GenericProjectile
{
    public GenericPenProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
    }

    @Override
    public boolean damageReduce()
    {
        boolean remove = false;
        if (damage < originalDamage)
        {
            remove = true;
        }
        damage /= 2;
        return remove;
    }
}

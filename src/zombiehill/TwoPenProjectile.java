package zombiehill;

import java.awt.Point;

public class TwoPenProjectile extends GenericPenProjectile
{

	public TwoPenProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
	{
		super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
	}
	
	public boolean damageReduce()
    {
        boolean remove = false;
        if (damage <= (originalDamage / 4))
        {
            remove = true;
        }
        damage /= 2;
        return remove;
    }

}

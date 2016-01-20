package zombiehill;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author TJ
 */
public class KnifeProjectile extends GenericProjectile
{
    public KnifeProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius * 2, radius * 2);
    }

    @Override
    public boolean isOutOfBounds()
    {
        if ((projectilePoint.x - firingUnit.unitPoint.x) > 70 || (projectilePoint.x - firingUnit.unitPoint.x) < -20 || (projectilePoint.y - firingUnit.unitPoint.y) > 70 || (projectilePoint.y - firingUnit.unitPoint.y) < -20)
        {
            return true;
        }
        return super.isOutOfBounds();
    }
}
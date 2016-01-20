package zombiehill;

import java.awt.Graphics2D;
import java.awt.Point;

public class InvisibleProjectile extends GenericProjectile
{
    public InvisibleProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
    }
    
    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius * 2, radius * 2);
    }
}
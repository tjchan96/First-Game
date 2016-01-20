package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class NukeProjectile extends GenericProjectile
{
    public NukeProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
        radius = 20;
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        g2.rotate(Math.toRadians(angle), screenPoint.x, screenPoint.y);
        g2.setColor(Color.black);
        g2.fillOval(screenPoint.x, screenPoint.y, radius * 2, radius * 2);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius * 2, radius * 2);

        g2.setTransform(nonTransformed);
    }
}
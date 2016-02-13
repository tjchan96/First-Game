package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.Main;
import units.GenericUnit;

public class BoneProjectile extends GenericProjectile
{
    protected int angleExtra = 0;

    public BoneProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        angleExtra += 30;
        g2.rotate(Math.toRadians(angle + angleExtra), screenPoint.x, screenPoint.y);
        g2.setColor(new Color(228, 228, 228));
        g2.fillRect(screenPoint.x - radius, screenPoint.y, radius * 4, radius * 2);
        g2.fillOval(screenPoint.x + radius * 2, screenPoint.y - radius, radius * 2, radius * 2);
        g2.fillOval(screenPoint.x - radius * 2, screenPoint.y - radius, radius * 2, radius * 2);
        g2.fillOval(screenPoint.x + radius * 2, screenPoint.y + radius, radius * 2, radius * 2);
        g2.fillOval(screenPoint.x - radius * 2, screenPoint.y + radius, radius * 2, radius * 2);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius * 4, radius * 2);

        g2.setTransform(nonTransformed);
    }
}
package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.Main;
import units.GenericUnit;

public class Smoke extends GenericParticle
{
    public Smoke(GenericUnit firingUnit, Point projectilePoint, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, projectilePoint, damage, speed, 0, isPlayerProjectile);
        noCollision = true;
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        g2.rotate(Math.toRadians(angle), screenPoint.x, screenPoint.y);
        g2.setColor(Color.lightGray);
        g2.fillOval(screenPoint.x, screenPoint.y, radius, radius);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius, radius);

        g2.setTransform(nonTransformed);
    }
}
package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.Main;
import units.GenericUnit;

public class Sparks extends GenericParticle
{
    public Sparks(GenericUnit firingUnit, Point projectilePoint, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, projectilePoint, damage, speed, angle, isPlayerProjectile);
        noCollision = true;
        angleOffset = 50;
        speedOffset = 8;
        radiusOffset = 6;
        this.angle = (int) (Math.random() * angleOffset - angleOffset / 2) + angle;
        this.speed = (int) (Math.random() * speedOffset + speedOffset);
        radius = (int) (Math.random() * radiusOffset + radiusOffset);
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        g2.rotate(Math.toRadians(angle), screenPoint.x, screenPoint.y);
        g2.setColor(Color.yellow);
        g2.fillOval(screenPoint.x, screenPoint.y, radius * 2, radius);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius * 2, radius);

        g2.setTransform(nonTransformed);
    }
}

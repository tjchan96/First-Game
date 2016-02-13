package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.Main;
import units.GenericUnit;

public class Flame extends InfinitePenProjectile
{
    protected int angleOffset = 30;
    protected int speedOffset = 3;
    protected int radiusMax = 100;
    protected int radiusOffset = 40;
    private int colorDifference = 0;

    public Flame(GenericUnit firingUnit, Point projectilePoint, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, projectilePoint, damage, speed, 0, isPlayerProjectile);
        this.angle = (int) (Math.random() * angleOffset - angleOffset / 2) + angle;
        this.speed = (int) (Math.random() * speedOffset + speed);
        radiusMax = (int) (Math.random() * radiusOffset + radiusOffset);
        radius = 0;
        colorDifference = (int) (Math.random() * 40);
        shouldMakeParticle = true;
        noCollision = false;
    }

    @Override
    public void run()
    {
        radius += 5;
        super.run();
    }

    @Override
    public boolean isOutOfBounds()
    {
        if (radius >= radiusMax)
        {
            return true;
        }
        return super.isOutOfBounds();
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        g2.rotate(Math.toRadians(angle), screenPoint.x, screenPoint.y);
        g2.setColor(new Color(205, 122 + colorDifference, 38));
        g2.fillOval(screenPoint.x, screenPoint.y, Math.abs(radius), Math.abs(radius));
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius, radius);

        g2.setTransform(nonTransformed);
    }
}
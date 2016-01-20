package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Fire extends InfinitePenProjectile
{
    protected int angleOffset = 360;
    protected int speedOffset = 3;
    protected int radiusOffset = 10;
    private int colorDifference = 0;
    
    public Fire(GenericUnit firingUnit, Point projectilePoint, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, projectilePoint, damage, speed, 0, isPlayerProjectile);
        radiusOffset = speed;
        this.angle = (int) (Math.random() * angleOffset - angleOffset / 2) + angle;
        this.speed = (int) (Math.random() * speedOffset + speedOffset);
        radius = (int) (Math.random() * radiusOffset + radiusOffset);
        this.damage = 30;
        colorDifference = (int) (Math.random() * 120);
        shouldMakeParticle = false;
        noCollision = true;
    }
    
    @Override
    public void run()
    {
        radius -= 2;
        super.run();
    }

    @Override
    public boolean isOutOfBounds()
    {
        if (radius <= 0)
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
        g2.setColor(new Color(205, 82 + colorDifference, 38));
        g2.fillOval(screenPoint.x, screenPoint.y, radius, radius);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius, radius);

        g2.setTransform(nonTransformed);
    }
}
package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class GenericProjectile
{
    protected GenericUnit firingUnit = null;
    protected Point screenPoint = new Point();
    protected Point projectilePoint = new Point();
    protected Rectangle projectileBounds = new Rectangle();
    protected boolean isPlayerProjectile = false;
    protected boolean noCollision = false;
    protected boolean shouldMakeParticle = true;
    protected int originalDamage = 0;
    protected int damage = 0;
    protected int angle = 0;
    protected int speed = 0;
    protected int radius = 3;

    public GenericProjectile(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        this.firingUnit = firingUnit;
        projectilePoint.setLocation(spawn);
        this.angle = angle;
        this.damage = damage;
        originalDamage = damage;
        this.speed = speed;
        this.isPlayerProjectile = isPlayerProjectile;
    }

    public void run()
    {
        projectilePoint.x += (int) calculateXMovement();
        projectilePoint.y += (int) calculateYMovement();
    }

    public boolean isOutOfBounds()
    {
        if ((screenPoint.x >= 0 && screenPoint.x <= Main.screenWidth) && (projectilePoint.y >= 0 && projectilePoint.y <= Main.screenHeight))
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean damageReduce()
    {
        damage /= 2;
        if (damage < originalDamage)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean hasHitGround()
    {
        if (projectilePoint.y < firingUnit.background.hillGround(projectilePoint.x + Main.screenWidth / 2))
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean hasHitTargetBody(GenericUnit target)
    {
        if (target.boundingBox.intersects(projectileBounds))
        {
            return true;
        }
        return false;
    }

    public boolean hasHitTargetHead(GenericUnit target)
    {
        if (target.boundingCircle.intersects(projectileBounds))
        {
            return true;
        }
        return false;
    }

    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        g2.rotate(Math.toRadians(angle), screenPoint.x, screenPoint.y);
        g2.setColor(Color.yellow);
        g2.fillOval(screenPoint.x, screenPoint.y, radius * 4, radius * 2);
        projectileBounds.setFrame(screenPoint.x, screenPoint.y, radius * 4, radius * 2);

        g2.setTransform(nonTransformed);
    }

    private double calculateXMovement()
    {
        double xMovement = (Math.cos(Math.toRadians(angle)) * (double) speed);
        return xMovement;
    }

    private double calculateYMovement()
    {
        double yMovement = (Math.sin(Math.toRadians(angle)) * (double) speed);
        return yMovement;
    }

    public boolean isIsPlayerProjectile()
    {
        return isPlayerProjectile;
    }

    public GenericUnit getUnit()
    {
        return firingUnit;
    }

    public Point getPoint()
    {
        return projectilePoint;
    }

    public int getDamage()
    {
        return damage;
    }

    public int getAngle()
    {
        return angle;
    }
}

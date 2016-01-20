package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Rifle extends GenericWeapon
{
    public Rifle(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 60;
        projectileSpeed = 40;
        attackSpeed = 7;
        accuracy = 165.0;
        originalAccuracy = accuracy;
        recoil = 4;
        cost = tier2Cost;
        requirement = 0;
    }

    @Override
    protected int getDirectionToTarget()
    {
        double differenceInX = targetPoint.x - screenPoint.x;
        double differenceInY = targetPoint.y - screenPoint.y;
        int angle = (int) Math.toDegrees(Math.atan2(differenceInY, differenceInX));
        if (!straightAngle)
        {
            return (int) (angle);
        } else
        {
            return 180;
        }
    }

    @Override
    protected void createProjectile()
    {
        projectileHolder.createProjectile(new GenericProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.makeSparks(holder, firingPoint, isPlayerWeapon, getDirectionToTarget());
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        super.paintSelf(g2, playerPoint, color);
        AffineTransform nonTransformed = g2.getTransform();
        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.setColor(new Color(87, 87, 87));
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 13, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 13, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 13, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4 - 13, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 - 13, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 - 13, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 - 13, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 - 13, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 - 13, 4, 4);
            //Body
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 13, 80, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 - 17, 60, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 15, 3, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 47, screenPoint.y + holder.unitHeight / 4 - 16, 3, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 - 17, 3, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 53, screenPoint.y + holder.unitHeight / 4 - 17, 3, 20);
            //Sights
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 - 18, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 56, screenPoint.y + holder.unitHeight / 4 - 19, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 57, screenPoint.y + holder.unitHeight / 4 - 20, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 - 20, 2, 5);
            //Barrel
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 115, screenPoint.y + holder.unitHeight / 4 - 13, 25, 5);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Stock
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 6, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 4, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 2, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 + 2, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 + 4, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 + 6, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 + 8, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 + 10, 4, 4);
            //Body 
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 + 7, 80, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 12, 10, 25);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 + 6, 60, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 6, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 47, screenPoint.y + holder.unitHeight / 4 - 5, 3, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 - 4, 3, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 53, screenPoint.y + holder.unitHeight / 4 - 4, 3, 20);
            //Sights
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 57, screenPoint.y + holder.unitHeight / 4 + 14, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 56, screenPoint.y + holder.unitHeight / 4 + 13, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 + 12, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 + 14, 2, 5);
            //Barrel
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 115, screenPoint.y + holder.unitHeight / 4 + 8, 25, 5);

        }
        if (holder.isFacingLeft)
        {
            firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 - 15) + 120 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 10) + 120 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        } else
        {
            firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 + 15) + 120 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 10) + 120 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        }
        g2.setTransform(nonTransformed);
    }
}

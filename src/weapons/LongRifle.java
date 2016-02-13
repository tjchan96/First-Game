package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.ProjectileHolder;
import projectiles.TwoPenProjectile;
import units.GenericUnit;

public class LongRifle extends GenericWeapon
{
    public LongRifle(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 80;
        projectileSpeed = 40;
        attackSpeed = 4;
        accuracy = 180.0;
        originalAccuracy = accuracy;
        recoil = 2;
        cost = tier3Cost;
        requirement = 1;
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
        projectileHolder.createProjectile(new TwoPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.makeSparks(holder, firingPoint, isPlayerWeapon, getDirectionToTarget());
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        super.paintSelf(g2, playerPoint, color);
        AffineTransform nonTransformed = g2.getTransform();
        g2.setColor(new Color(87, 87, 87));
        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 2, screenPoint.y + holder.unitHeight / 4 - 13, 6, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 7, screenPoint.y + holder.unitHeight / 4 - 13, 6, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 13, 6, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 13, 6, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 19, screenPoint.y + holder.unitHeight / 4 - 13, 6, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 - 13, 6, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 27, screenPoint.y + holder.unitHeight / 4 - 13, 6, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 28, screenPoint.y + holder.unitHeight / 4 - 13, 6, 8);
            //Muzzle
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 10, 20, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 145, screenPoint.y + holder.unitHeight / 4 - 20, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 144, screenPoint.y + holder.unitHeight / 4 - 19, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 143, screenPoint.y + holder.unitHeight / 4 - 18, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 142, screenPoint.y + holder.unitHeight / 4 - 17, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 141, screenPoint.y + holder.unitHeight / 4 - 16, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 140, screenPoint.y + holder.unitHeight / 4 - 15, 2, 2);
            //Body and Barrel
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 21, 5, 4);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 16, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 13, 30, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 33, screenPoint.y + holder.unitHeight / 4 - 18, 70, 14);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 43, screenPoint.y + holder.unitHeight / 4 - 9, 10, 20);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 2, screenPoint.y + holder.unitHeight / 4 - 7, 6, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 7, screenPoint.y + holder.unitHeight / 4 - 5, 6, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 3, 6, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 1, 6, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 19, screenPoint.y + holder.unitHeight / 4 + 1, 6, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 + 3, 6, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 27, screenPoint.y + holder.unitHeight / 4 + 5, 6, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 28, screenPoint.y + holder.unitHeight / 4 + 5, 6, 8);
            //Muzzle
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 7, 20, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 145, screenPoint.y + holder.unitHeight / 4 + 13, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 144, screenPoint.y + holder.unitHeight / 4 + 13, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 143, screenPoint.y + holder.unitHeight / 4 + 13, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 142, screenPoint.y + holder.unitHeight / 4 + 12, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 141, screenPoint.y + holder.unitHeight / 4 + 11, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 140, screenPoint.y + holder.unitHeight / 4 + 10, 2, 2);
            //Body and Barrel
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 17, 5, 4);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 11, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 + 8, 30, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 33, screenPoint.y + holder.unitHeight / 4 + 4, 70, 14);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 43, screenPoint.y + holder.unitHeight / 4 - 10, 10, 20);
        }
        if (holder.isFacingLeft())
        {
            firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2 - 35) + 100 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 2 - 40) + 100 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        } else
        {
            firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2 + 35) + 100 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 2 - 40) + 100 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        }
        g2.setTransform(nonTransformed);
    }
}

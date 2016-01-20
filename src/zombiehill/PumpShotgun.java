package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class PumpShotgun extends GenericWeapon
{
    public PumpShotgun(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 20;
        projectileSpeed = 40;
        accuracy = 45.0;
        originalAccuracy = accuracy;
        attackSpeed = 3;
        recoil = 1;
        cost = tier2Cost;
        requirement = 7;
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
        projectileHolder.createProjectile(new GenericPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.createProjectile(new GenericPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.createProjectile(new GenericPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.createProjectile(new GenericPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.createProjectile(new GenericPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.createProjectile(new GenericPenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.makeSparks(holder, firingPoint, isPlayerWeapon, getDirectionToTarget());
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        super.paintSelf(g2, playerPoint, color);
        g2.setColor(new Color(87, 87, 87));
        AffineTransform untransformed = g2.getTransform();
        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 10, 10, 20);
            //Barrel
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 15, 75, 6);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 10, 40, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 10, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 13, screenPoint.y + holder.unitHeight / 4 - 10, 4, 19);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 16, screenPoint.y + holder.unitHeight / 4 - 10, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 19, screenPoint.y + holder.unitHeight / 4 - 10, 4, 17);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 22, screenPoint.y + holder.unitHeight / 4 - 10, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 25, screenPoint.y + holder.unitHeight / 4 - 10, 4, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 28, screenPoint.y + holder.unitHeight / 4 - 10, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 31, screenPoint.y + holder.unitHeight / 4 - 10, 4, 13);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 34, screenPoint.y + holder.unitHeight / 4 - 10, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 37, screenPoint.y + holder.unitHeight / 4 - 10, 4, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 10, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 42, screenPoint.y + holder.unitHeight / 4 - 10, 4, 9);
            //Body
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 44, screenPoint.y + holder.unitHeight / 4 - 11, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 12, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 46, screenPoint.y + holder.unitHeight / 4 - 13, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 47, screenPoint.y + holder.unitHeight / 4 - 13, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 48, screenPoint.y + holder.unitHeight / 4 - 14, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 48, screenPoint.y + holder.unitHeight / 4 - 14, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49, screenPoint.y + holder.unitHeight / 4 - 14, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49, screenPoint.y + holder.unitHeight / 4 - 14, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 - 15, 12, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 95, screenPoint.y + holder.unitHeight / 4 - 7, 35, 6);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 128, screenPoint.y + holder.unitHeight / 4 - 8, 7, 7);
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 54, screenPoint.y + holder.unitHeight / 4 - 10, 41, 12);
            //Sights
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 25, 3, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 18, 13, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 130, screenPoint.y + holder.unitHeight / 4 - 19, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 129, screenPoint.y + holder.unitHeight / 4 - 18, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 128, screenPoint.y + holder.unitHeight / 4 - 17, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 127, screenPoint.y + holder.unitHeight / 4 - 16, 2, 2);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 9, 10, 25);
            //Barrel
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 + 11, 75, 6);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 + 6, 40, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 10, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 13, screenPoint.y + holder.unitHeight / 4 - 9, 4, 19);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 16, screenPoint.y + holder.unitHeight / 4 - 8, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 19, screenPoint.y + holder.unitHeight / 4 - 7, 4, 17);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 22, screenPoint.y + holder.unitHeight / 4 - 6, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 25, screenPoint.y + holder.unitHeight / 4 - 5, 4, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 28, screenPoint.y + holder.unitHeight / 4 - 4, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 31, screenPoint.y + holder.unitHeight / 4 - 3, 4, 13);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 34, screenPoint.y + holder.unitHeight / 4 - 2, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 37, screenPoint.y + holder.unitHeight / 4 - 1, 4, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 42, screenPoint.y + holder.unitHeight / 4 + 1, 4, 9);
            //Body
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 44, screenPoint.y + holder.unitHeight / 4 + 8, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 + 9, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 46, screenPoint.y + holder.unitHeight / 4 + 10, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 47, screenPoint.y + holder.unitHeight / 4 + 11, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 48, screenPoint.y + holder.unitHeight / 4 + 11, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 48, screenPoint.y + holder.unitHeight / 4 + 12, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49, screenPoint.y + holder.unitHeight / 4 + 12, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49, screenPoint.y + holder.unitHeight / 4 + 12, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 + 12, 12, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 95, screenPoint.y + holder.unitHeight / 4 + 3, 35, 6);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 128, screenPoint.y + holder.unitHeight / 4 + 3, 7, 7);
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 54, screenPoint.y + holder.unitHeight / 4 + 1, 41, 12);
            //Sights
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 + 20, 3, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 + 16, 13, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 130, screenPoint.y + holder.unitHeight / 4 + 16, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 129, screenPoint.y + holder.unitHeight / 4 + 16, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 128, screenPoint.y + holder.unitHeight / 4 + 16, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 127, screenPoint.y + holder.unitHeight / 4 + 16, 2, 2);
        }
        g2.setTransform(untransformed);
        firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2) + 120 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 + 10) + 30 * Math.sin(Math.toRadians(getDirectionToTarget()))) - 30);
    }
}

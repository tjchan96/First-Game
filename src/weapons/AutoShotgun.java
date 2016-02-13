package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.ProjectileHolder;
import projectiles.GenericPenProjectile;
import units.GenericUnit;

public class AutoShotgun extends GenericWeapon
{
    public AutoShotgun(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 16;
        projectileSpeed = 40;
        accuracy = 45.0;
        originalAccuracy = accuracy;
        attackSpeed = 10;
        recoil = 3;
        cost = tier3Cost;
        requirement = 11;
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
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 10, 10, 20);
            //Muzzle
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 104, screenPoint.y + holder.unitHeight / 4 - 8, 25, 5);
            //Sights
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 25, 2, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 39, screenPoint.y + holder.unitHeight / 4 - 23, 2, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 38, screenPoint.y + holder.unitHeight / 4 - 21, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 37, screenPoint.y + holder.unitHeight / 4 - 19, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 36, screenPoint.y + holder.unitHeight / 4 - 17, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 103, screenPoint.y + holder.unitHeight / 4 - 25, 2, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 102, screenPoint.y + holder.unitHeight / 4 - 23, 2, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 101, screenPoint.y + holder.unitHeight / 4 - 21, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 19, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 99, screenPoint.y + holder.unitHeight / 4 - 17, 2, 3);
            //Magazine
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 10, 7, 30);
            //Body
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 15, 65, 15);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 15, 40, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 15, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 13, screenPoint.y + holder.unitHeight / 4 - 15, 4, 19);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 16, screenPoint.y + holder.unitHeight / 4 - 15, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 19, screenPoint.y + holder.unitHeight / 4 - 15, 4, 17);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 22, screenPoint.y + holder.unitHeight / 4 - 15, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 25, screenPoint.y + holder.unitHeight / 4 - 15, 4, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 28, screenPoint.y + holder.unitHeight / 4 - 15, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 31, screenPoint.y + holder.unitHeight / 4 - 15, 4, 13);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 34, screenPoint.y + holder.unitHeight / 4 - 15, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 37, screenPoint.y + holder.unitHeight / 4 - 15, 4, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 15, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 42, screenPoint.y + holder.unitHeight / 4 - 15, 4, 9);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 10, 10, 25);
            //Muzzle
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 104, screenPoint.y + holder.unitHeight / 4 + 6, 25, 5);
            //Sights
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 15, 2, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 39, screenPoint.y + holder.unitHeight / 4 + 15, 2, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 38, screenPoint.y + holder.unitHeight / 4 + 15, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 37, screenPoint.y + holder.unitHeight / 4 + 15, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 36, screenPoint.y + holder.unitHeight / 4 + 15, 2, 3);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 103, screenPoint.y + holder.unitHeight / 4 + 15, 2, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 102, screenPoint.y + holder.unitHeight / 4 + 15, 2, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 101, screenPoint.y + holder.unitHeight / 4 + 15, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 15, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 99, screenPoint.y + holder.unitHeight / 4 + 15, 2, 3);
            //Magazine
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 20, 7, 30);
            //Body
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 2, 65, 15);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 + 11, 40, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 5, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 13, screenPoint.y + holder.unitHeight / 4 - 4, 4, 19);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 16, screenPoint.y + holder.unitHeight / 4 - 3, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 19, screenPoint.y + holder.unitHeight / 4 - 2, 4, 17);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 22, screenPoint.y + holder.unitHeight / 4 - 1, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 25, screenPoint.y + holder.unitHeight / 4, 4, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 28, screenPoint.y + holder.unitHeight / 4 + 1, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 31, screenPoint.y + holder.unitHeight / 4 + 2, 4, 13);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 34, screenPoint.y + holder.unitHeight / 4 + 3, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 37, screenPoint.y + holder.unitHeight / 4 + 4, 4, 11);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 5, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 42, screenPoint.y + holder.unitHeight / 4 + 6, 4, 9);
        }
        g2.setTransform(untransformed);
        firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2) + 120 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4 + 10) + 30 * Math.sin(Math.toRadians(getDirectionToTarget()))) - 30);
    }
}

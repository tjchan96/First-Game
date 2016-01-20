package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class AssaultRifle extends GenericWeapon
{
    public AssaultRifle(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 20;
        projectileSpeed = 40;
        accuracy = 70.0;
        originalAccuracy = accuracy;
        attackSpeed = 25;
        recoil = 5;
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
    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        super.paintSelf(g2, playerPoint, color);
        AffineTransform nonTransformed = g2.getTransform();
        g2.setColor(new Color(87, 87, 87));
        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
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
            //Muzzle
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 120, screenPoint.y + holder.unitHeight / 4 - 8, 30, 5);
            //Barrel and Grip
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 13, 110, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 35);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 0, 30, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 5, 55, 10);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 0, 10, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 61, screenPoint.y + holder.unitHeight / 4 + 10, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 62, screenPoint.y + holder.unitHeight / 4 + 11, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 63, screenPoint.y + holder.unitHeight / 4 + 12, 3, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 64, screenPoint.y + holder.unitHeight / 4 + 13, 3, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 + 14, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 66, screenPoint.y + holder.unitHeight / 4 + 15, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 67, screenPoint.y + holder.unitHeight / 4 + 16, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 68, screenPoint.y + holder.unitHeight / 4 + 16, 3, 9);
            //Handle
            g2.setColor(new Color(107, 107, 107));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 - 17, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 - 17, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 - 21, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 135, screenPoint.y + holder.unitHeight / 4 - 15, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 134, screenPoint.y + holder.unitHeight / 4 - 14, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 133, screenPoint.y + holder.unitHeight / 4 - 13, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 132, screenPoint.y + holder.unitHeight / 4 - 12, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 131, screenPoint.y + holder.unitHeight / 4 - 11, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 130, screenPoint.y + holder.unitHeight / 4 - 10, 2, 2);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 7, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 5, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 3, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4 - 1, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 + 1, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 + 3, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 + 5, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 + 7, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 + 9, 4, 4);
            //Muzzle
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 120, screenPoint.y + holder.unitHeight / 4 + 2, 30, 5);
            //Body

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 + 3, 110, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 23, 10, 35);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 10, 30, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 5, 55, 10);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 10, 10, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 10, 10, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 61, screenPoint.y + holder.unitHeight / 4 - 12, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 62, screenPoint.y + holder.unitHeight / 4 - 14, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 63, screenPoint.y + holder.unitHeight / 4 - 16, 3, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 64, screenPoint.y + holder.unitHeight / 4 - 17, 3, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 18, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 66, screenPoint.y + holder.unitHeight / 4 - 19, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 67, screenPoint.y + holder.unitHeight / 4 - 20, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 68, screenPoint.y + holder.unitHeight / 4 - 20, 3, 9);
            //Barrel and Grip
            g2.setColor(Color.gray);
            //Handle
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 + 11, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 + 11, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 + 15, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 135, screenPoint.y + holder.unitHeight / 4 + 8, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 134, screenPoint.y + holder.unitHeight / 4 + 8, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 133, screenPoint.y + holder.unitHeight / 4 + 8, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 132, screenPoint.y + holder.unitHeight / 4 + 7, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 131, screenPoint.y + holder.unitHeight / 4 + 6, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 130, screenPoint.y + holder.unitHeight / 4 + 5, 2, 2);
        }
        if (holder.isFacingLeft)
        {
            firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 - 35) + 100 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 10) + 80 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));
        } else
        {
            firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 + 35) + 100 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 10) + 80 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));
        }
        g2.setTransform(nonTransformed);
    }
}
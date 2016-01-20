package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Lmg extends GenericWeapon
{
    public Lmg(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 30;
        projectileSpeed = 40;
        attackSpeed = 30;
        accuracy = 50.0;
        originalAccuracy = accuracy;
        recoil = 6;
        cost = tier3Cost;
        requirement = 8;
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
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 13, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 13, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 13, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4 - 13, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 - 13, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 - 13, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 - 13, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 - 13, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 - 13, 4, 4);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 - 15, 80, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 15, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 - 25, 10, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 135, screenPoint.y + holder.unitHeight / 4 - 15, 4, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 13, 90, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);

            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 15, 30, 30);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 90, screenPoint.y + holder.unitHeight / 4 - 25, 3, 10);


        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 7, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 5, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 3, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4 - 1, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 + 1, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 + 3, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 + 5, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 + 7, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 + 9, 4, 4);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 + 2, 80, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 10, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 135, screenPoint.y + holder.unitHeight / 4 + 5, 4, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 + 10, 10, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 + 6, 90, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);

            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 20, 30, 30);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 90, screenPoint.y + holder.unitHeight / 4 + 10, 3, 10);

        }
        firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2) + 110 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4) + 50 * Math.sin(Math.toRadians(getDirectionToTarget()))) - 35);
        g2.setTransform(nonTransformed);
    }
}
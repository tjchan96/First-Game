package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.ProjectileHolder;
import units.GenericUnit;

public class Magnum extends GenericWeapon
{
    public Magnum(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 35;
        projectileSpeed = 40;
        attackSpeed = 5;
        accuracy = 150.0;
        originalAccuracy = accuracy;
        recoil = 1;
        cost = tier1Cost;
        requirement = -1;
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
            //Body
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 23, 18, 15);
            //Barrel
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 78, screenPoint.y + holder.unitHeight / 4 - 23, 20, 7);
            //Sights
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 63, screenPoint.y + holder.unitHeight / 4 - 26, 3, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 94, screenPoint.y + holder.unitHeight / 4 - 27, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 93, screenPoint.y + holder.unitHeight / 4 - 25, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 92, screenPoint.y + holder.unitHeight / 4 - 24, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 91, screenPoint.y + holder.unitHeight / 4 - 23, 2, 3);
            //Grip
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 10, 10, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 46, screenPoint.y + holder.unitHeight / 4 - 12, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 47, screenPoint.y + holder.unitHeight / 4 - 14, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49, screenPoint.y + holder.unitHeight / 4 - 16, 3, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 51, screenPoint.y + holder.unitHeight / 4 - 17, 3, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 53, screenPoint.y + holder.unitHeight / 4 - 18, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 - 19, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 57, screenPoint.y + holder.unitHeight / 4 - 20, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 58, screenPoint.y + holder.unitHeight / 4 - 20, 3, 9);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            //Body
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 + 8, 18, 15);
            //Barrel
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 78, screenPoint.y + holder.unitHeight / 4 + 16, 20, 7);
            //Sights
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 63, screenPoint.y + holder.unitHeight / 4 + 22, 3, 4);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 94, screenPoint.y + holder.unitHeight / 4 + 21, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 93, screenPoint.y + holder.unitHeight / 4 + 21, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 92, screenPoint.y + holder.unitHeight / 4 + 20, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 91, screenPoint.y + holder.unitHeight / 4 + 19, 2, 3);
            //Grip
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 5, 10, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 46, screenPoint.y + holder.unitHeight / 4 + 5, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 47, screenPoint.y + holder.unitHeight / 4 + 6, 3, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49, screenPoint.y + holder.unitHeight / 4 + 7, 3, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 51, screenPoint.y + holder.unitHeight / 4 + 8, 3, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 53, screenPoint.y + holder.unitHeight / 4 + 9, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 + 10, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 57, screenPoint.y + holder.unitHeight / 4 + 11, 3, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 58, screenPoint.y + holder.unitHeight / 4 + 11, 3, 9);
        }
        firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2) + 70 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4) + 50 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));
        g2.setTransform(nonTransformed);
    }
}
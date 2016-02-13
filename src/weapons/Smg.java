package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.ProjectileHolder;
import units.GenericUnit;

public class Smg extends GenericWeapon
{
    public Smg(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 15;
        projectileSpeed = 40;
        attackSpeed = 50;
        accuracy = 50.0;
        recoil = 10;
        originalAccuracy = accuracy;
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
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 15, 10, 35);
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 - 10, 40, 10);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 - 15, 60, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 85, screenPoint.y + holder.unitHeight / 4 - 25, 3, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 75, screenPoint.y + holder.unitHeight / 4 - 25, 3, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 75, screenPoint.y + holder.unitHeight / 4 - 23, 13, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 95, screenPoint.y + holder.unitHeight / 4 - 10, 6, 6);

        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y - holder.unitHeight / 4 + 5, 10, 35);

            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 + 0, 40, 10);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 + 10, 60, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 85, screenPoint.y + holder.unitHeight / 4 + 15, 3, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 75, screenPoint.y + holder.unitHeight / 4 + 15, 3, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 75, screenPoint.y + holder.unitHeight / 4 + 18, 13, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 95, screenPoint.y + holder.unitHeight / 4 + 5, 6, 6);

        }
        firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2) + 80 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4) + 80 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        g2.setTransform(nonTransformed);
    }
}
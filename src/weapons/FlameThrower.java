package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.ProjectileHolder;
import projectiles.Flame;
import units.GenericUnit;

public class FlameThrower extends GenericWeapon
{
    public FlameThrower(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 15;
        projectileSpeed = 20;
        attackSpeed = 70;
        accuracy = 50.0;
        originalAccuracy = accuracy;
        recoil = 0;
        cost = tier4Cost;
        requirement = 9;
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
        projectileHolder.createProjectile(new Flame(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget(), isPlayerWeapon));
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

            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 10, 20, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 145, screenPoint.y + holder.unitHeight / 4 - 20, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 144, screenPoint.y + holder.unitHeight / 4 - 19, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 143, screenPoint.y + holder.unitHeight / 4 - 18, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 142, screenPoint.y + holder.unitHeight / 4 - 17, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 141, screenPoint.y + holder.unitHeight / 4 - 16, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 140, screenPoint.y + holder.unitHeight / 4 - 15, 2, 2);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 56, screenPoint.y + holder.unitHeight / 4 - 10, 20, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 61, screenPoint.y + holder.unitHeight / 4 - 10, 57, 20);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 108, screenPoint.y + holder.unitHeight / 4 - 10, 20, 20);

            //Body and Barrel
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 21, 5, 4);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 13, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 13, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 13, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4 - 13, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 - 13, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 - 13, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 - 13, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 - 13, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 - 13, 4, 4);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 16, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 13, 30, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 33, screenPoint.y + holder.unitHeight / 4 - 18, 70, 14);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 43, screenPoint.y + holder.unitHeight / 4 - 9, 10, 20);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 150, screenPoint.y + holder.unitHeight / 4 - 24, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 155, screenPoint.y + holder.unitHeight / 4 - 24, 10, 20);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 128, screenPoint.y + holder.unitHeight / 4 + 1, screenPoint.x + holder.unitWidth / 2 + 160, screenPoint.y + holder.unitHeight / 4 + 1);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 160, screenPoint.y + holder.unitHeight / 4 + 1, screenPoint.x + holder.unitWidth / 2 + 180, screenPoint.y + holder.unitHeight / 4 - 14);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 - 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 120, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 120, screenPoint.y + holder.unitHeight / 4 - 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 90, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 90, screenPoint.y + holder.unitHeight / 4 - 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 - 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 + 5, screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 5);

            g2.setColor(Color.red);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 180, screenPoint.y + holder.unitHeight / 4 - 15, 2, 2);

        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);

            g2.setColor(Color.gray);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 56, screenPoint.y + holder.unitHeight / 4 - 10, 20, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 61, screenPoint.y + holder.unitHeight / 4 - 10, 57, 20);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 108, screenPoint.y + holder.unitHeight / 4 - 10, 20, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 145, screenPoint.y + holder.unitHeight / 4 + 13, 2, 7);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 144, screenPoint.y + holder.unitHeight / 4 + 13, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 143, screenPoint.y + holder.unitHeight / 4 + 13, 2, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 142, screenPoint.y + holder.unitHeight / 4 + 12, 2, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 141, screenPoint.y + holder.unitHeight / 4 + 11, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 140, screenPoint.y + holder.unitHeight / 4 + 10, 2, 2);
            //Body and Barrel
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 17, 5, 4);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 7, 4, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 8, screenPoint.y + holder.unitHeight / 4 - 5, 4, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 11, screenPoint.y + holder.unitHeight / 4 - 3, 4, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 14, screenPoint.y + holder.unitHeight / 4 - 1, 4, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 17, screenPoint.y + holder.unitHeight / 4 + 1, 4, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 + 3, 4, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 + 5, 4, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 26, screenPoint.y + holder.unitHeight / 4 + 7, 4, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 29, screenPoint.y + holder.unitHeight / 4 + 9, 4, 4);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 33, screenPoint.y + holder.unitHeight / 4 + 4, 90, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 11, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 + 8, 30, 5);

            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 43, screenPoint.y + holder.unitHeight / 4 - 10, 10, 20);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 150, screenPoint.y + holder.unitHeight / 4 + 5, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 155, screenPoint.y + holder.unitHeight / 4 + 5, 10, 20);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 128, screenPoint.y + holder.unitHeight / 4 - 0, screenPoint.x + holder.unitWidth / 2 + 160, screenPoint.y + holder.unitHeight / 4 + 0);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 160, screenPoint.y + holder.unitHeight / 4 + 0, screenPoint.x + holder.unitWidth / 2 + 180, screenPoint.y + holder.unitHeight / 4 + 10);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 110, screenPoint.y + holder.unitHeight / 4 + 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 120, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 120, screenPoint.y + holder.unitHeight / 4 + 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 90, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 90, screenPoint.y + holder.unitHeight / 4 + 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 + 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 + 5);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 - 5, screenPoint.x + holder.unitWidth / 2 + 60, screenPoint.y + holder.unitHeight / 4 + 5);

            g2.setColor(Color.red);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 180, screenPoint.y + holder.unitHeight / 4 + 10, 2, 2);
        }
        if (holder.isFacingLeft())
        {
            firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2 - 25) + 130 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4 - 40) + 120 * Math.sin(Math.toRadians(getDirectionToTarget())) + 20));
        } else
        {
            firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2 - 25) + 150 * Math.cos(Math.toRadians(getDirectionToTarget())) + 10), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4 - 40) + 120 * Math.sin(Math.toRadians(getDirectionToTarget())) + 10));
        }

        g2.setTransform(nonTransformed);
    }
}

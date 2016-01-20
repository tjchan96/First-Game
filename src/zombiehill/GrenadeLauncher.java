package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class GrenadeLauncher extends GenericWeapon
{
    public GrenadeLauncher(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 100;
        projectileSpeed = 20;
        attackSpeed = 3;
        accuracy = 90.0;
        originalAccuracy = accuracy;
        recoil = 2;
        cost = tier4Cost;
        requirement = 12;
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
        projectileHolder.createProjectile(new GrenadeProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget(), isPlayerWeapon));
        projectileHolder.makeSmoke(holder, firingPoint, isPlayerWeapon, 10);
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

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 3, 35, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 13, 35, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 - 25, 10, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 75, screenPoint.y + holder.unitHeight / 4 - 25, 20, 3);


            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 - 10, 80, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 13, 90, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);


            g2.setColor(Color.gray);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 18, 40, 20);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 10, 40, 20);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 + 0, 40, 20);

            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 15, 40, 25);
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

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 3, 35, 15);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 2, 35, 5);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 55, screenPoint.y + holder.unitHeight / 4 + 2, 80, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 + 6, 90, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 + 15, 10, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 75, screenPoint.y + holder.unitHeight / 4 + 25, 20, 3);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 10, 3, 3);
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 10, 40, 20);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 18, 40, 20);

            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 + 0, 40, 20);

            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 15, 40, 25);
        }
        firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2) + 130 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 40) + 120 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));
        g2.setTransform(nonTransformed);
    }
}

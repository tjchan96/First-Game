package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class SniperRifle extends GenericWeapon
{
    public SniperRifle(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 80;
        projectileSpeed = 40;
        attackSpeed = 3;
        accuracy = 200.0;
        originalAccuracy = accuracy;
        recoil = 1;
        cost = tier4Cost;
        requirement = 2;
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
        projectileHolder.createProjectile(new InfinitePenProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
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
            g2.setColor(Color.gray);
            //Scope
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 - 30, 10, 20);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 30, screenPoint.y + holder.unitHeight / 4 - 30, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 - 33, 20, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 - 33, 20, 9);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 57, screenPoint.y + holder.unitHeight / 4 - 33, 20, 9);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 33, screenPoint.y + holder.unitHeight / 4 - 33, 20, 9);
            //Body and Barrel
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 - 15, 50, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 12, 50, 5);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 - 15, 40, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 15, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 12, screenPoint.y + holder.unitHeight / 4 - 15, 5, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 15, 5, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 18, screenPoint.y + holder.unitHeight / 4 - 15, 5, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 21, screenPoint.y + holder.unitHeight / 4 - 15, 5, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 - 15, 5, 10);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 15, 6, 20);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.setColor(Color.gray);
            //Scope
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 + 10, 10, 20);
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 30, screenPoint.y + holder.unitHeight / 4 + 28, 50, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 20, screenPoint.y + holder.unitHeight / 4 + 26, 20, 9);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 65, screenPoint.y + holder.unitHeight / 4 + 26, 20, 9);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 57, screenPoint.y + holder.unitHeight / 4 + 26, 20, 9);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 33, screenPoint.y + holder.unitHeight / 4 + 26, 20, 9);
            //Barrel and Body
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 50, screenPoint.y + holder.unitHeight / 4 + 4, 50, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 + 8, 50, 5);
            //Stock
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 10, screenPoint.y + holder.unitHeight / 4 + 8, 40, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 5, screenPoint.y + holder.unitHeight / 4 - 4, 10, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 12, screenPoint.y + holder.unitHeight / 4 - 3, 5, 18);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 1, 5, 16);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 18, screenPoint.y + holder.unitHeight / 4 + 1, 5, 14);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 21, screenPoint.y + holder.unitHeight / 4 + 3, 5, 12);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 23, screenPoint.y + holder.unitHeight / 4 + 5, 5, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 25, screenPoint.y + holder.unitHeight / 4 + 7, 5, 8);
            //Grip
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 5, 6, 20);

        }
        if (holder.isFacingLeft)
        {
            firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 - 25) + 110 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 40) + 120 * Math.sin(Math.toRadians(getDirectionToTarget())) + 20));
        } else
        {
            firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 - 25) + 150 * Math.cos(Math.toRadians(getDirectionToTarget())) + 10), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 40) + 120 * Math.sin(Math.toRadians(getDirectionToTarget())) + 10));
        }
        g2.setTransform(nonTransformed);
    }
}
package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Knife extends GenericWeapon
{
    private int stabRecoil = 4;

    public Knife(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 50;
        attackSpeed = 15;
        projectileSpeed = 40;
        accuracy = 100.0;
        originalAccuracy = accuracy;
        recoil = 0;
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
        projectileHolder.createProjectile(new KnifeProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget(), isPlayerWeapon));
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        super.paintSelf(g2, playerPoint, color);

        AffineTransform nonTransformed = g2.getTransform();

        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.setColor(new Color(105, 105, 105));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 2, 10, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 9, 2, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 48 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 5, 18, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 63 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 5, 5, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 68 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 3, 5, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 73 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 1, 5, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 78 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 + 1, 5, 2);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);

            //Grip
            g2.setColor(new Color(105, 105, 105));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 2, 10, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 49 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 9, 2, 20);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 48 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 5, 18, 10);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 63 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 5, 5, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 68 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 3, 5, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 73 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 - 1, 5, 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 78 + attackCounter * stabRecoil - (int) (200.0 / attackSpeed), screenPoint.y + holder.unitHeight / 4 + 1, 5, 2);
        }
        firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2) + 1 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 2) + 1 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));

        g2.setTransform(nonTransformed);
    }
}
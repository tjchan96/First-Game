package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class NukeStrike extends GenericWeapon
{
    public NukeStrike(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 100;
        projectileSpeed = 5;
        attackSpeed = 0.2;
        recoil = 0;
        cost = tier4Cost;
        requirement = 5;
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
        projectileHolder.createProjectile(new NukeProjectile(holder, new Point(holderPoint.x, 0), damage, projectileSpeed, 90, isPlayerWeapon));
        ((Player) holder).removeNuke();
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

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 15, 20, 30);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 15, screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 35);

            g2.setColor(Color.red);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 5, 10, 10);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);

            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 10, 20, 30);
            g2.drawLine(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 10, screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 40);

            g2.setColor(Color.red);
            g2.fillOval(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 + 0, 10, 10);
        }
        firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2 - 25) + 150 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 4 - 40) + 120 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        g2.setTransform(nonTransformed);
    }
}

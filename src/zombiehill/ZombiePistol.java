package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class ZombiePistol extends GenericWeapon
{
    public ZombiePistol(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 5;
        projectileSpeed = 40;
        attackSpeed = 10;
        accuracy = 60.0;
        recoil = 3;
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        super.paintSelf(g2, playerPoint, color);

        AffineTransform nonTransformed = g2.getTransform();

        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            //Body
            g2.setColor(new Color(105, 105, 105));
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 10, 40, 8);
            //Sights and Barrel
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 - 16, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 43, screenPoint.y + holder.unitHeight / 4 - 18, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 14, 40, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 - 9, 2, 5);
            //Grip
            g2.setColor(new Color(105, 105, 105));
            g2.rotate(Math.toRadians(10), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4 - 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 15, 12, 15);
        } else
        {
            //Body
            g2.setColor(new Color(105, 105, 105));
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 4, 40, 7);
            //Sights and Barrel
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 70, screenPoint.y + holder.unitHeight / 4 + 16, 2, 3);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 43, screenPoint.y + holder.unitHeight / 4 + 15, 2, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 8, 40, 8);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 + 5, 2, 5);
            //Grip
            g2.setColor(new Color(105, 105, 105));
            g2.rotate(Math.toRadians(-10), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 + 1, 12, 15);
        }
        firingPoint = new Point((int) ((holder.unitPoint.x + holder.unitWidth / 2) + 70 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.unitPoint.y + holder.unitHeight / 2) + 70 * Math.sin(Math.toRadians(getDirectionToTarget()))));

        g2.setTransform(nonTransformed);
    }
}
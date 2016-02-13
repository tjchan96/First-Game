package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.ProjectileHolder;
import projectiles.GenericProjectile;
import units.GenericUnit;

public class Carbine extends GenericWeapon
{
    private int rounds = 0;
    private int roundCounter = 0;

    public Carbine(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 15;
        projectileSpeed = 40;
        attackSpeed = 10;
        accuracy = 140.0;
        originalAccuracy = accuracy;
        recoil = 3;
        cost = tier2Cost;
        requirement = 0;
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
        projectileHolder.createProjectile(new GenericProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.makeSparks(holder, firingPoint, isPlayerWeapon, getDirectionToTarget());
    }

    @Override
    protected void shouldAttack()
    {
        if (roundCounter > 0)
        {
            roundCounter--;
        } else if (rounds > 0)
        {
            rounds--;
            createProjectile();
            roundCounter = 3;
        } else if (attackCounter > 0)
        {
            attackCounter--;
        } else if (shouldAttack)
        {
            rounds = 2;
            createProjectile();
            attackCounter = (int) (100.0 / attackSpeed);
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
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil - roundCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
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
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 100, screenPoint.y + holder.unitHeight / 4 - 8, 15, 5);
            //Body
            g2.setColor(new Color(107, 107, 107));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 8, 60, 10);
            //Barrel and Grip
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 - 13, 90, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);
            //Handle
            g2.setColor(new Color(107, 107, 107));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 - 17, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 - 17, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 - 21, 50, 5);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil + roundCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
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
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 104, screenPoint.y + holder.unitHeight / 4 + 2, 10, 5);
            //Body
            g2.setColor(Color.gray);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 45, screenPoint.y + holder.unitHeight / 4 - 3, 60, 10);
            //Barrel and Grip
            g2.setColor(new Color(87, 87, 87));
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 15, screenPoint.y + holder.unitHeight / 4 + 6, 90, 6);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 40, screenPoint.y + holder.unitHeight / 4 - 13, 10, 25);
            //Handle
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 80, screenPoint.y + holder.unitHeight / 4 + 11, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 + 11, 5, 5);
            g2.fillRect(screenPoint.x + holder.unitWidth / 2 + 35, screenPoint.y + holder.unitHeight / 4 + 15, 50, 5);
        }
        if (holder.isFacingLeft())
        {
            firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2 - 35) + 70 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4 - 10) + 80 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));
        } else
        {
            firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2 + 35) + 70 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4 - 10) + 80 * Math.sin(Math.toRadians(getDirectionToTarget())) - 20));
        }
        g2.setTransform(nonTransformed);
    }
}
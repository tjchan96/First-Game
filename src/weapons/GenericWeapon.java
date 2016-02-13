package weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import main.Main;
import main.ProjectileHolder;
import projectiles.GenericProjectile;
import units.GenericUnit;
import units.Player;

public class GenericWeapon
{
    protected ProjectileHolder projectileHolder = null;
    protected GenericUnit holder = null;
    protected Point holderPoint = new Point();
    protected Point screenPoint = new Point();
    protected Point firingPoint = new Point();
    protected Point targetPoint = new Point();
    protected boolean holderPointSet = false;
    protected boolean isPlayerWeapon = false;
    protected boolean shouldAttack = false;
    protected boolean straightAngle = false;
    protected double attackSpeed = 2.0;
    protected double originalAccuracy = 20.0;
    protected double accuracy = 20.0;
    protected int cost = 0;
    protected int smokeCount = 0;
    protected int sparkCount = 0;
    protected int damage = 2;
    protected int projectileSpeed = 5;
    protected int attackCounter = 0;
    protected int recoil = 4;
    protected int requirement = -1;
    protected int tier1Cost = 100;
    protected int tier2Cost = 400;
    protected int tier3Cost = 800;
    protected int tier4Cost = 1500;

    public GenericWeapon(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        this.isPlayerWeapon = isPlayerWeapon;
        this.holder = holder;
        this.projectileHolder = projectileHolder;
    }

    public void run()
    {
        if (isPlayerWeapon)
        {
            if (((Player) holder).getKeyDownPressed())
            {
                if (accuracy + 10 > 200)
                {
                    accuracy = 200;
                } else
                {
                    accuracy += 10;
                }
            } else
            {
                accuracy = originalAccuracy;
            }
        }
        if (!holderPointSet)
        {
            holderPoint = holder.getUnitPoint();
        }
        targetPoint = holder.getTargetPoint();
        shouldAttack();
    }

    public void paintSelf(Graphics2D g2, Point playerPoint, Color color)
    {
        screenPoint = new Point(holderPoint.x - playerPoint.x + Main.screenWidth / 2, holderPoint.y);

        AffineTransform nonTransformed = g2.getTransform();

        g2.setColor(color);

        if (getDirectionToTarget() > -90 && getDirectionToTarget() < 90)
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() - attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            holder.setIsFacingLeft(false);
        } else
        {
            g2.rotate(Math.toRadians(getDirectionToTarget() + attackCounter * recoil), screenPoint.x + holder.unitWidth / 2, screenPoint.y + holder.unitHeight / 4);
            holder.setIsFacingLeft(true);
        }

        g2.translate(screenPoint.x + holder.unitWidth / 2 - 5, screenPoint.y + holder.unitHeight / 4 - 5);

        g2.translate(holder.unitWidth / 2, holder.unitHeight / 2);
        g2.fillRect(-holder.unitWidth / 2, -holder.unitHeight / 2, 50, 10);
        firingPoint = new Point((int) ((holder.getUnitPoint().x + holder.unitWidth / 2) + 1 * Math.cos(Math.toRadians(getDirectionToTarget()))), (int) ((holder.getUnitPoint().y + holder.unitHeight / 4) + 1 * Math.sin(Math.toRadians(getDirectionToTarget()))));
        g2.setTransform(nonTransformed);
    }

    protected void shouldAttack()
    {
        if (attackCounter > 0)
        {
            attackCounter--;
        } else if (shouldAttack)
        {
            createProjectile();
            attackCounter = (int) (100.0 / attackSpeed);
        }
    }

    public void setShouldAttack(boolean shouldAttack)
    {
        this.shouldAttack = shouldAttack;
    }

    protected void createProjectile()
    {
        projectileHolder.createProjectile(new GenericProjectile(holder, firingPoint, damage, projectileSpeed, getDirectionToTarget() + (int) ((2000.0 / accuracy) * Math.random() - (2000.0 / accuracy) / 2), isPlayerWeapon));
        projectileHolder.makeSparks(holder, firingPoint, isPlayerWeapon, getDirectionToTarget());
    }

    protected int getDirectionToTarget()
    {
        double differenceInX = targetPoint.x - holderPoint.x;
        double differenceInY = targetPoint.y - holderPoint.y;
        double angle = Math.toDegrees(Math.atan2(differenceInY, differenceInX));
        if (!straightAngle)
        {
            return (int) (angle);
        } else
        {
            return 180;
        }
    }

    public int getCost()
    {
        return cost;
    }

	public int getRequirement()
	{
		return requirement;
	}

    public void setHolderPoint(Point holderPoint)
    {
        holderPointSet = true;
        this.holderPoint = holderPoint;
    }

    public void setStraightAngle(boolean straightAngle)
    {
        this.straightAngle = straightAngle;
    }
}

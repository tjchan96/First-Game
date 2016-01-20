package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class SkeletalMinotaur extends GenericZombie
{
    public SkeletalMinotaur(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 4;
        range = 70;
        health = 240;
        weapon = new BasicSkeletonWeapon(this, target, projectileHolder, false);
        color1 = new Color(228, 228, 228);
        color2 = new Color(228, 228, 228);
        maxHealth = health;
    }

    @Override
    public void run()
    {
        if (distanceToTarget < 350)
        {
            speed = 14;
        } else
        {
            speed = 4;
        }
        super.run();
    }

    @Override
    public void moveAway(Rectangle rect)
    {
        if (!isDying)
        {
            shouldMoveUp = true;
            super.moveAway(rect);
        }
    }

    @Override
    public void damageSelfFace(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeLargeSmoke(this, hitByBulletPoint, isPlayer, 20);
        health -= damage * 3;
        shotHead = true;
        if (health <= 0)
        {
            die();
        }
    }

    @Override
    public void damageSelf(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeSmoke(this, hitByBulletPoint, isPlayer, 5);
        health -= damage;
        if (health <= 0)
        {
            die();
        }
    }

    @Override
    protected void die()
    {
        projectileHolder.makeLargeSmoke(this, unitPoint, isPlayer, 50);
        isDead = true;
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(unitPoint.x - playerPoint.x + Main.screenWidth / 2, unitPoint.y);
        paintSelfFeet(g2, playerPoint, color2);
        paintSelfHead(g2, color2);
        g2.setColor(color1);
        if (isFacingLeft)
        {
            boundingBox.setBounds(screenPoint.x + unitWidth / 8 + 5, screenPoint.y, unitWidth * 3 / 4, unitHeight + 25);
        } else
        {
            boundingBox.setBounds(screenPoint.x + unitWidth / 8 - 5, screenPoint.y, unitWidth * 3 / 4, unitHeight + 25);
        }
        weapon.paintSelf(g2, playerPoint, color2);
        paintSelfHealthBar(g2);
    }

    @Override
    protected void paintSelfHead(Graphics2D g2, Color color)
    {
        g2.setColor(color);
        if (!(shotHead) || (isPlayer))
        {
            if (isFacingLeft)
            {
                g2.fillRect(screenPoint.x + -unitWidth * 3 / 8, screenPoint.y - unitHeight * 5 / 8, unitWidth * 5 / 4, unitHeight * 3 / 4);
                boundingCircle.setFrame(screenPoint.x + -unitWidth * 3 / 8, screenPoint.y - unitHeight * 5 / 8, unitWidth * 5 / 4, unitHeight * 3 / 4);
            } else
            {
                g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y - unitHeight * 5 / 8, unitWidth * 5 / 4, unitHeight * 3 / 4);
                boundingCircle.setFrame(screenPoint.x + unitWidth / 8, screenPoint.y - unitHeight * 5 / 8, unitWidth * 5 / 4, unitHeight * 3 / 4);
            }
        } else
        {
            boundingCircle.setFrame(0, 0, 0, 0);
        }
    }
}
package units;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import main.Background;
import main.Main;
import main.ProjectileHolder;
import weapons.BasicSkeletonWeapon;

public class BasicSkeleton extends GenericZombie
{
    public BasicSkeleton(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 2;
        range = 350;
        health = 60;
        weapon = new BasicSkeletonWeapon(this, target, projectileHolder, false);
        color1 = new Color(228, 228, 228);
        color2 = new Color(228, 228, 228);
        maxHealth = health;
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
}
package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class GenericGrave extends GenericUnit
{
    protected int zombieTimer = 0;
    protected double zombieTimerRate = 1;
    protected int zombieSpawnType = 1;

    public GenericGrave(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 0;
        range = 0;
        health = 100;
        weapon = new GenericWeapon(this, target, projectileHolder, isPlayer);
        color1 = new Color(105, 105, 105);
        color2 = new Color(131, 131, 131);
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(unitPoint.x - playerPoint.x + Main.screenWidth / 2, unitPoint.y);

        g2.setColor(color1);
        g2.fillOval(screenPoint.x, screenPoint.y - unitHeight / 2, unitWidth * 5 / 4, unitWidth * 5 / 4);
        g2.fillRect(screenPoint.x, screenPoint.y, unitWidth * 5 / 4, unitHeight * 7 / 4);
        boundingBox.setBounds(screenPoint.x, screenPoint.y, unitWidth * 5 / 4, unitHeight * 7 / 4);
        boundingCircle.setFrame(screenPoint.x, screenPoint.y - unitHeight / 2, unitWidth * 5 / 4, unitWidth * 5 / 4);

        g2.setColor(color2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 10, unitWidth, 2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 20, unitWidth, 2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 30, unitWidth, 2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 40, unitWidth, 2);

        paintSelfHealthBar(g2);
    }

    public void spawnStuff(UnitHolder unitHolder, int day)
    {
        if (zombieTimer > (int) (200.0 - zombieTimerRate))
        {
            Point spawnPoint = new Point(unitPoint.x + 1, unitPoint.y);
            zombieSpawnType = (int) (Math.random() * 200.0);
            if (zombieSpawnType <= 20 && day != 2 && day != 3 && day != 8)
            {
                unitHolder.createUnit(new BasicZombie(background, spawnPoint, unitHolder.getPlayer(), projectileHolder, false));
            } else if (zombieSpawnType <= 30 && day > 1 && day != 3 && day != 8)
            {
                unitHolder.createUnit(new BasicSkeleton(background, spawnPoint, unitHolder.getPlayer(), projectileHolder, false));
            } else if (zombieSpawnType <= 40 && day > 2 && day != 8)
            {
                unitHolder.createUnit(new RunnerZombie(background, spawnPoint, unitHolder.getPlayer(), projectileHolder, false));
            } else if (zombieSpawnType <= 45 && day > 4 && day != 8 && this instanceof StartingGrave)
            {
                unitHolder.createUnit(new GravePlanter(background, spawnPoint, unitHolder.getPlayer(), projectileHolder, false));
            } else if ((zombieSpawnType <= 50 && day > 5 && day != 8) || (day == 8 && zombieSpawnType <= 20))
            {
                unitHolder.createUnit(new SkeletalMinotaur(background, spawnPoint, unitHolder.getPlayer(), projectileHolder, false));
            } else if ((zombieSpawnType <= 55 && day > 6 && day != 8) || (day == 8 && zombieSpawnType <= 40))
            {
                unitHolder.createUnit(new ZombieCommando(background, spawnPoint, unitHolder.getPlayer(), projectileHolder, false));
            }
            if (day > 8)
            {
                zombieTimerRate += 0.01;
            }
            zombieTimer = 0;
        } else
        {
            zombieTimer++;
        }
    }

    @Override
    public void paintSelfHealthBar(Graphics2D g2)
    {
        g2.setColor(Color.red);
        g2.fillRect(screenPoint.x, screenPoint.y - unitHeight - 10, (int) ((health / 100.0) * unitWidth), 5);
    }

    @Override
    protected boolean shouldMove()
    {
        return false;
    }

    @Override
    protected boolean shouldAttack()
    {
        return false;
    }

    @Override
    protected void chooseMove()
    {
    }

    @Override
    public void moveAway(Rectangle rect)
    {
    }

    @Override
    protected void die()
    {
        projectileHolder.makeLargeSmoke(this, unitPoint, isPlayer, 50);
        isDead = true;
    }

    @Override
    public void damageSelfFace(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeSmoke(this, hitByBulletPoint, isPlayer, 5);
        health -= damage;
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
}

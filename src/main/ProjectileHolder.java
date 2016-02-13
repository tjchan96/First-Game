package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import projectiles.Blood;
import projectiles.Fire;
import projectiles.GenericPenProjectile;
import projectiles.GenericProjectile;
import projectiles.GrenadeProjectile;
import projectiles.NukeProjectile;
import projectiles.Smoke;
import projectiles.Sparks;
import units.GenericGrave;
import units.GenericUnit;
import units.Player;
import units.StartingGrave;

public class ProjectileHolder
{
    protected Main main = null;
    private ArrayList<GenericProjectile> projectileList = new ArrayList<GenericProjectile>();
    private ArrayList<GenericProjectile> particleList = new ArrayList<GenericProjectile>();
    private ArrayList<Point> headshotList = new ArrayList<Point>();
    private ArrayList<GenericUnit> invincibleList = new ArrayList<GenericUnit>();

    public ProjectileHolder(Main mainArg)
    {
        main = mainArg;
    }

    public void createProjectile(GenericProjectile projectile)
    {
        projectileList.add(projectile);
    }

    public void runProjectiles()
    {
        destroyProjectiles();
        addParticles();
        for (int i = 0; i < projectileList.size(); i++)
        {
            projectileList.get(i).run();
        }
    }

    public void makeFire(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int flameCount)
    {
        for (int i = 0; i < flameCount; ++i)
        {
            particleList.add(new Fire(firingUnit, projectilePoint, 0, 35, 0, playerProjectile));
        }
    }

    public void makeMassiveFire(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int flameCount)
    {
        for (int i = 0; i < flameCount; ++i)
        {
            particleList.add(new Fire(firingUnit, projectilePoint, 0, 250, 0, playerProjectile));
        }
    }

    public void makeSmoke(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int smokeCount)
    {
        for (int i = 0; i < smokeCount; ++i)
        {
            particleList.add(new Smoke(firingUnit, projectilePoint, 0, 10, 0, playerProjectile));
        }
    }

    public void makeLargeSmoke(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int smokeCount)
    {
        for (int i = 0; i < smokeCount; ++i)
        {
            particleList.add(new Smoke(firingUnit, projectilePoint, 0, 20, 0, playerProjectile));
        }
    }

    public void makeHugeSmoke(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int smokeCount)
    {
        for (int i = 0; i < smokeCount; ++i)
        {
            particleList.add(new Smoke(firingUnit, projectilePoint, 0, 35, 0, playerProjectile));
        }
    }

    public void makeMassiveSmoke(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int smokeCount)
    {
        for (int i = 0; i < smokeCount; ++i)
        {
            particleList.add(new Smoke(firingUnit, projectilePoint, 0, 250, 0, playerProjectile));
        }
    }

    public void makeSparks(GenericUnit firingUnit, Point projectilePoint, boolean playerProjectile, int angle)
    {
        for (int i = 0; i < 3; ++i)
        {
            particleList.add(new Sparks(firingUnit, projectilePoint, 0, 10, angle, playerProjectile));
        }
    }

    public void makeBlood(GenericUnit firingUnit, Point hitPoint, boolean isPlayer, int bloodCount)
    {
        for (int i = 0; i < bloodCount; ++i)
        {
            particleList.add(new Blood(firingUnit, hitPoint, 0, 10, 0, isPlayer));
        }
    }

    public void makeLargeBlood(GenericUnit firingUnit, Point hitPoint, boolean isPlayer, int bloodCount)
    {
        for (int i = 0; i < bloodCount; ++i)
        {
            particleList.add(new Blood(firingUnit, hitPoint, 0, 15, 0, isPlayer));
        }
    }

    private void addParticles()
    {
        for (int i = 0; i < particleList.size(); i++)
        {
            projectileList.add(particleList.get(i));
        }
        particleList.clear();
    }

    public void paintSelfProjectiles(Graphics2D g2)
    {
        for (int i = 0; i < projectileList.size(); i++)
        {
            projectileList.get(i).paintSelf(g2, main.getUnitHolder().getPlayer().getUnitPoint());
        }
        g2.setColor(Color.yellow);
        g2.setFont(new Font("showcard gothic", Font.PLAIN, 12));
        Iterator<Point> iter = headshotList.iterator();
        while (iter.hasNext())
        {
            Point point = (Point) iter.next();
            g2.drawString("Headshot", point.x - 50 - main.getUnitHolder().getPlayer().getUnitPoint().x + Main.screenWidth / 2, point.y);
            point.y -= 2;
            if (point.y <= 0)
            {
                iter.remove();
            }
        }
        Iterator<GenericUnit> iter2 = invincibleList.iterator();
        while (iter2.hasNext())
        {
            GenericUnit unit = (GenericUnit) iter2.next();
            unit.paintSelfHealthBar(g2);
            iter2.remove();
        }
    }

    private void destroyProjectiles()
    {
        Iterator<GenericProjectile> projectileIterator = projectileList.listIterator();
        outerloop:
        while (projectileIterator.hasNext())
        {
            GenericProjectile projectile = projectileIterator.next();

            if (!(projectile.noCollision()))
            {
                if (projectile.hasHitGround())
                {
                    if (projectile.shouldMakeParticle())
                    {
                        makeSmoke(projectile.getUnit(), projectile.getPoint(), projectile.isIsPlayerProjectile(), 5);
                        if (projectile instanceof GrenadeProjectile)
                        {
                            makeHugeSmoke(projectile.getFiringUnit(), projectile.getPoint(), true, 30);
                            makeFire(projectile.getFiringUnit(), projectile.getPoint(), true, 30);
                        }
                        if (projectile instanceof NukeProjectile)
                        {
                            makeMassiveSmoke(projectile.getFiringUnit(), projectile.getPoint(), true, 150);
                            makeMassiveFire(projectile.getFiringUnit(), projectile.getPoint(), true, 150);
                        }
                        projectileIterator.remove();
                        continue;
                    } else
                    {
                        projectileIterator.remove();
                        continue;
                    }
                }
            }
            if (projectile.isOutOfBounds())
            {
                projectileIterator.remove();
                continue;
            }
            for (GenericUnit target : main.getUnitHolder().getEnemiesOf(projectile.isIsPlayerProjectile()))
            {
                if (((projectile.hasHitTargetBody(target) || projectile.hasHitTargetHead(target))))
                {
                    if (projectile.hasHitTargetHead(target) && !(target.isPlayer()) && !target.shotHead())
                    {
                        target.damageSelfFace(projectile.getDamage(), projectile.getProjectilePoint());
                        if (!(target instanceof GenericGrave) && !(target instanceof Player))
                        {
                            headshotList.add(new Point(projectile.getProjectilePoint()));
                            main.getUnitHolder().getPlayer().setMoney(main.getUnitHolder().getPlayer().getMoney() + 5);
                            main.getUnitHolder().getPlayer().setScore(main.getUnitHolder().getPlayer().getScore() + 5);
                        }
                        if (target instanceof StartingGrave)
                        {
                            invincibleList.add(target);
                        }
                    } else
                    {
                        target.damageSelf(projectile.getDamage(), projectile.getProjectilePoint());
                        if (target instanceof StartingGrave)
                        {
                            invincibleList.add(target);
                        }
                    }
                    if (projectile instanceof GrenadeProjectile)
                    {
                        makeHugeSmoke(projectile.getFiringUnit(), projectile.getPoint(), true, 30);
                        makeFire(projectile.getFiringUnit(), projectile.getPoint(), true, 30);
                    }
                    if (projectile instanceof NukeProjectile)
                    {
                        makeMassiveSmoke(projectile.getFiringUnit(), projectile.getPoint(), true, 150);
                        makeMassiveFire(projectile.getFiringUnit(), projectile.getPoint(), true, 150);
                    }
                    if (!(projectile instanceof GenericPenProjectile))
                    {
                        projectileIterator.remove();
                        continue outerloop;
                    } else
                    {
                        if (projectile.damageReduce())
                        {
                            projectileIterator.remove();
                            continue outerloop;
                        }
                    }
                }
            }
        }
    }

	public Main getMain() {
		return main;
	}
}

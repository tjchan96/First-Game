package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import units.GenericGrave;
import units.GenericUnit;
import units.Player;

/**
 *
 * @author TJ
 */
public class UnitHolder
{
    private Main main = null;
    private ArrayList<GenericUnit> unitList = new ArrayList<GenericUnit>();
    private ArrayList<GenericUnit> unitsToBeMadeList = new ArrayList<GenericUnit>();

    public UnitHolder(Main mainArg)
    {
        main = mainArg;
    }

    public void createUnitDelayed(GenericUnit unit)
    {
        unitsToBeMadeList.add(unit);
    }

    public void createUnit(GenericUnit unit)
    {
        unitList.add(unit);
    }

    public void runUnits()
    {
        for (int i = 0; i < unitsToBeMadeList.size(); i++)
        {
            unitList.add(unitsToBeMadeList.get(i));
        }
        unitsToBeMadeList.clear();
        destroyUnits();
        for (int i = 0; i < unitList.size(); i++)
        {
            unitList.get(i).run();
            if (unitList.get(i) instanceof GenericGrave)
            {
                GenericGrave grave = (GenericGrave) unitList.get(i);
                grave.spawnStuff(this, main.getBackground().getDay());
            }
        }
        handleCollisions();
    }

    public void paintSelfUnits(Graphics2D g2)
    {
        for (int i = unitList.size() - 1; i >= 0; i--)
        {
            unitList.get(i).paintSelf(g2, getPlayer().getUnitPoint());
        }
    }

    public ArrayList<GenericUnit> getEnemiesOf(boolean isPlayer)
    {
        ArrayList<GenericUnit> enemyUnits = new ArrayList<GenericUnit>();
        for (int i = 0; i < unitList.size(); i++)
        {
            if (unitList.get(i).isPlayer() != isPlayer)
            {
                enemyUnits.add(unitList.get(i));
            }
        }
        return enemyUnits;
    }

    private void destroyUnits()
    {
        Iterator<GenericUnit> unitIterator = unitList.listIterator();
        while (unitIterator.hasNext())
        {
            GenericUnit unit = unitIterator.next();
            if (unit.isDead())
            {
                unitIterator.remove();
                if (getPlayer().isPlayer() == false)
                {
                    main.lose();
                } else
                {
                    getPlayer().setMoney(getPlayer().getMoney() + 10);
                    getPlayer().setScore(getPlayer().getScore() + 10);
                }
                continue;
            }
        }
    }

    private void handleCollisions()
    {
        for (int i = 0; i < unitList.size(); i++)
        {
            for (int j = 0; j < unitList.size(); j++)
            {
                if (i != j && unitList.get(i).getBoundingBox().intersects(unitList.get(j).getBoundingBox()))
                {
                    if (!(unitList.get(i) instanceof GenericGrave) && !(unitList.get(j) instanceof GenericGrave) && !unitList.get(i).isDying() && !unitList.get(j).isDying())
                    {
                        unitList.get(i).moveAway(unitList.get(j).getBoundingBox());
                        unitList.get(j).moveAway(unitList.get(i).getBoundingBox());
                    }
                }
            }
        }
    }

    public Player getPlayer()
    {
        if (unitList.isEmpty() || (!(unitList.get(0) instanceof Player)))
        {
            main.lose();
            return null;
        }
        return (Player) unitList.get(0);
    }
}
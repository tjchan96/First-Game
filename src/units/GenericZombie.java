package units;

import java.awt.Point;

import main.Background;
import main.ProjectileHolder;
import weapons.GenericWeapon;

public class GenericZombie extends GenericUnit
{
    public GenericZombie(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        weapon = new GenericWeapon(this, target, projectileHolder, false);
        targetPoint = target.unitPoint;
    }

    @Override
    protected boolean shouldMove()
    {
        return Math.abs(xDistanceToTarget) > 40;
    }

    @Override
    protected void chooseMove()
    {
        if (isDying)
        {
            shouldMoveLeft = false;
            shouldMoveRight = false;
        } else
        {
            if (xDistanceToTarget < 0)
            {
                shouldMoveLeft = true;
                shouldMoveRight = false;
            } else if (xDistanceToTarget > 0)
            {
                shouldMoveRight = true;
                shouldMoveLeft = false;
            }
        }
        if (jumpingCount == 0)
        {
            shouldMoveUp = false;
        }
    }

    @Override
    protected boolean shouldAttack()
    {
        return distanceToTarget < range && !isDying;
    }
}

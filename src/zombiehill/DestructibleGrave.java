package zombiehill;

import java.awt.Point;

public class DestructibleGrave extends GenericGrave
{
    public DestructibleGrave(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        zombieTimerRate = 100;
    }
}

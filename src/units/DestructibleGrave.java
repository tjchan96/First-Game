package units;

import java.awt.Point;

import main.Background;
import main.ProjectileHolder;

public class DestructibleGrave extends GenericGrave
{
    public DestructibleGrave(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        zombieTimerRate = 100;
    }
}

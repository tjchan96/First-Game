package weapons;

import java.awt.Point;

import main.ProjectileHolder;
import units.DestructibleGrave;
import units.GenericUnit;

public class GravePlanterWeapon extends GenericWeapon
{
    public GravePlanterWeapon(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 0;
        projectileSpeed = 0;
        attackSpeed = 0.3;
        recoil = 0;
    }

    @Override
    protected void createProjectile()
    {
        projectileHolder.getMain().getUnitHolder().createUnitDelayed(new DestructibleGrave(projectileHolder.getMain().getBackground(), new Point(holderPoint), projectileHolder.getMain().getUnitHolder().getPlayer(), projectileHolder, false));
    }
}
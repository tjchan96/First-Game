package zombiehill;

import java.awt.Point;

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
        projectileHolder.main.getUnitHolder().createUnitDelayed(new DestructibleGrave(projectileHolder.main.getBackground(), new Point(holderPoint), projectileHolder.main.getUnitHolder().getPlayer(), projectileHolder, false));
    }
}
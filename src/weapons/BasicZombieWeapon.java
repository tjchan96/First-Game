package weapons;

import main.ProjectileHolder;
import projectiles.InvisibleProjectile;
import units.GenericUnit;

public class BasicZombieWeapon extends GenericWeapon
{
    public BasicZombieWeapon(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 5;
        projectileSpeed = 15;
        attackSpeed = 10;
    }

    @Override
    protected void createProjectile()
    {
        projectileHolder.createProjectile(new InvisibleProjectile(holder, holder.getUnitPoint(), damage, projectileSpeed, getDirectionToTarget(), isPlayerWeapon));
    }
}
package zombiehill;

public class BasicSkeletonWeapon extends GenericWeapon
{
    public BasicSkeletonWeapon(GenericUnit holder, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayerWeapon)
    {
        super(holder, target, projectileHolder, isPlayerWeapon);
        damage = 5;
        projectileSpeed = 10;
        attackSpeed = 2;
    }

    @Override
    protected void createProjectile()
    {
        projectileHolder.createProjectile(new BoneProjectile(holder, holder.unitPoint, damage, projectileSpeed, getDirectionToTarget(), isPlayerWeapon));
    }
}
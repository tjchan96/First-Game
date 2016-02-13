package units;

import java.awt.Color;
import java.awt.Point;

import main.Background;
import main.ProjectileHolder;
import weapons.BasicZombieWeapon;

public class BasicZombie extends GenericZombie
{
    public BasicZombie(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 2;
        range = 70;
        health = 120;
        weapon = new BasicZombieWeapon(this, target, projectileHolder, false);
        color1 = new Color(90, 75, 42);
        color2 = new Color(110, 95, 62);
        maxHealth = health;
    }
}
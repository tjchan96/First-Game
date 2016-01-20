package zombiehill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Blood extends GenericParticle
{
    public Blood(GenericUnit firingUnit, Point spawn, int damage, int speed, int angle, boolean isPlayerProjectile)
    {
        super(firingUnit, spawn, damage, speed, angle, isPlayerProjectile);
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(projectilePoint.x - playerPoint.x + Main.screenWidth / 2, projectilePoint.y);
        g2.setColor(Color.red);
        g2.fillOval(screenPoint.x, screenPoint.y, radius, radius);
    }
}

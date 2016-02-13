package units;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import main.Background;
import main.Main;
import main.ProjectileHolder;

/**
 *
 * @author TJ
 */
public class StartingGrave extends GenericGrave
{
    public StartingGrave(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        zombieTimerRate = 150;
    }

    @Override
    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(unitPoint.x - playerPoint.x + Main.screenWidth / 2, unitPoint.y);

        g2.setColor(color1);
        g2.fillOval(screenPoint.x, screenPoint.y - unitHeight / 2, unitWidth * 5 / 4, unitWidth * 5 / 4);
        g2.fillRect(screenPoint.x, screenPoint.y, unitWidth * 5 / 4, unitHeight * 7 / 4);
        boundingBox.setBounds(screenPoint.x, screenPoint.y, unitWidth * 5 / 4, unitHeight * 7 / 4);
        boundingCircle.setFrame(screenPoint.x, screenPoint.y - unitHeight / 2, unitWidth * 5 / 4, unitWidth * 5 / 4);

        g2.setColor(color2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 10, unitWidth, 2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 20, unitWidth, 2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 30, unitWidth, 2);
        g2.fillRect(screenPoint.x + unitWidth / 8, screenPoint.y + 40, unitWidth, 2);
    }

    @Override
    public void paintSelfHealthBar(Graphics2D g2)
    {
        g2.setColor(Color.red);
        g2.setFont(new Font("showcard gothic", Font.PLAIN, 12));
        g2.drawString("Indestructable", screenPoint.x - 20, screenPoint.y - unitHeight - 10);
    }

    @Override
    public void damageSelfFace(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeSmoke(this, hitByBulletPoint, isPlayer, 5);
    }

    @Override
    public void damageSelf(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeSmoke(this, hitByBulletPoint, isPlayer, 5);
    }
}

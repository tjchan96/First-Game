package zombiehill;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author TJ
 */
public class Background
{
    private Main main = null;
    private ArrayList<Point> stars = new ArrayList<Point>();
    private ArrayList<String> level = new ArrayList<String>();
    private double timeOfDay = 12.0;
    private int day = 1;
    private int hillQuality = 5;
    private int darkness = 0;

    public Background(Main main)
    {
        this.main = main;
        for (int i = 0; i < 100; i++)
        {
            stars.add(new Point((int) (Math.random() * Main.screenWidth), (int) (Math.random() * Main.screenHeight)));
        }
        level.add("zombies");
        level.add("skeletons");
        level.add("runners");
        level.add("destructible graves");
        level.add("grave planters");
        level.add("skeletal minotaurs");
        level.add("zombie commandos");
        level.add("the elites");
//        weaponStrings.add("pistol -  starting weapon");
//        weaponStrings.add("magnum - headshots ftw");
//        weaponStrings.add("rifle - singleshot");
//        weaponStrings.add("long rifle - like a rifle, but longer -_-");
//        weaponStrings.add("sniper rifle - hits multiple enemies");
//        weaponStrings.add("carbine - three round burst");
//        weaponStrings.add("ranged carbine - as its name implies, it is a carbine with a higher rate of fire.. wait a minute");
//        weaponStrings.add("nuclear strike - a red button with an antenna on the end");
//        weaponStrings.add("smg - small machinegun, in case you're wierd and don't know what it stands for");
//        weaponStrings.add("assault rifle - not be confused with the rifle, long rifle, or sniper rifle");
//        weaponStrings.add("lmg - light machinegun. not large machinegun, you nej");
//        weaponStrings.add("flamethrower - it had to be included, and i couldn't think of anywhere else to put it- so its a progression of lmg");
//        weaponStrings.add("pump shotgun - no, we did have time to actually make it pump");
//        weaponStrings.add("auto shotgun - its pretty op");
//        weaponStrings.add("grenade launcher - for some reason its after auto shotgun");
    }

    public int hillGround(int x)
    {
        double newX = x;
        double y = (Math.sin(newX / 200.0)) * 50.0 + (Math.sin(newX / 60.0)) * 30.0 + Main.screenHeight - 150.0;
        return (int) y;
    }

    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        if (!Main.isRunShop())
        {
            timeOfDay += 0.01;
            if (timeOfDay > 24)
            {
                timeOfDay = 0;
                day++;
                main.getUnitHolder().getPlayer().setHealth(main.getUnitHolder().getPlayer().maxHealth);
                if (day == 4)
                {
                    main.setSpawnGraves(true);
                }
                if (day > 8)
                {
                    level.add("you hacker");
                }
            }
        }
        darkness = (int) (-(Math.abs(timeOfDay - 12.0)) * 20);
        paintSelfSky(g2);
        paintSelfStars(g2);
        paintSelfSunAndMoon(g2);
        paintSelfHillGround(g2, playerPoint);
    }

    private void paintSelfHillGround(Graphics2D g2, Point playerPoint)
    {
        for (int i = -Main.screenWidth * 2 / hillQuality; i < Main.screenWidth / hillQuality * 3; i++)
        {
            int x3 = i * hillQuality - playerPoint.x;
            int y3 = hillGround(i * hillQuality);
            g2.setColor(colorWithDarkness(new Color(166, 232, 122)));
            g2.fillRect(x3, y3, hillQuality * 2, Main.screenHeight * 2);
            g2.setColor(colorWithDarkness(new Color(177, 255, 124)));
            g2.fillRect(x3, y3 - 18, hillQuality * 2, 10);
            g2.fillRect(x3, y3 + 9, hillQuality * 2, 10);
            g2.setColor(colorWithDarkness(new Color(219, 203, 141)));
            g2.fillRect(x3, y3 - 9, hillQuality * 2, 10);
            g2.fillRect(x3, y3, hillQuality * 2, 10);
        }

        g2.setColor(Color.yellow);
        g2.setFont(new Font("showcard gothic", Font.PLAIN, 50));
        g2.drawString("day  " + day + "  " + level.get(day - 1), (int) (Main.screenWidth / 2 - 200), (int) (Main.screenHeight / 2 - 200));
        g2.setFont(new Font("showcard gothic", Font.PLAIN, 20));
        g2.drawString("money  " + main.getUnitHolder().getPlayer().getMoney(), (int) (50), (int) (Main.screenHeight / 2 - 250));
        g2.drawString("score  " + main.getUnitHolder().getPlayer().getScore(), (int) (Main.screenWidth - 150), (int) (Main.screenHeight / 2 - 250));
        g2.drawString(main.getUnitHolder().getPlayer().weapon.getClass().getName().substring(11), (int) (Main.screenWidth / 2 - 200), (int) (Main.screenHeight / 2 - 150));
    }

    private void paintSelfSky(Graphics2D g2)
    {
        g2.setColor(colorWithDarkness(new Color(145, 231, 253)));
        g2.fillRect(0, 0, Main.screenWidth, Main.screenHeight);
    }

    private void paintSelfSunAndMoon(Graphics2D g2)
    {
        AffineTransform nonTransformed = g2.getTransform();

        g2.rotate(Math.toRadians(timeOfDay * 15), Main.screenWidth / 2, Main.screenHeight);

        g2.setColor(new Color(235, 235, 235));
        g2.fillOval(Main.screenWidth / 2, 100, 50, 50);
        g2.setColor(new Color(255, 251, 104));
        g2.fillOval(Main.screenWidth / 2, Main.screenHeight * 2 - 100, 50, 50);

        g2.setTransform(nonTransformed);
    }

    private void paintSelfStars(Graphics2D g2)
    {
        for (int i = 0; i < stars.size(); i++)
        {
            g2.setColor(new Color(95, 181, 203));
            g2.fillOval(stars.get(i).x, stars.get(i).y, 3, 3);
        }
    }

    private Color colorWithDarkness(Color color)
    {
        if (darkness > -50)
        {
            darkness = -50;
        } else if (darkness < -200)
        {
            darkness = -200;
        }

        int red = color.getRed() + darkness;
        if (red > 255)
        {
            red = 255;
        } else if (red < 0)
        {
            red = 0;
        }
        int green = color.getGreen() + darkness;
        if (green > 255)
        {
            green = 255;
        } else if (green < 0)
        {
            green = 0;
        }
        int blue = color.getBlue() + darkness;
        if (blue > 255)
        {
            blue = 255;
        } else if (blue < 0)
        {
            blue = 0;
        }
        return new Color(red, green, blue);
    }

    public int getDay()
    {
        return day;
    }
}
package main;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import units.DestructibleGrave;
import units.Player;
import units.StartingGrave;

/**
 *
 * @author TJ
 */
public class Main
{
    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
    private static int fPS = 50;
    private static int score = 50;
    private static boolean run = true;
    private static boolean titleScreenRun = true;
    private static boolean runShop = true;
    private static Main main = null;
    private boolean spawnGraves = false;
    private Painter painter = new Painter(this);
    private TitleScreen titleScreen = new TitleScreen();
    private JFrame screen = null;
    private Shop shop = null;
    private Background background = new Background(this);
    private UnitHolder unitHolder = new UnitHolder(this);
    private ProjectileHolder projectileHolder = new ProjectileHolder(this);

    public static void main(String[] args)
    {
        main = new Main();
        Timer mainTimer = new Timer(1000 / fPS, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (run)
                {
                    main.run();
                }
            }
        });
        mainTimer.start();
    }

    public Main()
    {
        screen = new JFrame("Zombie Hill");
        screen.setSize(screenWidth, screenHeight);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.add(painter);
        screen.setVisible(true);
        unitHolder.createUnit(new Player(background, new Point(Main.screenWidth / 2, Main.screenHeight), null, projectileHolder, true));
        unitHolder.createUnit(new StartingGrave(background, new Point(-Main.screenWidth * 3 / 2 - 200, background.hillGround(-Main.screenWidth)), unitHolder.getPlayer(), projectileHolder, false));
        unitHolder.createUnit(new StartingGrave(background, new Point(-Main.screenWidth * 3 / 2 - 400, background.hillGround(-Main.screenWidth)), unitHolder.getPlayer(), projectileHolder, false));
        unitHolder.createUnit(new StartingGrave(background, new Point(-Main.screenWidth * 3 / 2 - 600, background.hillGround(-Main.screenWidth)), unitHolder.getPlayer(), projectileHolder, false));
        unitHolder.createUnit(new StartingGrave(background, new Point(Main.screenWidth * 3 / 2 + 200, background.hillGround(-Main.screenWidth)), unitHolder.getPlayer(), projectileHolder, false));
        unitHolder.createUnit(new StartingGrave(background, new Point(Main.screenWidth * 3 / 2 + 400, background.hillGround(-Main.screenWidth)), unitHolder.getPlayer(), projectileHolder, false));
        unitHolder.createUnit(new StartingGrave(background, new Point(Main.screenWidth * 3 / 2 + 600, background.hillGround(-Main.screenWidth)), unitHolder.getPlayer(), projectileHolder, false));
        shop = new Shop(this);
    }

    private void run()
    {
        if (!titleScreenRun)
        {
            if (!runShop)
            {
                unitHolder.runUnits();
                projectileHolder.runProjectiles();
                if (spawnGraves)
                {
                    for (int i = 0; i < 10; i++)
                    {
                        unitHolder.createUnit(new DestructibleGrave(background, new Point((int) (((i - 5) * Main.screenWidth / 4) + (Main.screenWidth / 4 * Math.random())), 0), unitHolder.getPlayer(), projectileHolder, false));
                    }
                    spawnGraves = false;
                }
            }
            shop.run();
        }
        painter.repaint();
        score = unitHolder.getPlayer().getScore();
    }

    public void lose()
    {
        if (titleScreenRun == false)
        {
            titleScreenRun = true;
            JOptionPane.showMessageDialog(screen, "score: " + score + "   gg no re");
            screen.setVisible(false);
            screen.dispose();
            System.exit(0);
        }
    }

    public Background getBackground()
    {
        return background;
    }

    public UnitHolder getUnitHolder()
    {
        return unitHolder;
    }

    public ProjectileHolder getProjectileHolder()
    {
        return projectileHolder;
    }

    public Shop getShop()
    {
        return shop;
    }

    public TitleScreen getTitleScreen()
    {
        return titleScreen;
    }

    public static boolean isTitleScreenRun()
    {
        return titleScreenRun;
    }

    public static void setTitleScreenRun(boolean run)
    {
        Main.titleScreenRun = run;
    }

    public static boolean isRunShop()
    {
        return runShop;
    }

    public static void setRunShop(boolean runShop)
    {
        Main.runShop = runShop;
    }

    public void setSpawnGraves(boolean spawnGraves)
    {
        this.spawnGraves = spawnGraves;
    }
}
package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author TJ
 */
public class TitleScreen
{
    private ArrayList<Point> stars = new ArrayList<Point>();
    private Rectangle start = new Rectangle(Main.screenWidth / 4, 150, Main.screenWidth / 2, 150);
    private Rectangle instructions = new Rectangle(Main.screenWidth / 4, 350, Main.screenWidth / 2, 100);
    private Rectangle credits = new Rectangle(Main.screenWidth / 4, 500, Main.screenWidth / 2, 100);
    private ArrayList<String> instructionString = new ArrayList<String>();
    private ArrayList<String> creditsString = new ArrayList<String>();
    private double timeOfDay = 12.0;
    private boolean instructionsOpen = false;
    private boolean creditsOpen = false;
    private int hillQuality = 5;
    private int darkness = 0;

    public TitleScreen()
    {
        for (int i = 0; i < 100; i++)
        {
            stars.add(new Point((int) (Math.random() * Main.screenWidth), (int) (Math.random() * Main.screenHeight)));
        }
        instructionString.add("Controls: wasd to move, q/e/mousewheel to switch weapons");
        instructionString.add("	f or p for shop, mouse to shoot");
        instructionString.add("health regenerates after each day");
        instructionString.add("crouching slows you down but increases accuracy");
        instructionString.add("press space to knife");
        instructionString.add("headshots deal 3x damage and garner $5");
        instructionString.add("kills give you $10");
        instructionString.add("score = money gained; no score subtracted for buying weapons");
        creditsString.add("Coding, design, graphics: TJ Chan, Royston Chan, Charles Huang");
        creditsString.add("*In order of credit");
    }

    public void run(MouseEvent me)
    {
        if (start.contains(me.getPoint()))
        {
            Main.setTitleScreenRun(false);
        }
        if (instructionsOpen)
        {
        	instructionsOpen = false;
        }
        else if (instructions.contains(me.getPoint()))
        {
        	instructionsOpen = true;
        }
        if (creditsOpen)
        {
        	creditsOpen = false;
        }
        else if (credits.contains(me.getPoint()))
        {
        	creditsOpen = true;
        }
    }

    public int hillGround(int x)
    {
        double newX = x;
        double y = (Math.sin(newX / 200.0)) * 50.0 + (Math.sin(newX / 60.0)) * 30.0 + Main.screenHeight - 150.0;
        return (int) y;
    }

    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        timeOfDay += 0.05;
        if (timeOfDay > 24)
        {
            timeOfDay = 0;
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

        g2.setColor(Color.gray);
        g2.fill(start);
        g2.fill(instructions);
        g2.fill(credits);
        g2.setColor(Color.yellow);
        g2.setFont(new Font("showcard gothic", Font.PLAIN, 100));
        g2.drawString("start", start.x + start.width / 16, start.y + start.height * 3 / 4);
        if (instructionsOpen)
        {
            g2.setFont(new Font("showcard gothic", Font.PLAIN, 12));
            instructions.height = 250;
            creditsOpen = false;
            for (int i = 0; i < instructionString.size(); i++)
            {
                g2.drawString(instructionString.get(i), instructions.x + instructions.width / 16, instructions.y + instructions.height * (i + 1) / 10);
            }
        } else if (!creditsOpen)
        {
            g2.setFont(new Font("showcard gothic", Font.PLAIN, 60));
            instructions.height = 100;
            g2.drawString("instructions", instructions.x + instructions.width / 16, instructions.y + instructions.height * 3 / 4);
        }
        if (creditsOpen)
        {
            g2.setFont(new Font("showcard gothic", Font.PLAIN, 12));
            credits.y = 350;
            credits.height = 250;
            instructionsOpen = false;
            for (int i = 0; i < creditsString.size(); i++)
            {
                g2.drawString(creditsString.get(i), credits.x + credits.width / 16, credits.y + credits.height * (i + 1) / 10);
            }
        } else if (!instructionsOpen)
        {
            g2.setFont(new Font("showcard gothic", Font.PLAIN, 60));
            credits.y = 500;
            credits.height = 100;
            g2.drawString("credits", credits.x + credits.width / 16, credits.y + credits.height * 3 / 4);
        }
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
}
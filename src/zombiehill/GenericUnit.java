package zombiehill;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class GenericUnit
{
    protected Background background = null;
    protected ProjectileHolder projectileHolder = null;
    protected GenericWeapon weapon = null;
    protected Polygon foot1 = null;
    protected Polygon foot2 = null;
    protected Rectangle boundingBox = new Rectangle();
    protected Ellipse2D boundingCircle = new Ellipse2D.Double();
    protected Color color1 = null;
    protected Color color2 = null;
    protected Point screenPoint = new Point();
    protected Point unitPoint = new Point();
    protected Point targetPoint = new Point();
    protected Point hitByBulletPoint = new Point();
    protected Point deathPoint = new Point();
    protected GenericUnit target = null;
    protected boolean shouldMoveLeft = false;
    protected boolean shouldMoveRight = false;
    protected boolean shouldMoveUp = false;
    protected boolean shouldMoveDown = false;
    protected boolean foot1Forward = false;
    protected boolean isFacingLeft = false;
    protected boolean isPlayer = false;
    protected boolean isDying = false;
    protected boolean isDead = false;
    protected boolean shotHead = false;
    protected int[] foot1XPoints = new int[4];
    protected int[] foot1YPoints = new int[4];
    protected int[] foot2XPoints = new int[4];
    protected int[] foot2YPoints = new int[4];
    protected double maxHealth = 100;
    protected double health = 100;
    protected int deathTimer = 0;
    protected int jumpMax = 30;
    protected int foot1Offset = 0;
    protected int bloodCount = 0;
    protected int jumpingCount = 0;
    protected int unitWidth = 50;
    protected int unitHeight = 50;
    protected int speed = 10;
    protected int range = 30;
    protected int xDistanceToTarget = 0;
    protected int distanceToTarget = 0;
    protected int angle = 0;

    public GenericUnit(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        this.projectileHolder = projectileHolder;
        this.background = background;
        this.target = target;
        this.isPlayer = isPlayer;
        unitPoint = spawn;
    }

    public void run()
    {
        weapon.run();
        calculateDistance();
        if (shouldAttack())
        {
            weapon.setShouldAttack(true);
        } else
        {
            weapon.setShouldAttack(false);
        }
        chooseMove();
        if (shouldMove())
        {
            move();
        }
        handleGravity();
    }

    public void paintSelf(Graphics2D g2, Point playerPoint)
    {
        screenPoint = new Point(unitPoint.x - playerPoint.x + Main.screenWidth / 2, unitPoint.y);
        paintSelfFeet(g2, playerPoint, color2);
        paintSelfHead(g2, color2);
        g2.setColor(color1);
        if (isFacingLeft)
        {
            g2.fillRect(screenPoint.x + unitWidth / 8 + 5, screenPoint.y, unitWidth * 3 / 4, unitHeight);
            boundingBox.setBounds(screenPoint.x + unitWidth / 8 + 5, screenPoint.y, unitWidth * 3 / 4, unitHeight + 25);
        } else
        {
            g2.fillRect(screenPoint.x + unitWidth / 8 - 5, screenPoint.y, unitWidth * 3 / 4, unitHeight);
            boundingBox.setBounds(screenPoint.x + unitWidth / 8 - 5, screenPoint.y, unitWidth * 3 / 4, unitHeight + 25);
        }
        weapon.paintSelf(g2, playerPoint, color2);
        paintSelfHealthBar(g2);
    }

    protected void paintSelfHead(Graphics2D g2, Color color)
    {
        g2.setColor(color);
        if (!(shotHead) || (isPlayer))
        {
            g2.fillOval(screenPoint.x + unitWidth / 8, screenPoint.y - unitHeight * 5 / 8, unitWidth * 3 / 4, unitHeight * 3 / 4);
            boundingCircle.setFrame(screenPoint.x + unitWidth / 8, screenPoint.y - unitHeight * 5 / 8, unitWidth * 3 / 4, unitHeight * 3 / 4);
        } else
        {
            boundingCircle.setFrame(0, 0, 0, 0);
        }
    }

    protected void paintSelfFeet(Graphics2D g2, Point playerPoint, Color color)
    {
        g2.setColor(color);
        foot1XPoints[0] = screenPoint.x + unitWidth * 5 / 16 + foot1Offset;
        foot1XPoints[1] = screenPoint.x + unitWidth * 6 / 16;
        foot1XPoints[2] = screenPoint.x + unitWidth * 10 / 16;
        foot1XPoints[3] = screenPoint.x + unitWidth * 11 / 16 + foot1Offset;

        if (jumpingCount == jumpMax)
        {
            foot1YPoints[0] = background.hillGround(foot1XPoints[0] + playerPoint.x);
            foot1YPoints[1] = screenPoint.y + unitWidth / 2;
            foot1YPoints[2] = screenPoint.y + unitWidth / 2;
            foot1YPoints[3] = background.hillGround(foot1XPoints[3] + playerPoint.x);
        } else
        {
            foot1YPoints[0] = screenPoint.y + unitWidth * 3 / 2;
            foot1YPoints[1] = screenPoint.y + unitWidth / 2;
            foot1YPoints[2] = screenPoint.y + unitWidth / 2;
            foot1YPoints[3] = screenPoint.y + unitWidth * 3 / 2;
        }

        foot1 = new Polygon(foot1XPoints, foot1YPoints, 4);
        g2.fillPolygon(foot1);

        foot2XPoints[0] = screenPoint.x + unitWidth * 5 / 16 - foot1Offset;
        foot2XPoints[1] = screenPoint.x + unitWidth * 6 / 16;
        foot2XPoints[2] = screenPoint.x + unitWidth * 10 / 16;
        foot2XPoints[3] = screenPoint.x + unitWidth * 11 / 16 - foot1Offset;

        if (jumpingCount == jumpMax)
        {
            foot2YPoints[0] = background.hillGround(foot2XPoints[0] + playerPoint.x);
            foot2YPoints[1] = screenPoint.y + unitWidth / 2;
            foot2YPoints[2] = screenPoint.y + unitWidth / 2;
            foot2YPoints[3] = background.hillGround(foot2XPoints[3] + playerPoint.x);
        } else
        {
            foot2YPoints[0] = screenPoint.y + unitWidth * 3 / 2;
            foot2YPoints[1] = screenPoint.y + unitWidth / 2;
            foot2YPoints[2] = screenPoint.y + unitWidth / 2;
            foot2YPoints[3] = screenPoint.y + unitWidth * 3 / 2;
        }

        foot2 = new Polygon(foot2XPoints, foot2YPoints, 4);
        g2.fillPolygon(foot2);
    }

    protected void paintSelfHealthBar(Graphics2D g2)
    {
        g2.setColor(Color.red);
        g2.fillRect(screenPoint.x, screenPoint.y - unitHeight - 10, (int) (((double) health / maxHealth) * unitWidth), 5);
    }

    protected abstract boolean shouldMove();

    protected abstract void chooseMove();

    protected void move()
    {
        if (shouldMoveLeft)
        {
            unitPoint.x -= speed;
            moveFeet();
        }

        if (shouldMoveRight)
        {
            unitPoint.x += speed;
            moveFeet();
        }

        if (shouldMoveUp)
        {
            jump();
        } else
        {
            jumpingCount = 0;
        }
    }

    private void moveFeet()
    {
        if (foot1Offset < -15)
        {
            foot1Forward = true;
        }

        if (foot1Offset > 15)
        {
            foot1Forward = false;
        }

        if (foot1Forward)
        {
            foot1Offset += speed / 2;
        } else
        {
            foot1Offset -= speed / 2;
        }
    }

    private void jump()
    {
        if (jumpingCount > 0)
        {
            unitPoint.y -= jumpingCount;
            jumpingCount -= 1;
        }
    }

    private void handleGravity()
    {
        if (shouldMoveDown)
        {
            unitPoint.y = background.hillGround(unitPoint.x + unitWidth / 2 + Main.screenWidth / 2) - unitHeight - 10;
            jumpingCount = jumpMax;
        } else if (unitPoint.y >= background.hillGround(unitPoint.x + unitWidth / 2 + Main.screenWidth / 2) - unitHeight - 30)
        {
            unitPoint.y = background.hillGround(unitPoint.x + unitWidth / 2 + Main.screenWidth / 2) - unitHeight - 20;
            jumpingCount = jumpMax;
        } else
        {
            unitPoint.y += 10;
        }
    }

    protected abstract boolean shouldAttack();

    protected void calculateDistance()
    {
        xDistanceToTarget = targetPoint.x - unitPoint.x;
        distanceToTarget = (int) Math.sqrt((Math.pow(xDistanceToTarget, 2) + Math.pow(targetPoint.y - unitPoint.y, 2)));
    }

    protected void die()
    {
        projectileHolder.makeLargeBlood(this, unitPoint, isPlayer, 50);
        isDead = true;
    }

    public void moveAway(Rectangle rect)
    {
        if (rect.x > boundingBox.x)
        {
            unitPoint.x -= speed;
        } else if (rect.x < boundingBox.x)
        {
            unitPoint.x += speed;
        } else
        {
            unitPoint.x += (Math.random() * 4 - 2) * speed;
        }
    }

    public void damageSelfFace(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeLargeBlood(this, hitByBulletPoint, isPlayer, 20);
        health -= damage * 3;
        shotHead = true;
        if (health <= 0)
        {
            die();
        }
    }

    public void damageSelf(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeBlood(this, hitByBulletPoint, isPlayer, 5);
        health -= damage;
        if (health <= 0)
        {
            die();
        }
    }

    public double getHealth()
    {
        return health;
    }
    
    public void setHealth(double health)
    {
        this.health = health;
    }

    public void setIsFacingLeft(boolean isFacingLeft)
    {
        this.isFacingLeft = isFacingLeft;
    }
}
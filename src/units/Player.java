package units;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.Background;
import main.Main;
import main.ProjectileHolder;
import weapons.GenericWeapon;
import weapons.Knife;
import weapons.NukeStrike;
import weapons.Pistol;

/**
 *
 * @author TJ
 */
public class Player extends GenericUnit
{
    protected ArrayList<GenericWeapon> weaponList = new ArrayList<GenericWeapon>();
    protected int newWeaponIndex = 1;
    protected int weaponIndex = 1;
    private double mouseWheelRotation = 0;
    private int money = 0;
    private int score = 0;
    private boolean keyLeftPressed = false;
    private boolean keyRightPressed = false;
    private boolean keyUpPressed = false;
    private boolean keyDownPressed = false;
    private boolean leftClicked = false;
    private boolean keyEPressed = false;
    private boolean keyQPressed = false;
    private boolean keySpacePressed = false;

    public Player(Background background, Point spawn, GenericUnit target, ProjectileHolder projectileHolder, boolean isPlayer)
    {
        super(background, spawn, target, projectileHolder, isPlayer);
        speed = 10;
        range = 10;
        health = 200;
        weaponList.add(new Knife(this, null, projectileHolder, true));
        weaponList.add(new Pistol(this, null, projectileHolder, true));
        weapon = weaponList.get(newWeaponIndex);
        color1 = new Color(105, 105, 105);
        color2 = new Color(131, 131, 131);
        maxHealth = health;
    }

    @Override
    public void run()
    {
        swap();
        super.run();
    }

    @Override
    protected boolean shouldMove()
    {
        return ((unitPoint.x > -Main.screenWidth * 3 / 2 || !shouldMoveLeft) && (unitPoint.x < Main.screenWidth * 3 / 2 || !shouldMoveRight)) && !isDying;
    }

    @Override
    protected void chooseMove()
    {
        if (!isDying)
        {
            shouldMoveLeft = keyLeftPressed;
            shouldMoveRight = keyRightPressed;
            shouldMoveUp = keyUpPressed;
            shouldMoveDown = keyDownPressed;
        }
        if (shouldMoveDown)
        {
            speed = 5;
        } else
        {
            speed = 10;
        }
    }

    @Override
    protected boolean shouldAttack()
    {
        return leftClicked;
    }

    @Override
    public void moveAway(Rectangle rect)
    {
    }

    private void swap()
    {
        if (mouseWheelRotation > 0)
        {
            weaponIndex++;
            mouseWheelRotation = 0;
        } else if (mouseWheelRotation < 0)
        {
            weaponIndex--;
            mouseWheelRotation = 0;
        } else if (keyQPressed)
        {
            weaponIndex--;
            keyQPressed = false;
        } else if (keyEPressed)
        {
            weaponIndex++;
            keyEPressed = false;
        }
        if (keySpacePressed)
        {
            if (newWeaponIndex != 0)
            {
                weaponIndex = newWeaponIndex;
                weapon.setShouldAttack(false);
            } else
            {
                weapon.setShouldAttack(true);
            }
            newWeaponIndex = 0;
        } else
        {
            newWeaponIndex = weaponIndex;
        }
        int index = Math.abs(newWeaponIndex % weaponList.size());
        weapon = weaponList.get(index);
    }

    @Override
    public void damageSelfFace(int damage, Point hitByBulletPoint)
    {
        projectileHolder.makeLargeBlood(this, hitByBulletPoint, isPlayer, 20);
        health -= damage;
        shotHead = true;
        if (health <= 0)
        {
            die();
        }
    }

    public void setKeyDownPressed(boolean keyDownPressed)
    {
        this.keyDownPressed = keyDownPressed;
    }

    public void setKeyLeftPressed(boolean keyLeftPressed)
    {
        this.keyLeftPressed = keyLeftPressed;
    }

    public void setKeyRightPressed(boolean keyRightPressed)
    {
        this.keyRightPressed = keyRightPressed;
    }

    public void setKeyUpPressed(boolean keyUpPressed)
    {
        this.keyUpPressed = keyUpPressed;
    }

    public void setLeftClicked(boolean leftClicked)
    {
        this.leftClicked = leftClicked;
    }

    public void setMouseWheelRotation(double mouseWheelRotation)
    {
        this.mouseWheelRotation = mouseWheelRotation;
    }

    public void setTargetPoint(Point targetPoint)
    {
        this.targetPoint = targetPoint;
    }

    public void setKeyQPressed(boolean keyQPressed)
    {
        this.keyQPressed = keyQPressed;
    }

    public void setKeyEPressed(boolean keyEPressed)
    {
        this.keyEPressed = keyEPressed;
    }

    public void setKeySpacePressed(boolean keySpacePressed)
    {
        this.keySpacePressed = keySpacePressed;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public boolean getKeyDownPressed()
    {
        return keyDownPressed;
    }

    public void removeNuke()
    {
        for (int i = 0; i < weaponList.size(); ++i)
        {
            if (weaponList.get(i) instanceof NukeStrike)
            {
                weaponList.remove(i);
                return;
            }
        }
    }

	public GenericWeapon getWeapon()
	{
		return weapon;
	}

	public double getMaxHealth()
	{
		return maxHealth;
	}

	public ArrayList<GenericWeapon> getWeaponList()
	{
		return weaponList;
	}
}

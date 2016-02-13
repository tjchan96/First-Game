package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import weapons.*;

public class Shop
{
    private Main main = null;
    private boolean open = false;
    private boolean hasNuke = false;
    private Color color1 = new Color(77, 77, 77);
    private Color color2 = new Color(171, 171, 171);
    private ArrayList<GenericWeapon> weapons = new ArrayList<GenericWeapon>();
    private ArrayList<Rectangle> weaponRects = new ArrayList<Rectangle>();
    private ArrayList<Boolean> weaponAvailable = new ArrayList<Boolean>();
    private Rectangle mainWindow = new Rectangle(100, 100, Main.screenWidth - 200, Main.screenHeight - 200);
    private Rectangle precision = new Rectangle(mainWindow.x + 10, mainWindow.y + 10, mainWindow.width / 2 - 20, mainWindow.height - 20);
    private Rectangle automatic = new Rectangle(mainWindow.x + mainWindow.width / 2 + 10, mainWindow.y + 10, mainWindow.width / 2 - 20, mainWindow.height - 20);
    private Rectangle magnum = new Rectangle(precision.x + precision.width / 4 + 10, precision.y + 10, precision.width / 2 - 10, precision.height / 4 - 10);
    private Rectangle rifle = new Rectangle(precision.x + 10, precision.y + precision.height / 4 + 10, precision.width / 2 - 10, precision.height / 4 - 10);
    private Rectangle longRifle = new Rectangle(precision.x + 10, precision.y + precision.height * 2 / 4 + 10, precision.width / 2 - 10, precision.height / 4 - 10);
    private Rectangle sniperRifle = new Rectangle(precision.x + 10, precision.y + precision.height * 3 / 4 + 10, precision.width / 2 - 10, precision.height / 4 - 20);
    private Rectangle carbine = new Rectangle(precision.x + precision.width / 2 + 10, precision.y + precision.height / 4 + 10, precision.width / 2 - 20, precision.height / 4 - 10);
    private Rectangle rangedCarbine = new Rectangle(precision.x + precision.width / 2 + 10, precision.y + precision.height * 2 / 4 + 10, precision.width / 2 - 20, precision.height / 4 - 10);
    private Rectangle nuclearStrike = new Rectangle(precision.x + precision.width / 2 + 10, precision.y + precision.height * 3 / 4 + 10, precision.width / 2 - 20, precision.height / 4 - 20);
    private Rectangle smg = new Rectangle(automatic.x + automatic.width / 4 + 10, automatic.y + 10, automatic.width / 2 - 10, automatic.height / 4 - 10);
    private Rectangle assaultRifle = new Rectangle(automatic.x + 10, automatic.y + automatic.height / 4 + 10, automatic.width / 2 - 10, automatic.height / 4 - 10);
    private Rectangle lmg = new Rectangle(automatic.x + 10, automatic.y + automatic.height * 2 / 4 + 10, automatic.width / 2 - 10, automatic.height / 4 - 10);
    private Rectangle flamethrower = new Rectangle(automatic.x + 10, automatic.y + automatic.height * 3 / 4 + 10, automatic.width / 2 - 10, automatic.height / 4 - 20);
    private Rectangle pumpShotgun = new Rectangle(automatic.x + automatic.width / 2 + 10, automatic.y + automatic.height / 4 + 10, automatic.width / 2 - 20, automatic.height / 4 - 10);
    private Rectangle autoShotgun = new Rectangle(automatic.x + automatic.width / 2 + 10, automatic.y + automatic.height * 2 / 4 + 10, automatic.width / 2 - 20, automatic.height / 4 - 10);
    private Rectangle grenadeLauncher = new Rectangle(automatic.x + automatic.width / 2 + 10, automatic.y + automatic.height * 3 / 4 + 10, automatic.width / 2 - 20, automatic.height / 4 - 20);

    public Shop(Main main)
    {
        this.main = main;
        weaponRects.add(magnum);
        weaponRects.add(rifle);
        weaponRects.add(longRifle);
        weaponRects.add(sniperRifle);
        weaponRects.add(carbine);
        weaponRects.add(rangedCarbine);
        weaponRects.add(nuclearStrike);
        weaponRects.add(smg);
        weaponRects.add(assaultRifle);
        weaponRects.add(lmg);
        weaponRects.add(flamethrower);
        weaponRects.add(pumpShotgun);
        weaponRects.add(autoShotgun);
        weaponRects.add(grenadeLauncher);
        weapons.add(new Magnum(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new Rifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new LongRifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new SniperRifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new Carbine(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new RangedCarbine(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new NukeStrike(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new Smg(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new AssaultRifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new Lmg(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new FlameThrower(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new PumpShotgun(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new AutoShotgun(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        weapons.add(new GrenadeLauncher(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
        for (int i = 0; i < 14; i++)
        {
            weaponAvailable.add(true);
        }
        for (int i = 0; i < weapons.size(); i++)
        {
            weapons.get(i).setHolderPoint(new Point((int) (weaponRects.get(i).getX() - mainWindow.width / 2 + weaponRects.get(i).width / 2 - 50), (int) (weaponRects.get(i).getY()) + weaponRects.get(i).height / 2));
            weapons.get(i).setStraightAngle(true);
        }
    }

    public void run()
    {
        Main.setRunShop(open);
    }

    public void paintSelf(Graphics2D g2)
    {
        if (open)
        {
            g2.setColor(color2);
            g2.fill(mainWindow);
            g2.setColor(color1);
            g2.fill(precision);
            g2.fill(automatic);
            g2.setFont(new Font("stencil", Font.PLAIN, 12));
            g2.setColor(color2);
            g2.drawString("*Must buy all", precision.x + 10, precision.y + 25);
            g2.drawString("guns above", precision.x + 10, precision.y + 40);
            g2.drawString("the gun you", precision.x + 10, precision.y + 55);
            g2.drawString("wish to", precision.x + 10, precision.y + 70);
            g2.drawString("purchase", precision.x + 10, precision.y + 85);
            g2.setFont(new Font("stencil", Font.PLAIN, 16));
            for (int i = 0; i < weaponRects.size(); i++)
            {
                g2.setColor(color2);
                g2.fill(weaponRects.get(i));
                g2.setColor(Color.black);
                weapons.get(i).paintSelf(g2, new Point(), new Color(131, 131, 131));
                g2.setColor(Color.black);
                g2.drawString(weapons.get(i).getClass().getName().substring(11), weaponRects.get(i).x + g2.getFont().getSize(), weaponRects.get(i).y + g2.getFont().getSize());
                g2.setColor(Color.black);
                g2.drawString(weapons.get(i).getCost() + "", weaponRects.get(i).x + g2.getFont().getSize(), weaponRects.get(i).y + g2.getFont().getSize() * 2);
            }
            g2.setFont(new Font("stencil", Font.PLAIN, 30));
            for (int i = 0; i < weaponRects.size(); i++)
            {
                if (!weaponAvailable.get(i))
                {
                    g2.setColor(Color.black);
                    g2.drawString("Bought!", weaponRects.get(i).x + g2.getFont().getSize(), weaponRects.get(i).y + g2.getFont().getSize() * 2);
                }
            }
            hasNuke = false;
            for (int i = 0; i < main.getUnitHolder().getPlayer().getWeaponList().size(); i++)
            {
                if (main.getUnitHolder().getPlayer().getWeaponList().get(i) instanceof NukeStrike)
                {
                    hasNuke = true;
                }
            }
            if (!hasNuke)
            {
                weaponAvailable.set(6, true);
            }
        }
    }

    public void buy(MouseEvent me)
    {
        if (open)
        {
            for (int i = 0; i < weaponRects.size(); i++)
            {
                if (weaponRects.get(i).contains(me.getPoint()) && weaponAvailable.get(i) && (weapons.get(i).getRequirement() == -1 || !weaponAvailable.get(weapons.get(i).getRequirement())) && main.getUnitHolder().getPlayer().getMoney() - weapons.get(i).getCost() >= -1)
                {
                    weaponAvailable.set(i, false);
                    main.getUnitHolder().getPlayer().setMoney(main.getUnitHolder().getPlayer().getMoney() - weapons.get(i).getCost());
                    if (weapons.get(i) instanceof Magnum)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new Magnum(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof Rifle)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new Rifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof LongRifle)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new LongRifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof SniperRifle)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new SniperRifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof Carbine)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new Carbine(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof RangedCarbine)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new RangedCarbine(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof NukeStrike)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new NukeStrike(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof Smg)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new Smg(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof AssaultRifle)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new AssaultRifle(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof Lmg)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new Lmg(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof FlameThrower)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new FlameThrower(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof PumpShotgun)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new PumpShotgun(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof AutoShotgun)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new AutoShotgun(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                    if (weapons.get(i) instanceof GrenadeLauncher)
                    {
                        main.getUnitHolder().getPlayer().getWeaponList().add(new GrenadeLauncher(main.getUnitHolder().getPlayer(), null, main.getProjectileHolder(), true));
                    }
                } else
                {
                }
            }
        }
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }
}

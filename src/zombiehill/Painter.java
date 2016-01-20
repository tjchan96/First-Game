package zombiehill;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author TJ
 */
public class Painter extends Canvas implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener
{
    public Main main = null;
    private BufferedImage offscreen = null;
    private Rectangle box = null;
    private Graphics offgc = null;

    public Painter(Main mainArg)
    {
        main = mainArg;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if (!Main.isTitleScreenRun())
        {
            main.getBackground().paintSelf(g2, main.getUnitHolder().getPlayer().unitPoint);
            main.getUnitHolder().paintSelfUnits(g2);
            main.getProjectileHolder().paintSelfProjectiles(g2);
            main.getShop().paintSelf(g2);
        } else
        {
            main.getTitleScreen().paintSelf(g2, main.getUnitHolder().getPlayer().unitPoint);
        }
    }

    @Override
    public void update(Graphics g)
    {
        box = g.getClipRect();

        offscreen = new BufferedImage(box.width, box.height, BufferedImage.TYPE_INT_RGB);
        offgc = offscreen.getGraphics();
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, box.width, box.height);
        offgc.setColor(getForeground());
        offgc.translate(-box.x, -box.y);
        paint(offgc);
        g.drawImage(offscreen, box.x, box.y, this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        Player player = main.getUnitHolder().getPlayer();
        if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_F)
        {
            main.getShop().setOpen(!main.getShop().isOpen());
        }
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            player.setKeyLeftPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            player.setKeyRightPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            player.setKeyUpPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            player.setKeyDownPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            player.setKeyQPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_E)
        {
            player.setKeyEPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            player.setKeySpacePressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        Player player = main.getUnitHolder().getPlayer();
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            player.setKeyLeftPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            player.setKeyRightPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            player.setKeyUpPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            player.setKeyDownPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            player.setKeyQPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_E)
        {
            player.setKeyEPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            player.setKeySpacePressed(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
    }

    @Override
    public void mouseEntered(MouseEvent me)
    {
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        if (me.getButton() == MouseEvent.BUTTON1)
        {
            Player player = main.getUnitHolder().getPlayer();
            player.setLeftClicked(true);
            main.getShop().buy(me);
        }
        if (Main.isTitleScreenRun() == true)
        {
            main.getTitleScreen().run(me);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        if (me.getButton() == MouseEvent.BUTTON1)
        {
            Player player = main.getUnitHolder().getPlayer();
            player.setLeftClicked(false);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
        {
            main.getUnitHolder().getPlayer().setMouseWheelRotation(e.getWheelRotation());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        main.getUnitHolder().getPlayer().setTargetPoint(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        main.getUnitHolder().getPlayer().setTargetPoint(e.getPoint());
    }
}

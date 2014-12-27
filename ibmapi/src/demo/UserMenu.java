package demo;

import gui.iBankMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class UserMenu extends iBankMenu {

    protected JFrame parentFrame;

    protected KeyboardFocusManager manager;
    protected KeyEventPostProcessor postProcessor;

    public UserMenu(JFrame parent) {
        super();
        parentFrame = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(postProcessor = new KeyEventPostProcessor()
                {
                    @Override
                    public boolean postProcessKeyEvent(KeyEvent e)
                    {
                        if (KeyEvent.VK_ESCAPE == e.getKeyCode())
                        {
                            returnMain();
                        }
                        return true;
                    }
                }
        );
    }

    protected void returnMain()
    {
        manager.removeKeyEventPostProcessor(postProcessor);
        dispose();
        setVisible(false);
        parentFrame.setVisible(true);
    }

}

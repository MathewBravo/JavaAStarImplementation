package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    DisplayNode displayNode;

    public InputHandler(DisplayNode displayNode){
        this.displayNode = displayNode;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            displayNode.assessNode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

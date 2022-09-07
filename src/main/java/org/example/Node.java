package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    Node parentNode;
    int col, row;

    boolean startNode, goalNode;

    boolean blocked, clear, hasChecked;

    int gCost, hCost, fCost;


    public Node(int col, int row){
        this.col = col;
        this.row = row;
        this.setFocusable(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        addActionListener(this);
    }

    public void setNodeAsStart(){
        setBackground(Color.magenta);
        setForeground(Color.WHITE);
        startNode = true;
    }

    public void setNodeAsGoal(){
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
        goalNode = true;
    }

    public void setAsBlocked(){
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        blocked = true;
    }

    public void setAsClear(){
        clear = true;
    }

    public void setHasChecked(){
        if(!startNode && !goalNode){
            hasChecked = true;
            setBackground(Color.PINK);
            setForeground(Color.BLACK);
        }
    }

    public void showPath(){
        setBackground(Color.YELLOW);
        setForeground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setAsBlocked();
    }
}

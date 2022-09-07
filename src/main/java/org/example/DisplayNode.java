package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayNode extends JPanel {
    final int cols=35, rows=25;
    final int nodeCount = 150;
    final int screenWidth = nodeCount * cols;
    final int screenHeight = nodeCount * rows;

    ArrayList<Node> openNodes = new ArrayList<>();
    ArrayList<Node> checkedNodes = new ArrayList<>();
    Node[][] nodes = new Node[cols][rows];
    Node startNode, goalNode;
    Node current;
    int detectionAttempts = 0;
    boolean finished;
    public DisplayNode(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(rows, cols));
        this.addKeyListener(new InputHandler(this));
        this.setFocusable(true);

        int currentCol = 0;
        int currentRow = 0;
        while (currentCol < cols && currentRow < rows){
            nodes[currentCol][currentRow] = new Node(currentCol, currentRow);
            this.add(nodes[currentCol][currentRow]);

            currentCol++;
            if(currentCol == cols){
                currentCol = 0;
                currentRow++;
            }
        }

        setStartNode(2, 8);
        setGoalNode(32, 21);


        setBlockedNodes(12, 3);
        setBlockedNodes(11, 4);
        setBlockedNodes(11, 5);
        setBlockedNodes(12, 6);
        setBlockedNodes(13, 7);
        setBlockedNodes(14, 7);
        setBlockedNodes(15, 8);
        setBlockedNodes(15, 9);
        setBlockedNodes(15, 10);
        setBlockedNodes(15, 11);
        setBlockedNodes(15, 12);
        setBlockedNodes(15, 13);
        setBlockedNodes(15, 14);
        setBlockedNodes(15, 15);
        setBlockedNodes(14, 16);
        setBlockedNodes(14, 17);
        setBlockedNodes(14, 18);
        setBlockedNodes(14, 19);
        setBlockedNodes(14, 20);
        setBlockedNodes(14, 21);
        setBlockedNodes(14, 22);
        setBlockedNodes(14, 23);
        setBlockedNodes(14, 24);

        setNodeCost();
    }

    private void setStartNode(int col, int row){
        nodes[col][row].setNodeAsStart();
        startNode = nodes[col][row];
        current = startNode;
    }
    private void setGoalNode(int col, int row){
        nodes[col][row].setNodeAsGoal();
        goalNode = nodes[col][row];
    }

    private void setBlockedNodes(int col, int row){
        nodes[col][row].setAsBlocked();
    }

    private void setNodeCost(){
        int col = 0, row = 0;

        while(col < cols && row < rows){
            calculateNodeCost(nodes[col][row]);
            col++;
            if(col == cols){
                col = 0;
                row++;
            }
        }
    }

    private void calculateNodeCost(Node node){
        int dist_X, dist_Y;
        dist_X = Math.abs(node.col - startNode.col);
        dist_Y = Math.abs(node.row - startNode.row);
        node.gCost = dist_X + dist_Y;

        dist_X = Math.abs(node.col - goalNode.col);
        dist_Y = Math.abs(node.row - goalNode.row);
        node.hCost = dist_X + dist_Y;

        node.fCost = node.gCost + node.hCost;
    }

    public void assessNode(){
        while(finished == false && detectionAttempts < 10000){
            detectionAttempts++;
            int col = current.col;
            int row = current.row;

            current.setHasChecked();
            checkedNodes.add(current);
            openNodes.remove(current);

            if(row - 1 >= 0) openNode(nodes[col][row-1]);
            if(col - 1 >= 0) openNode(nodes[col-1][row]);
            if(row + 1 < rows) openNode(nodes[col][row+1]);
            if(col + 1 < cols) openNode(nodes[col+1][row]);

            int bestNodeIndex = 0;
            int lowestFCost = 10000;

            for(int i = 0; i < openNodes.size(); i++ ){
                if(openNodes.get(i).fCost < lowestFCost){
                    bestNodeIndex = i;
                    lowestFCost = openNodes.get(i).fCost;
                }
                else if (openNodes.get(i).fCost == lowestFCost) {
                    if(openNodes.get(i).gCost < openNodes.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            current = openNodes.get(bestNodeIndex);

            if(current == goalNode) {
                finished = true;
                createPath();
            }
        }
    }
    public void openNode(Node node){
        if(!node.clear && !node.hasChecked && !node.blocked) {
            node.setAsClear();
            node.parentNode = current;
            openNodes.add(node);
        }
    }

    private void createPath(){
        Node current = goalNode;
        while(current != startNode){
            current = current.parentNode;
            if(current != startNode){
                current.showPath();
            }
        }
    }

}

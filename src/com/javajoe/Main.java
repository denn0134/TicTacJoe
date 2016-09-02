package com.javajoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private JPanel pnlBoard;
    private JPanel pnlControls;
    private JButton[][] btnCells = new JButton[3][3];
    private JButton btnEndTurn;
    private JButton btnReset;

    public Main(){
        super("Tic-Tac-Toe");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        pnlBoard = new JPanel();
        pnlBoard.setLayout(new GridLayout(3, 3));
        pnlBoard.setPreferredSize(new Dimension(600, 600));
        c.add(pnlBoard, BorderLayout.NORTH);

        pnlControls = new JPanel();
        pnlControls.setLayout(new BorderLayout());
        pnlControls.setSize(600, 200);
        c.add(pnlControls, BorderLayout.SOUTH);

        for(int i = 0; i < btnCells.length; i++){
            for(int j = 0; j < btnCells[0].length; j++){
                JButton button = new JButton("X");
                button.setPreferredSize(new Dimension(200, 200));
                btnCells[i][j] = button;
                pnlBoard.add(btnCells[i][j]);
            }//end for j
        }//end for i

        btnEndTurn = new JButton("End Turn");
        btnEndTurn.setPreferredSize(new Dimension(600,75));
        pnlControls.add(btnEndTurn, BorderLayout.NORTH);

        btnReset = new JButton("Reset Game");
        btnReset.setPreferredSize(new Dimension(600,75));
        pnlControls.add(btnReset, BorderLayout.SOUTH);

        setSize(600, 800);
        show();
    }//end Main

    public static void main(String[] args) {
	    Main app = new Main();
        app.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );//end addwindowListener
    }
}

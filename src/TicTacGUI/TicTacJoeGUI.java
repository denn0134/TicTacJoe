package TicTacGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import TicTacEngine.*;

/**
 * Created by spdennis on 10/4/2016.
 */
public class TicTacJoeGUI extends JFrame {
    private JPanel pnlBoard;
    private JPanel pnlControls;
    private CellButton[][] btnCells = new CellButton[3][3];
    private JButton btnReset;

    private TicTacToe engine;

    private ImageIcon xIcon = new ImageIcon("D:\\Dev\\JavaJoe\\TicTacJoe\\images\\X.png");
    private ImageIcon oIcon = new ImageIcon("D:\\Dev\\JavaJoe\\TicTacJoe\\images\\O.png");
    private ImageIcon blankIcon = new ImageIcon("D:\\Dev\\JavaJoe\\TicTacJoe\\images\\Blank.png");

    public TicTacJoeGUI() {
        super("Tic-Tac-Toe");

        //create the game engine
        engine = new TicTacToe();

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

        for (int i = 0; i < btnCells.length; i++) {
            for (int j = 0; j < btnCells[0].length; j++) {
                CellButton button = new CellButton(blankIcon);
                button.column = i;
                button.row = j;
                button.setPreferredSize(new Dimension(200, 200));
                button.addActionListener(new CellButtonHandeler());

                btnCells[i][j] = button;
                pnlBoard.add(btnCells[i][j]);
            }//end for j
        }//end for i

        btnReset = new JButton("Reset Game");
        btnReset.setPreferredSize(new Dimension(600, 75));
        btnReset.addActionListener(new ResetHandler());
        pnlControls.add(btnReset, BorderLayout.SOUTH);

        setSize(600, 800);
        show();
    }//end Main

    public static void main(String[] args) {
        TicTacJoeGUI app = new TicTacJoeGUI();
        app.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );//end addWindowListener
    }

    //gets the icon based on a cell state
    private ImageIcon getIconFromCellSate(TicTacToe.CellState state) {
        switch (state) {
            case X:
                return xIcon;
            case O:
                return oIcon;
            case BLANK:
                return blankIcon;
            default:
                return blankIcon;
        }//end switch
    }

    //refreshes the state of the GUI from the game engine
    private void refreshGUI() {
        for (int i = 0; i < btnCells.length; i++) {
            for (int j = 0; j < btnCells[i].length; j++) {
                TicTacToe.CellState state = engine.getCell(i, j);
                //btnCells[i][j].setIcon(getIconFromCellSate(state));
                btnCells[i][j].setCurrentIcon(getIconFromCellSate(state));
            }//end for j
        }//end for i
    }

    //button handler inner class for handling the reset button
    private class ResetHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            engine.resetGame();
            refreshGUI();
        }
    }

    private class CellButtonHandeler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CellButton button;
            button = (CellButton) e.getSource();
            try {
                engine.setCell(button.column, button.row);
                refreshGUI();
                TicTacToe.VictoryState victoryState = engine.checkVictory();
                if(victoryState == TicTacToe.VictoryState.XWINS)
                    JOptionPane.showMessageDialog(null, "X has won");
                else if(victoryState == TicTacToe.VictoryState.OWINS)
                    JOptionPane.showMessageDialog(null, "O has won");
                else if(victoryState == TicTacToe.VictoryState.DRAW)
                    JOptionPane.showMessageDialog(null, "DRAW");

            }
            catch (TicTacExeption tte){
                JOptionPane.showMessageDialog(null, tte.getMessage());
            }

        }
    }
}


package TicTacGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by spdennis on 10/4/2016.
 */
public class CellButton extends JButton
{
    private ImageIcon _currentIcon;
    public int column;
    public int row;

    public CellButton(ImageIcon startingIcon){
        super();

        this.setIcon(startingIcon);
        _currentIcon = startingIcon;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JButton btn = (JButton) e.getComponent();
                setButtonIcon(btn);
            }
        });
    }

    private void setButtonIcon(JButton button){
        Dimension size = button.getSize();
        java.awt.Insets insets = button.getInsets();
        size.width -= insets.left + insets.right;
        size.height -= insets.top + insets.bottom;

        if(size.width > size.height)
            size.width = -1;
        else
            size.height = -1;

        Image scaled = _currentIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaled));
    }

    public ImageIcon getCurrentIcon(){
        return _currentIcon;
    }

    public void setCurrentIcon(ImageIcon value){
        _currentIcon = value;
        setButtonIcon(this);
    }
}

package logic;

import java.awt.*;
import javax.swing.*;

public class Score extends JComponent
{
    private JLabel p1, turn, p2;
    private Font afont = Logic.getFont().deriveFont((float)(Logic.getWidth() / 50));

    public Score()
    {
        setLayout(new BorderLayout());
        p1 = new JLabel("Player 1 score: " + Logic.getP1_score());
        p2 = new JLabel("Player 2 score: " + Logic.getP2_score());
        turn = new JLabel("Turn: Player 1");
        setJLabelFontColor(afont, p1, p2, turn);
        turn.setHorizontalAlignment(JLabel.CENTER);
        add(p1, BorderLayout.WEST);
        add(p2, BorderLayout.EAST);
        add(turn, BorderLayout.CENTER);
    }

    private void setJLabelFontColor(Font f, JLabel... labels)
    {
        for(JLabel l : labels)
        {
            l.setForeground(new Color(63, 100, 152));
            l.setFont(f);
        }
    }

    public void refresh()
    {
       p1.setText("Player 1 score: " + Logic.getP1_score());
       p2.setText("Player 2 score: " + Logic.getP2_score());
       if(Logic.getP1Turn())
       {
           turn.setText("Turn: Player 1");
       }
       else
       {
           turn.setText("Turn: Player 2");
       }
       repaint();
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Logic.getWidth() - 30, Logic.getHeight() - Logic.getPanelt_height() - 10);
    }
}

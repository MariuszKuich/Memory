package game;

import objects.*;
import logic.*;

import javax.swing.*;
import java.awt.*;

public class MemFrame extends JFrame
{
   private final int w;
   private final int h;

   public MemFrame()
   {
      setIconImage(new ImageIcon(Logic.getPath("back_s.png")).getImage());
      w = Logic.getWidth();
      h = Logic.getHeight();
      setBounds(30, 30, w, h);
      setTitle("Memory by Mariusz v 1.2.0");
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());

      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      add(panel1, BorderLayout.CENTER);
      add(panel2, BorderLayout.PAGE_END);
      panel1.setPreferredSize(new Dimension(Logic.getWidth(), Logic.getPanelt_height()));
      panel2.setPreferredSize(new Dimension(Logic.getWidth(), Logic.getHeight() - Logic.getPanelt_height()));
      panel1.setBackground(new Color(231, 186, 129));
      panel2.setBackground(new Color(243, 247, 250));

      Score board = new Score();
      panel1.add(new CardsHandling(board));
      panel2.add(board);
}
}

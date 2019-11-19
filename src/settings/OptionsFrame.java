package settings;

import game.*;
import logic.Logic;

import java.awt.*;
import javax.swing.*;

public class OptionsFrame extends JFrame
{
    private Font afont = new Font("Sans-Serif", Font.PLAIN, 20);
    public OptionsFrame()
    {
        setIconImage(new ImageIcon(Logic.getPath("back_s.png")).getImage());
        setTitle("Memory - Options");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50,  250, 300);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panel_t = new JPanel();
        JPanel panel_b = new JPanel();
        add(panel_t, BorderLayout.PAGE_START);
        add(panel_b, BorderLayout.CENTER);

        JLabel banner = new JLabel("Choose resolution:");
        banner.setFont(afont);
        panel_t.add(banner);

        panel_b.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 20));
        JButton b1 = makeButton(840, 630);
        JButton b2 = makeButton(1170, 860);
        JButton b3 = makeButton(1500, 1200);
        b1.addActionListener(event ->
        {
            setVisible(false);
            Logic.setCard_w(70);
            Logic.setWidth(840);
            Logic.setHeight(630);
            Logic.setPanelt_height(Logic.getHeight() - 60);
            new MemFrame();
        });
        b2.addActionListener(event ->
        {
            setVisible(false);
            Logic.setCard_w(100);
            Logic.setWidth(1170);
            Logic.setHeight(860);
            Logic.setPanelt_height(Logic.getHeight() - 80);
            new MemFrame();
        });
        b3.addActionListener(event ->
        {
            setVisible(false);
            Logic.setCard_w(130);
            Logic.setWidth(1500);
            Logic.setHeight(1200);
            Logic.setPanelt_height(Logic.getHeight() - 100);
            new MemFrame();
        });
        panel_b.add(b1);
        panel_b.add(b2);
        panel_b.add(b3);
    }

    private JButton makeButton(int w, int h)
    {
        JButton b = new JButton(w + " x " + h);
        b.setPreferredSize(new Dimension(180, 50));
        b.setFont(afont.deriveFont(15.0F));
        return b;
    }

}

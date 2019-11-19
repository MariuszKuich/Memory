package objects;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.Timer;

public class CardsHandling extends JComponent
{
    private Score board;
    private boolean active = true;
    private Card[] cards = new Card[70];
    private Card[] pickedCards = new Card[2];
    public CardsHandling(Score s)
    {
        board = s;
        Scanner pictures = null;
        try
        {
            pictures = new Scanner(new File(Logic.getPath("paths.txt")));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("paths.txt file not found.");
            System.exit(0);
        }
        String path = null;
        int xs[] = new int [70];
        int ys[] = new int [70];
        int margin = 10;
        int spacing = 5;
        int x_coords = margin;
        int y_coords = 0;
        for(int i = 0 ; i < 70 ; i++)
        {
            if(i % 2 == 0) path = Logic.getPath(pictures.nextLine());
            if(x_coords > Logic.getWidth() - Logic.getCard_w() - margin)
            {
                x_coords = margin;
                y_coords += Logic.getCard_w() + spacing;
            }
            cards[i] = new Card(path, i);
            xs[i] = x_coords;
            ys[i] = y_coords;
            x_coords += Logic.getCard_w()+ spacing;
        }
        locationSetting(xs, ys);
        addMouseMotionListener(new MouseMotionHandler());
        addMouseListener(new MouseHandler());
    }

    public void locationSetting(int xs[], int ys[])
    {
        int i = 69;
        int j = 0;
        while(i >= 0)
        {
            int rand =(int)(Math.random() * (i + 1));
            cards[j].setLocation(new Point(xs[rand], ys[rand]));
            xs[rand] = xs[i];
            ys[rand] = ys[i];
            i--;
            j++;
        }
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(new ImageIcon(Logic.getPath("background.jpg")).getImage() , 0, 0, null);
        if(Logic.getCards_out() != 70)
        {
            for(Card c : cards)
            {
                if(c.getStatus())
                {
                    g.drawImage(c.getCurrentImage(), c.getPoint().x, c.getPoint().y, Logic.getCard_w(), Logic.getCard_w(), null);
                }
                else continue;
            }
        }
        else
        {
            Font mes_font = Logic.getFont().deriveFont((float)(Logic.getWidth() / 20));
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(mes_font);
            g2.setColor(new Color(63, 100, 152));
            String message;
            if(Logic.getP1_score() > Logic.getP2_score()) message = "PLAYER 1 WINS, CONGRATULATIONS!";
            else if(Logic.getP1_score() < Logic.getP2_score()) message = "PLAYER 2 WINS, CONGRATULATIONS!";
            else message = "DRAW, CONGRATULATIONS!";
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = mes_font.getStringBounds(message, context);

            double x = (Logic.getWidth() - bounds.getWidth()) / 2;
            double y = (Logic.getPanelt_height() - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double base_y = y + (ascent); //siegamy do linii bazowej tekstu

            g2.drawString(message, (int)x, (int)base_y - (Logic.getPanelt_height() / 30));
        }
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Logic.getWidth(), Logic.getHeight());
    }

    public Card find(Point p)
    {
        for(Card c : cards)
        {
            if(c.contains(p)) return c;
        }
        return null;
    }

    private class MouseMotionHandler extends MouseMotionAdapter
    {
        public void mouseMoved(MouseEvent event)
        {
            Card c = find(event.getPoint());
            if(c != null && !c.isFlipped() && active) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else setCursor(Cursor.getDefaultCursor());
        }
    }
    private class MouseHandler extends MouseAdapter
    {
        public void mousePressed(MouseEvent event)
        {
            Card c = find(event.getPoint());
            if(c != null && !c.isFlipped() && active)
            {
                if(Logic.getPicks() == 0)
                {
                    c.flipCard();
                    repaint();
                    pickedCards[0] = c;
                    Logic.increasePicks();
                }
                else
                {
                    active = false;
                    c.flipCard();
                    repaint();
                    Timer t = new Timer(500, new ActionListener()
                    {
                        public void actionPerformed(ActionEvent actionEvent)
                        {
                            pickedCards[1] = c;
                            if(Logic.checkCards(pickedCards))
                            {
                                cards[pickedCards[0].getId()].changeStatus();
                                cards[pickedCards[1].getId()].changeStatus();
                            }
                            else
                            {
                                cards[pickedCards[0].getId()].flipCard();
                                cards[pickedCards[1].getId()].flipCard();
                            }

                            repaint();
                            board.refresh();
                            active = true;
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }
}

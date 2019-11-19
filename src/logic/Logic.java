package logic;

import objects.Card;

import java.awt.*;
import java.io.File;

public abstract class Logic
{
    private static Font afont = new Font("Mansalva", Font.PLAIN, 1);
    private static int width;
    private static int height;
    private static int panelt_height;
    private static int card_w;
    private static int p1_score = 0, p2_score = 0;
    private static boolean turn_p1 = true;
    private static int cards_out = 0;
    private static byte picks = 0;

    public static int getWidth()
    {
        return width;
    }
    public static void setWidth(int w)
    {
        width = w;
    }
    public static int getHeight()
    {
        return height;
    }
    public static void setHeight(int h)
    {
        height = h;
    }
    public static int getPanelt_height()
    {
        return panelt_height;
    }
    public static void setPanelt_height(int p)
    {
        panelt_height = p;
    }
    public static int getCard_w()
    {
        return card_w;
    }
    public static void setCard_w(int c)
    {
        card_w = c;
    }
    public static int getP1_score()
    {
        return p1_score;
    }
    public static int getP2_score()
    {
        return p2_score;
    }
    public static boolean getP1Turn()
    {
        return turn_p1;
    }
    public static int getPicks()
    {
        return picks;
    }
    public static int getCards_out()
    {
        return cards_out;
    }
    public static Font getFont()
    {
        return afont;
    }
    public static void increasePicks()
    {
        picks++;
    }
    public static boolean checkCards(Card[] cards)
    {
        if(cards[0].getFront().equals(cards[1].getFront()))
        {
            picks = 0;
            cards_out += 2;
            if(turn_p1) p1_score++;
            else p2_score++;
            return true;
        }
        picks = 0;
        turn_p1 = !turn_p1;
        return false;
    }
    public static String getPath(String f)
    {
        return "." + File.separatorChar + "res" + File.separatorChar + "imgs" + File.separatorChar + f;
    }
}

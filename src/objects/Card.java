package objects;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Card
{
    private Image back = new ImageIcon(Logic.getPath("back.png")).getImage();
    private Image currentImage = back;
    private Image front;
    private int width = Logic.getCard_w();
    private int id;
    private Point location;
    private boolean isInGame = true;
    private boolean flipped = false;

    public Card(String path, int id)
    {
        front = new ImageIcon(path).getImage();
        this.id = id;
    }

    public Point getPoint()
    {
        return location;
    }
    public boolean getStatus()
    {
        return isInGame;
    }
    public Image getCurrentImage()
    {
        return currentImage;
    }
    public Image getFront()
    {
        return front;
    }
    public int getId()
    {
        return id;
    }
    public void setLocation(Point p)
    {
        location = p;
    }
    public boolean isFlipped()
    {
        if(flipped) return true;
        else return false;
    }



    public boolean contains(Point2D p)
    {
        if(p.getX() <= location.x + width && p.getX() >= location.x && p.getY() >= location.y && p.getY() <= location.y + width && isInGame) return true;
        else return false;
    }

    public void flipCard()
    {
        if(currentImage.equals(front))
        {
            currentImage = back;
            flipped = false;
        }
        else
        {
            currentImage = front;
            flipped = true;
        }
    }

    public void changeStatus()
    {
        isInGame = false;
    }
}

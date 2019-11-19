package game;

import settings.OptionsFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Memory
{
    public static void main(String[] args)
    {
        loadFont();
        EventQueue.invokeLater(() ->
        {
           new OptionsFrame();
        });
    }
    private static void loadFont()
    {
        try
        {
            Font  afont = Font.createFont(Font.TRUETYPE_FONT, new File("." + File.separatorChar + "res" + File.separatorChar + "Mansalva-Regular.ttf")).deriveFont(1f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(afont);
        }
        catch (IOException | FontFormatException e)
        {
            e.printStackTrace();
        }
    }
}

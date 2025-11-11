package pub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bartender extends JPanel{

    Image bartender;
    int xVelocity = 1;
    int yVelocity = 1;
    int x = 0;
    int y = 0;

    Bartender(){
    
        bartender = new ImageIcon("guest.png").getImage();

    }

    @Override
    public void paint(Graphics g){

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(bartender, 250, 80, null);
        
    }
}

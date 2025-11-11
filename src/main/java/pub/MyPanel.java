package pub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener{

    final int PANEL_WIDTH = 640;
    final int  PANEL_HEIGHT = 400;
    Image beer;
    Image background;
    Image guest;
    Timer timer;
    int xVelocity = 1;
    int yVelocity = 1;
    int x = 0;
    int y = 0;

    MyPanel(){
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.white);
        beer = new ImageIcon("beer.png").getImage();
        background = new ImageIcon("background.png").getImage();
        guest = new ImageIcon("guest.png").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g){

        super.paint(g); 
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background, 0, 0, null);
        g2D.drawImage(beer, x, y, null);
        g2D.drawImage(guest, 250, 80, null);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= PANEL_WIDTH - beer.getWidth(null) || x < 0){
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;
        repaint();
    }
}

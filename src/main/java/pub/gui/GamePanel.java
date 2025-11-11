package pub.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pub.game.Bartender;
import pub.game.Beer;
import pub.game.EmptyBeer;
import pub.game.FullBeer;
import pub.game.GameLogic;
import pub.game.Guest;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private GameLogic gameLogic;
    private Timer timer;

    private static final int PANEL_WIDTH = 600;
    private static final int  PANEL_HEIGHT = 700;
    private static final int TABLE_HEIGHT = 10;
    private static final int NUM_TABLES = 10;
    private static final int BARTENDER_X = PANEL_WIDTH - TABLE_HEIGHT*3/2;
    private static final int DOOR_X = 0;
    

    public GamePanel(GameLogic logic){
        this.gameLogic = logic;

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.gray);

        setFocusable(true); //érzékeli, hogy billentyűleütés történt
        this.addKeyListener(this);

        timer = new Timer(10, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawTables(g2D);
        drawBartender(g2D, gameLogic.getBartender());
        drawGuests(g2D);
        drawBeers(g2D);
        if(gameLogic.getIsGameOver()){
            drawGameOver(g2D);
        }
        
    }

    public void drawTables(Graphics2D g2D){
        for(int i = 0; i < NUM_TABLES; i++){
            g2D.setColor(new Color(150, 75, 0));
            int padding = (PANEL_HEIGHT - NUM_TABLES * TABLE_HEIGHT) / (NUM_TABLES+1);
            g2D.fillRect(getDOOR_X(), padding + (padding + TABLE_HEIGHT)*i, getBARTENDER_X()-TABLE_HEIGHT/2, TABLE_HEIGHT);

            g2D.setColor(Color.black);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawRect(getDOOR_X(), padding + (padding + TABLE_HEIGHT)*i, getBARTENDER_X()-TABLE_HEIGHT/2, TABLE_HEIGHT);
            
        }
    }

    public void drawBartender(Graphics2D g2D, Bartender bartender){
        int padding = (PANEL_HEIGHT - NUM_TABLES * TABLE_HEIGHT) / (NUM_TABLES +1);
        int y = padding + (padding + TABLE_HEIGHT)*bartender.getTableIndex();
        g2D.setColor(Color.pink);
        g2D.fillOval(BARTENDER_X, y, TABLE_HEIGHT, TABLE_HEIGHT);
        g2D.setColor(Color.black);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawOval(BARTENDER_X, y, TABLE_HEIGHT, TABLE_HEIGHT);      
    }

    public void drawGuests(Graphics2D g2D){
        int padding = (PANEL_HEIGHT - NUM_TABLES * TABLE_HEIGHT) / (NUM_TABLES +1);
        
        for (Guest guest : gameLogic.getGuests()){
            int y = padding + (padding + TABLE_HEIGHT)*guest.getTableIndex();
            g2D.setColor(Color.green);
            g2D.fillOval(guest.getX(), y, TABLE_HEIGHT, TABLE_HEIGHT);
            g2D.setColor(Color.black);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawOval(guest.getX(), y, TABLE_HEIGHT, TABLE_HEIGHT);
        }             
    }

    public void drawBeers(Graphics2D g2D){
        int padding = (PANEL_HEIGHT - NUM_TABLES * TABLE_HEIGHT) / (NUM_TABLES +1);
        
        for (Beer beer : gameLogic.getBeers()){
            int y = padding + (padding + TABLE_HEIGHT)*beer.getTableIndex();
            if (beer instanceof FullBeer){
                g2D.setColor(Color.yellow);
                g2D.fillOval(beer.getX(), y, TABLE_HEIGHT, TABLE_HEIGHT);
            }
            else if (beer instanceof EmptyBeer){
                g2D.setColor(Color.white);
                g2D.fillOval(beer.getX(), y, TABLE_HEIGHT, TABLE_HEIGHT);
            }
            g2D.setColor(Color.black);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawOval(beer.getX(), y, TABLE_HEIGHT, TABLE_HEIGHT);
        }             
    }

    public void drawGameOver( Graphics g2d){
        g2d.setColor(new Color(0, 0, 0, 100)); // Átlátszó fekete háttér
        g2d.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 64));
        String message = "GAME OVER!";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (PANEL_WIDTH - fm.stringWidth(message)) / 2;
        int y = (PANEL_HEIGHT / 2) - fm.getHeight() / 2;
        g2d.drawString(message, x, y);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        String scoreMsg = "Elért pontszám: " + gameLogic.getScore();
        g2d.drawString(scoreMsg, (PANEL_WIDTH - g2d.getFontMetrics().stringWidth(scoreMsg)) / 2, y + 40);
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(gameLogic.getIsGameOver()) return;
        if(e.getKeyCode() == 38){
            gameLogic.getBartender().moveUp();
        }
        else if(e.getKeyCode() == 40){
            gameLogic.getBartender().moveDown();
        }
        else if(e.getKeyCode() == 32){
            gameLogic.handleDraftBeer();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameLogic.getIsGameOver()){
            gameLogic.update();
        }
        repaint();
    }


    public static int getBARTENDER_X(){
        return BARTENDER_X;
    }
    public static int getDOOR_X(){
        return DOOR_X;
    }
    public static int getNUM_TABLES(){
        return NUM_TABLES;
    }
     public static int getTABLE_HEIGHT(){
        return TABLE_HEIGHT;
    }


}

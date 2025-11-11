package pub.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import pub.game.Bartender;
import pub.game.GameLogic;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private GameLogic gameLogic;
    private Timer timer;

    private static final int PANEL_WIDTH = 400;
    private static final int  PANEL_HEIGHT = 300;
    private static final int TABLE_HEIGHT = 30;
    private static final int NUM_TABLES = 4;
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
        g2D.fillOval(BARTENDER_X, y, TABLE_HEIGHT, TABLE_HEIGHT); // Példa: kék téglalap
        g2D.setColor(Color.black);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawOval(BARTENDER_X, y, TABLE_HEIGHT, TABLE_HEIGHT); // Példa: kék téglalap      
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


}

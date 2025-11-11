package pub.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import pub.game.GameLogic;

public class GameFrame extends JFrame{

    public GameFrame(){
        ImageIcon beer = new ImageIcon("beer.png");
        setIconImage(beer.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PUB GAME");
        setResizable(false);

        GameLogic gameLogic = new GameLogic();
        GamePanel gamePanel = new GamePanel(gameLogic);

        this.add(gamePanel);
        pack();

        setVisible(true);
    }

}

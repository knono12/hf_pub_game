package pub;


import javax.swing.*;

public class MyFrame extends JFrame{

    MyPanel beerPanel;
    Bartender bartender;
    
    MyFrame(){

        beerPanel = new MyPanel();
        bartender = new Bartender();
      
        ImageIcon beer = new ImageIcon("beer.png");
        setIconImage(beer.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PUB GAME");
        setLocationRelativeTo(null);
        setSize(650,450);

        this.add(beerPanel);
        add(bartender);
        pack(); 

        setVisible(true);
    }


}

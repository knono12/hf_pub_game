package pub.game;

import java.util.ArrayList;
import java.util.List;

import pub.gui.GamePanel;

public class GameLogic {
    private Bartender bartender;
    private List<Guest> guests;
    private List<Beer> beers;

    private int score;
    private boolean isGameOver;

    private static final int THRESHOLD = 450;       
    private static final int LATE_THRESHOLD = 200;

    public GameLogic(){
        bartender = new Bartender(GamePanel.getBARTENDER_X(), 0);
        guests = new ArrayList<>();
        beers = new ArrayList<>();
        score = 0;
        isGameOver = false;
    }

    public void update(){
        if(isGameOver) return;
        moveBeerGuest();
        //2. új vendégek jönnek elő random a 4 asztaltól
        //3. ütközések vesztési feltételek ellenőrzése
        //4. Kiszolgált vendégek és összeszedett sörök törlése

    }

    private void moveBeerGuest(){
        for (Beer beer : beers){
            beer.move();
        }
        for(Guest customer : guests){
            customer.move();
        }
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public Bartender getBartender(){
        return bartender;
    }
    
}

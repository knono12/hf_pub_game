package pub.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pub.gui.GamePanel;

public class GameLogic {
    private Bartender bartender;
    private List<Guest> guests;
    private List<Beer> beers;

    private int score;
    private boolean isGameOver;
    private int guestTimeCount;

    private static int GUEST_TIME_LIMIT = 100;

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
        createNewGuests();
        //3. ütközések vesztési feltételek ellenőrzése
        collisionBeerGuest();
        //4. Kiszolgált vendégek és összeszedett sörök törlése
        
    }

    private void moveBeerGuest(){
        for (Beer beer : beers){
            beer.move();
            if(beer.getX() < 0){
                isGameOver = true;
            }
        }
        for(Guest guest : guests){
            guest.move();
            if(guest.getX() > GamePanel.getBARTENDER_X()){
                isGameOver = true;
            }
        }
    }

    private void createNewGuests(){
        guestTimeCount ++;
        if(guestTimeCount > GUEST_TIME_LIMIT){
            Random random = new Random();
            int ran = random.nextInt(GamePanel.getNUM_TABLES()-1);
            Guest guest = new Guest(0, ran);
            guests.add(guest);
            guestTimeCount = 0;
        }
    }

    private void collisionBeerGuest(){
        List<Beer> beersToRemove = new ArrayList<>();
        List<Guest> guestsToRemove = new ArrayList<>();

        for(Beer beer : beers){
            if(beer instanceof FullBeer){
                for(Guest guest : guests){
                    if(beer.getTableIndex() == guest.getTableIndex()){
                        if (!(beer.getX() + GamePanel.getTABLE_HEIGHT() < guest.getX() || guest.getX() + GamePanel.getTABLE_HEIGHT() < beer.getX())){
                            score ++;
                            beersToRemove.add(beer);
                            guestsToRemove.add(guest);
                        }
                    }
                }
            }
        } 

        beers.removeAll(beersToRemove);
        guests.removeAll(guestsToRemove);
    }

    



    public void handleDraftBeer(){
        FullBeer fb = new FullBeer(GamePanel.getBARTENDER_X(), bartender.getTableIndex());
        beers.add(fb);
    }


    public boolean getIsGameOver(){
        return isGameOver;
    }
    public Bartender getBartender(){
        return bartender;
    }
    public List<Guest> getGuests(){
        return guests;
    }
    public List<Beer> getBeers(){
        return beers;
    }
    public int getScore(){
        return score;
    }
    
}

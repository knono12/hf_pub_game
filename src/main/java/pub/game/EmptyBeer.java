package pub.game;

public class EmptyBeer extends Beer{
    private static int emptySpeed = -3;

    public EmptyBeer(int x, int tableIndex){
        super(x, tableIndex, emptySpeed);
    }

    //csapos összeszedi ...
}

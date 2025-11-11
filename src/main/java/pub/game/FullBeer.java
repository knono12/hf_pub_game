package pub.game;

public class FullBeer extends Beer{
    private static int fullSpeed = 3;

    public FullBeer(int x, int tableIndex){
        super(x, tableIndex, fullSpeed);
    }

    public EmptyBeer createEMEmptyBeer(){
        return new EmptyBeer(x, tableIndex);
    }

    public FullBeer draftBeer(){
        return new FullBeer(x, tableIndex);
    }
}

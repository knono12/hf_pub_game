package pub.game;

public class FullBeer extends Beer{
    private static  final int FULL_SPEED = -3;

    public FullBeer(int x, int tableIndex){
        super(x, tableIndex, FULL_SPEED);
    }

    public EmptyBeer createEMEmptyBeer(){
        return new EmptyBeer(x, tableIndex);
    }

}

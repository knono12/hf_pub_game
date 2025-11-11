package pub.game;

public abstract class Beer {

    protected int x;
    protected int tableIndex;
    protected int speed;

    protected Beer(int initialX, int tidx, int sp){
        x = initialX;
        tableIndex = tidx;
        speed = sp;
    }

    public void move(){
        x += speed;
    }

    public int getX() {
        return x;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public int getSpeed() {
        return speed;
    }
}

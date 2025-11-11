package pub.game;

public class Guest {
    private int x;
    private int tableIndex;
    private int speed = 2;
    private boolean isServed;

    public Guest(int x, int tableIndex){
        this.x = x;
        this.tableIndex = tableIndex;
    }

    public int getXPosition() {
        return x;
    }
    public int getTableIndex() {
        return tableIndex;
    }
    public boolean isServed() {
        return isServed;
    }
    public int getSpeed() {
        return speed;
    }

    public void move(){
        x += speed;
    }

}

package pub.game;

public class Guest {
    private int x;
    private int tableIndex;
    private int speed = 1;
    private boolean isServed;

    public Guest(int x, int tableIndex){
        this.x = x;
        this.tableIndex = tableIndex;
        this.isServed = false;
    }

    public int getX() {
        return x;
    }
    public int getTableIndex() {
        return tableIndex;
    }
    public boolean isServed() {
        return isServed;
    }
    public void setIsServed(boolean tf) {
        isServed = tf;
    }
    public int getSpeed() {
        return speed;
    }

    public void move(){
        x += speed;
    }

}

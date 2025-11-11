package pub.game;

import pub.gui.GamePanel;

public class Bartender {
    private int y;
    private int tableIndex; //éppen melyik asztalnál járunk

    Bartender(int x, int t){
        this.y = x;
        tableIndex = t;
    }

    public void moveUp(){
        if(tableIndex > 0){
            tableIndex --;
        }
    }

    public void moveDown(){
        if(tableIndex < GamePanel.getNUM_TABLES()-1){
            tableIndex ++;
        }
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public int getY() {
        return y;
    }
}

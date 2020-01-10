package chessboard;

import creature.Creature;

public class Cell <T extends Creature>{
    private T creature;

    public Cell(){}
    public Cell(T creature){
        this.creature=creature;
    }
    public void setCreature(T creature){
        this.creature=creature;
    }
    public T getCreature(){
        return creature;
    }
}

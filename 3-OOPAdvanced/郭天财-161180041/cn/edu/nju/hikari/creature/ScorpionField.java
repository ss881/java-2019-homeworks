package creature;

import java.util.Vector;

import javax.swing.text.DefaultEditorKit.BeepAction;

import command.Style;
import ground.*;
import static command.Command.*;

public class ScorpionField implements Style{
    public Scorpion scorpion = new Scorpion();
    public Snake snake = new Snake();
    public Vector<Potato> potatoes = new Vector<Potato>(17);
    {
        for(int i = 0; i < 17; i++)
            potatoes.addElement(new Potato());
    }

    public void giveCommand(int cmd){
        switch(cmd){
            case WING:wing();break;
            case GOOSE:goose();break;
            case PUNCH:punch();break;
            case FISH_SCALE:fishScale();break;
            case SQUARE:square();break;
            case MOON:moon();break;
            case BEE:bee();break;
            default:wing();
        }
    }

    private void clear(){
        scorpion.leave();
        snake.leave();
        for(Potato p : potatoes){
            p.leave();
        }
    }

    public void randomSnake(){}

    public void moon(){
        
        clear();
        snake.moveTo(Ground.tiles[Central][Central]);
        scorpion.moveTo(Ground.tiles[Central][Central-1]);
        potatoes.get(0).moveTo(Ground.tiles[Central][Central+1]);
        potatoes.get(1).moveTo(Ground.tiles[Central+1][Central]);
        potatoes.get(2).moveTo(Ground.tiles[Central+1][Central+1]);
        potatoes.get(3).moveTo(Ground.tiles[Central+1][Central-1]);
        potatoes.get(4).moveTo(Ground.tiles[Central+2][Central+2]);
        potatoes.get(5).moveTo(Ground.tiles[Central+2][Central+1]);
        potatoes.get(6).moveTo(Ground.tiles[Central+2][Central]);
        potatoes.get(7).moveTo(Ground.tiles[Central+2][Central-1]);
        potatoes.get(8).moveTo(Ground.tiles[Central+2][Central-2]);
        potatoes.get(9).moveTo(Ground.tiles[Central+3][Central+3]);
        potatoes.get(10).moveTo(Ground.tiles[Central+3][Central+2]);
        potatoes.get(11).moveTo(Ground.tiles[Central+3][Central-2]);
        potatoes.get(12).moveTo(Ground.tiles[Central+3][Central-3]);
        potatoes.get(13).moveTo(Ground.tiles[Central+4][Central+3]);
        potatoes.get(14).moveTo(Ground.tiles[Central+4][Central-3]);
        potatoes.get(15).moveTo(Ground.tiles[Central+5][Central+4]);
        potatoes.get(16).moveTo(Ground.tiles[Central+5][Central-4]);
    }
    public void goose(){
        clear();
        scorpion.moveTo(Ground.tiles[Central][Central]);
        snake.moveTo(Ground.tiles[Central+1][Central-1]);
        potatoes.get(0).moveTo(Ground.tiles[Central+2][Central-2]);
        potatoes.get(1).moveTo(Ground.tiles[Central+3][Central-3]);
        potatoes.get(2).moveTo(Ground.tiles[Central+4][Central-4]);
    }

    public void punch(){
        clear();
        scorpion.moveTo(Ground.tiles[Central][Central]);
        snake.moveTo(Ground.tiles[Central][Central+2]);
        potatoes.get(0).moveTo(Ground.tiles[Central][Central-2]);
        potatoes.get(1).moveTo(Ground.tiles[Central+1][Central+1]);
        potatoes.get(2).moveTo(Ground.tiles[Central+1][Central-3]);
        potatoes.get(3).moveTo(Ground.tiles[Central+1][Central-1]);
        

    }

    public void snake(){}

    public void fishScale(){
        clear();
        scorpion.moveTo(Ground.tiles[Central][Central]);
        snake.moveTo(Ground.tiles[Central-2][Central]);
        potatoes.get(0).moveTo(Ground.tiles[Central][Central+2]);
        potatoes.get(1).moveTo(Ground.tiles[Central-3][Central+1]);
        potatoes.get(2).moveTo(Ground.tiles[Central-1][Central+1]);
        potatoes.get(3).moveTo(Ground.tiles[Central+1][Central+1]);
        potatoes.get(4).moveTo(Ground.tiles[Central+3][Central+1]);
        potatoes.get(5).moveTo(Ground.tiles[Central+2][Central]);
        potatoes.get(6).moveTo(Ground.tiles[Central+1][Central-1]);
        potatoes.get(7).moveTo(Ground.tiles[Central][Central-2]);
    }
    public void square(){
        clear();
        scorpion.moveTo(Ground.tiles[Central][Central]);
        snake.moveTo(Ground.tiles[Central+1][Central-1]);
        potatoes.get(0).moveTo(Ground.tiles[Central+1][Central+1]);
        potatoes.get(1).moveTo(Ground.tiles[Central+2][Central-2]);
        potatoes.get(2).moveTo(Ground.tiles[Central+2][Central+2]);
        potatoes.get(3).moveTo(Ground.tiles[Central+3][Central-1]);
        potatoes.get(4).moveTo(Ground.tiles[Central+3][Central+1]);
        potatoes.get(5).moveTo(Ground.tiles[Central+4][Central]);
    }

    public void wing(){
        clear();
        scorpion.moveTo(Ground.tiles[Central+3][Central+3]);
        snake.moveTo(Ground.tiles[Central+2][Central+2]);
        potatoes.get(0).moveTo(Ground.tiles[Central][Central]);
        potatoes.get(1).moveTo(Ground.tiles[Central+1][Central+1]);
        potatoes.get(2).moveTo(Ground.tiles[Central+4][Central+2]);
        potatoes.get(3).moveTo(Ground.tiles[Central+5][Central+1]);
        potatoes.get(4).moveTo(Ground.tiles[Central+6][Central]);
    }
    public void bee(){
        clear();
        scorpion.moveTo(Ground.tiles[Central][Central]);
        snake.moveTo(Ground.tiles[Central][Central+1]);
        potatoes.get(0).moveTo(Ground.tiles[Central][Central-1]);
        potatoes.get(1).moveTo(Ground.tiles[Central][Central+2]);
        potatoes.get(2).moveTo(Ground.tiles[Central][Central-2]);
        potatoes.get(3).moveTo(Ground.tiles[Central][Central+3]);
        potatoes.get(4).moveTo(Ground.tiles[Central-1][Central-1]);
        potatoes.get(5).moveTo(Ground.tiles[Central-2][Central]);
        potatoes.get(6).moveTo(Ground.tiles[Central-3][Central+1]);
        potatoes.get(7).moveTo(Ground.tiles[Central+1][Central-1]);
        potatoes.get(8).moveTo(Ground.tiles[Central+2][Central]);
        potatoes.get(9).moveTo(Ground.tiles[Central+3][Central+1]);
    }

}
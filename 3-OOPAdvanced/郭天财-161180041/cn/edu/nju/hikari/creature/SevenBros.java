package creature;

import ground.*;
import command.Style;

public class SevenBros implements Style{
    public CalabashBoy []calaBros = new CalabashBoy[7];
    {
        for(int i = 0; i < 7; i++) {
            calaBros[i] = new CalabashBoy(i+1);
        }
    }

    public void randomSnake(){
        for(CalabashBoy cala : calaBros){
            cala.randomStand();
        }
    }

    public void wing(){

    }
    public void goose(){

    }

    public void punch(){

    }

    public void snake(){

        BubbleSort();
    }
    public void fishScale(){

    }
    public void square(){

    }
    public void moon(){
    }
    public void bee(){

    }

    private void BubbleSort(){
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < 7 - i -1; j++){
                CalabashBoy cala = (CalabashBoy) Ground.tiles[1][j+7].life;
                cala.SwapWithNext();
            }
        }
    }


}
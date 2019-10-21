package com.swt.model.advance;

import com.swt.model.basic.Character;
import com.swt.model.basic.Color;
import com.swt.model.basic.Rank;
import javafx.scene.image.Image;

/**
 * 葫芦娃类
 */
public class Calabash extends Character {
    private int id;
    private Color color;
    private Rank rank;

    public Calabash(){}

    /**
     * 不要使用id进行初始化，葫芦娃出生时应该给予颜色和排行
     * @param color
     * @param rank
     * @param image
     */
    public Calabash(Color color, Rank rank, Image image){
        super(0, rank.ordinal() + 2, image);
        this.color = color;
        this.rank = rank;
        this.id = rank.ordinal();
    }
    public int getId(){
        return this.id;
    }
    public Color getColor(){
        return this.color;
    }
    public Rank getRank(){
        return this.rank;
    }

    /**
     * 报数
     * @param isColor   true时按颜色报数，否则按排行报数
     */
    public void shout(boolean isColor){
        if(isColor){
            System.out.print(this.color + " ");
        }else{
            System.out.print(this.rank + " ");
        }
    }
}

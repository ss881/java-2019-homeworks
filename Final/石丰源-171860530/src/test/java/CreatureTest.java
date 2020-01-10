import nju.sfy.model.Field;
import nju.sfy.model.Tile;
import nju.sfy.model.creature.Grandpa;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

//测试
public class CreatureTest {
    @Test
    public void testMove(){

        Field field = new Field();
        Tile tiles[][] = field.getTiles();
        int N = tiles.length;

        //初始化战场，每个砖块上无holder
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                assertTrue(tiles[i][j].getHolder() == null);
            }
        }

        //将爷爷设置在任意位置，随机向周围移动，检测移动后原砖块是否不再包含爷爷，目标砖块是否包含爷爷
        //共测试10000次
        Grandpa grandpa = new Grandpa();
        grandpa.setField(field);
        Random random = new Random();

        for(int i = 0; i < 10000; i++){
            int x = random.nextInt(N);
            int y = random.nextInt(N);

            grandpa.setTile(field.getTiles()[x][y]);
            field.getTiles()[x][y].setHolder(grandpa);
            assertTrue(grandpa == field.getTiles()[x][y].getHolder());

            Tile tile1 = field.getTiles()[x][y];
            int offsetX = random.nextInt(2);
            int offsetY = random.nextInt(2);
            if(grandpa.doMove(offsetX, offsetY)){
                Tile tile2 = field.getTiles()[x + offsetX][y + offsetY];
                if(tile1 != tile2){
                    assertTrue(tile1.getHolder() != grandpa);
                    assertTrue(tile2.getHolder() == grandpa);
                }
            }
        }
    }

    @Test
    public void testDead(){
        Field field = new Field();
        Tile tiles[][] = field.getTiles();
        int N = tiles.length;

        //初始化战场，每个砖块上无holder
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                assertTrue(tiles[i][j].getHolder() == null);
            }
        }

        //将爷爷设置在任意位置，受到攻击死亡，检测原砖块是否不再包含爷爷，爷爷不在任何砖块上
        //共测试10000次
        for(int i = 0; i < 10000; i++){
            Grandpa grandpa = new Grandpa();
            grandpa.setField(field);
            Random random = new Random();

            int x = random.nextInt(N);
            int y = random.nextInt(N);

            grandpa.setTile(field.getTiles()[x][y]);
            field.getTiles()[x][y].setHolder(grandpa);
            assertTrue(grandpa == field.getTiles()[x][y].getHolder());

            Tile tile1 = field.getTiles()[x][y];
            grandpa.betAttacked(100000000);
            assertTrue(grandpa.getTile() == null);
            assertTrue(tile1.getHolder() != grandpa);
        }
    }


}

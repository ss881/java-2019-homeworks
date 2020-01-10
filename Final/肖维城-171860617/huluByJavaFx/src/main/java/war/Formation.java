package war;

import creature.Creature;
import creature.HuluBros;
import creature.Monsters;
import location.Position;

import java.util.ArrayList;

import static war.BattleField.FIELD_HEIGHT;
import static war.BattleField.FIELD_WIDTH;

public class Formation {
    //public final static int FIELD_WIDTH = 15;
    //public final static int FIELD_HEIGHT = 8;
    //char map[][];
    public String fStr;
    public int mCount;
    //Array map;
    //蛇精：$，蝎子精：#
    /**长蛇vs偃月
     * * * * * * * * * * * * * * A
     * 1 * * * * * * * * * * * B *
     * 2 * * * * * * * * * * C D *
     0 3 * * * * * * * * $ E F * *
     * 4 * * * * * * * * # G H * *
     * 5 * * * * * * * * * * I J *
     * 6 * * * * * * * * * * * K *
     * 7 * * * * * * * * * * * * L
     */
    Formation(){
        //map = new char[FIELD_HEIGHT][FIELD_WIDTH];
        String f = "* * * * * * * * * * * * * * A\n" +
                   "* 1 * * * * * * * * * * * B *\n" +
                   "* 2 * * * * * * * * * * C D *\n" +
                   "0 3 * * * * * * * * $ E F * *\n" +
                   "* 4 * * * * * * * * # G H * *\n" +
                   "* 5 * * * * * * * * * * I J *\n" +
                   "* 6 * * * * * * * * * * * K *\n" +
                   "* 7 * * * * * * * * * * * * L\n";
        String f2 = "* * * * * * * * * * * * * * *\n" +
                    "* 1 * * * * * * * * * * * A *\n" +
                    "* 2 * * * * * * * * * * B * *\n" +
                    "0 3 * * * * * * * * $ C D * *\n" +
                    "* 4 * * * * * * * * # E F * *\n" +
                    "* 5 * * * * * * * * * * G * *\n" +
                    "* 6 * * * * * * * * * * * H *\n" +
                    "* 7 * * * * * * * * * * * * *\n";
        fStr = f2;
        //mCount = 12;
        mCount = 8;
        //map[FIELD_HEIGHT][FIELD_WIDTH];
        /*for(int i=0; i<FIELD_HEIGHT; i++){
            for(int j=0; j<FIELD_WIDTH; j++){
                map[i][j] =
            }
        }*/
    }

    public <T> void setFormation(T x, BattleField bf){
        //int i = BattleField.FIELD_HEIGHT;
        if(x.getClass().getSimpleName().equals("HuluBros")){
            HuluBros hb = (HuluBros) x;
            for(int i=0; i<FIELD_HEIGHT; i++){
                for(int j=0; j<FIELD_WIDTH; j++){
                    char ch = fStr.charAt(i*(FIELD_WIDTH*2)+j);
                    if(ch >= '0' && ch <= '7'){//葫芦兄弟: 0-7
                        Creature ct = null;
                        if(ch != '0') {//1-7
                            ct = hb.hList.get(ch - '0' - 1);
                        }else{//Grandpa=0
                            ct = hb.grandPa;
                        }
                        Position pos = bf.posList.get(i*FIELD_WIDTH+j);
                        ct.pos = pos;
                        pos.holder = ct;
                    }
                }
            }
        }else if(x.getClass().getSimpleName().equals("Monsters")){
            Monsters ms = (Monsters) x;
            for(int i=0; i<FIELD_HEIGHT; i++){
                for(int j=0; j<FIELD_WIDTH; j++){
                    char ch = fStr.charAt(i*(FIELD_WIDTH*2)+j);
                    Creature ct = null;
                    if(ch >= 'A' && ch <= 'Z'){//喽啰: A-Z
                        ct = ms.mList.get(ch - 'A');
                        //mCount++;
                    }else if(ch == '$'){//蛇精
                        ct = ms.SnakeSpirit;
                    }else if(ch == '#'){//蝎子精
                        ct = ms.ScorpionSpirit;
                    }else{
                        continue;
                    }
                    assert ct!=null;
                    Position pos = bf.posList.get(i*FIELD_WIDTH+j);
                    ct.pos = pos;
                    pos.holder = ct;
                }
            }
        }
    }

    public <T> void setFormation(T x, ArrayList<Position> posList){
        if(x.getClass().getSimpleName().equals("HuluBros")){
            //System.out.println("setFormation:   HuluBros:");
            HuluBros hb = (HuluBros) x;
            for(int i=0; i<FIELD_HEIGHT; i++){
                for(int j=0; j<FIELD_WIDTH; j++){
                    //char ch = fStr.charAt(i*(FIELD_WIDTH*2)+j);
                    char ch = fStr.charAt(i*(FIELD_WIDTH*2)+j*2);
                    //System.out.print(ch);
                    if(ch >= '0' && ch <= '7'){//葫芦兄弟: 0-7
                        //System.out.print("ch="+ch+'\n');
                        Creature ct = null;
                        if(ch != '0') {//1-7
                            ct = hb.hList.get(ch - '0' - 1);
                            //HuluBros.Hulu hl = (HuluBros.Hulu) ct;
                            //System.out.print(hl.id+" : ");
                            //System.out.println(hl.pos.toString());
                        }else{//Grandpa=0
                            ct = hb.grandPa;
                        }
                        assert ct!=null;
                        Position pos = posList.get(i*FIELD_WIDTH+j);
                        //System.out.println("pos: "+pos);
                        ct.pos = pos;
                        //System.out.println("ct.pos: "+ct.pos);
                        pos.holder = ct;
                        //System.out.println(pos.holder == hb.hList.get(ch-'0'-1)||pos.holder == hb.grandPa);
                    }
                }
                //System.out.print('\n');
            }
        }else if(x.getClass().getSimpleName().equals("Monsters")){
            //System.out.println("setFormation:   Monsters:");
            Monsters ms = (Monsters) x;
            for(int i=0; i<FIELD_HEIGHT; i++){
                for(int j=0; j<FIELD_WIDTH; j++){
                    char ch = fStr.charAt(i*(FIELD_WIDTH*2)+j*2);
                    Creature ct = null;
                    if(ch >= 'A' && ch <= 'Z'){//喽啰: A-Z
                        ct = ms.mList.get(ch - 'A');
                        //mCount++;
                    }else if(ch == '$'){//蛇精
                        ct = ms.SnakeSpirit;
                        //System.out.println("snake: "+ct.pos);
                    }else if(ch == '#'){//蝎子精
                        ct = ms.ScorpionSpirit;
                        //System.out.println("scorp: "+ct.pos);
                    }else{
                        continue;
                    }
                    assert ct!=null;
                    Position pos = posList.get(i*FIELD_WIDTH+j);
                    ct.pos = pos;
                    //System.out.println("pos = "+pos);
                    pos.holder = ct;
                }
            }
        }
    }
}

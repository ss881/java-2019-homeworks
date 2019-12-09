public class Map {
    private static final int N = 20;
    private static Creature[][] map = new Creature[N][N];


    public Map() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Creature();
            }
        }
    }

    //map为用来初始化this.map的地图，(x,y)为初始化的坐标点
    public static void setMap(Creature obj, int x, int y) {
            if(obj.getCamp()) {
                switch (obj.getName())
                {
                    case "1":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"RED");
                        break;
                    }
                    case "2":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"ORANGE");
                        break;
                    }
                    case "3":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"YELLOW");
                        break;
                    }
                    case "4":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"GREEN");
                        break;
                    }
                    case "5":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"CYAN");
                        break;
                    }
                    case "6":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"BLUE");
                        break;
                    }
                    case "7":
                    {
                        map[x][y] = new CalabashBrother(obj.getName(),"PURPLE");
                        break;
                    }
                    case "G":
                    {
                        map[x][y] = new Grandpa();
                        break;
                    }
                }

            }
            else {
                if (obj.getName() == "x") {
                    map[x][y] = new BadGuy();
                } else {
                    map[x][y]=new SerpentDemon();
                }
            }
    }

    public static void initMap()
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Creature();
            }
        }
    }

    public static void printMap()
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].getStatus()) {
                    String x=map[i][j].getName();
                    System.out.print(map[i][j].getName()+" ");
                }
                else
                {
                    System.out.print("  ");
                }
                /*if(j==N-1)
                {
                    System.out.print("|");
                }*/
            }
            System.out.println();
            //System.out.print("|");
        }
    }
}
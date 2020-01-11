public class Map {
    char symbol_map[][];
    Map()
    {
        symbol_map=new char[15][15];
        for(int i=0;i<15;i++)
            for(int j=0;j<15;j++) symbol_map[i][j]='_';
    }
    void printMap()
    {
        for(int i=0;i<15;i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(symbol_map[j][i]);
            }
            System.out.print('\n');
        }
    }
}

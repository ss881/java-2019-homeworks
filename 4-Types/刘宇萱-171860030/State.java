import java.util.*;
import java.lang.reflect.Method;

class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(int newx, int newy) {
        x = newx;
        y = newy;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int newx) { x = newx; }

    public void setY(int newy) { y = newy; }
}

class Formation {
    //calabash formation
    private Coordinate calabashstart[] = {
        new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0), new Coordinate(0, 0)
    };
    
    private Coordinate line[] = {
        new Coordinate(2, 1), new Coordinate(3, 1), new Coordinate(4, 1), new Coordinate(5, 1), new Coordinate(6, 1), new Coordinate(7, 1), new Coordinate(8, 1)
    };
    /*final char line[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/

    //underling formation
    private Coordinate underlingstart[] = {
        new Coordinate(1, 9), new Coordinate(2, 9), new Coordinate(3, 9), new Coordinate(4, 9), new Coordinate(5, 9), new Coordinate(6, 9), new Coordinate(7, 9), new Coordinate(8, 9)
    };

    private Coordinate cranewing[] = {
        new Coordinate(1, 8),
        new Coordinate(2, 7),
        new Coordinate(3, 6),
        new Coordinate(4, 5),
        new Coordinate(5, 4),
        new Coordinate(6, 5),
        new Coordinate(7, 6),
        new Coordinate(8, 7)
    };
    /*final char cranewing[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    private Coordinate wildgoose[] = {
        new Coordinate(1, 10),
        new Coordinate(2, 9),
        new Coordinate(3, 8),
        new Coordinate(4, 7),
        new Coordinate(5, 6),
        new Coordinate(6, 5),
        new Coordinate(7, 4),
        new Coordinate(8, 3)
    };
    /*final char wildgoose[][] = {

        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    private Coordinate yoke[] = {
        new Coordinate(1, 6),
        new Coordinate(2, 7),
        new Coordinate(3, 6),
        new Coordinate(4, 7),
        new Coordinate(5, 6),
        new Coordinate(6, 7),
        new Coordinate(7, 6),
        new Coordinate(8, 7)
    };
    /*final char yoke[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    private Coordinate fishscale[] = {
        new Coordinate(3, 7),
        new Coordinate(4, 6), new Coordinate(4, 8),
        new Coordinate(5, 5), new Coordinate(5, 7),
        new Coordinate(6, 6), new Coordinate(6, 8),
        new Coordinate(7, 7)
    };
    /*final char fishscale[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    private Coordinate square[] = {
        new Coordinate(3, 6),
        new Coordinate(4, 5), new Coordinate(4, 7),
        new Coordinate(5, 4), new Coordinate(5, 8),
        new Coordinate(6, 5), new Coordinate(6, 7),
        new Coordinate(7, 6)
    };
    /*final char square[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    private Coordinate crescentmoon[] = {
        new Coordinate(3, 7),
        new Coordinate(4, 5), new Coordinate(4, 6),
        new Coordinate(5, 5), new Coordinate(5, 6),
        new Coordinate(6, 5), new Coordinate(6, 6),
        new Coordinate(7, 7)
    };
    /*final char crescentmoon[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/
    private Coordinate arrow[] = {
        new Coordinate(3, 7),
        new Coordinate(4, 6),
        new Coordinate(5, 5), new Coordinate(5, 6), new Coordinate(5, 7), new Coordinate(5, 8),
        new Coordinate(6, 6),
        new Coordinate(7, 7)
    };
    /*final char arrow[][] = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'U', 'U', 'U', 'U', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };*/

    private Coordinate calabashform[];
    private Coordinate underlingform[] = underlingstart;

    Formation() {
        Random random = new Random();
        int seq[] = {0, 1, 2, 3, 4, 5, 6};
        for(int i = 0; i < 7; i++) {
            int r = random.nextInt(i + 1);
            int temp = seq[i];
            seq[i] = seq[r];
            seq[r] = temp;
        }

        for(int i = 0; i < calabashstart.length; i++) {
            calabashstart[i].setX(seq[i] + 2);
        }

        calabashform = calabashstart;
    }


    public void setCalabashForm() {
        calabashform = line;
    }

    public void setUnderlingForm (char ch) {
        switch(ch) {
            case '0': {
                underlingform = cranewing;
                break;
            }
            case '1': {
                underlingform = wildgoose;
                break;
            }
            case '2': {
                underlingform = yoke;
                break;
            }
            case '3': {
                underlingform = fishscale;
                break;
            }
            case '4': {
                underlingform = square;
                break;
            }
            case '5': {
                underlingform = crescentmoon;
                break;
            }
            case '6': {
                underlingform = arrow;
                break;
            }
            default: {
                ;
            }
        }
    }

    public Coordinate getCalaCoordinate(int i) {
        return new Coordinate(calabashform[i].getX(), calabashform[i].getY());
    }

    public Coordinate getUnderlingCoordinate(int i) {
        return new Coordinate(underlingform[i].getX(), underlingform[i].getY());
    }

    public boolean isInUnderlingCoordinate(int x, int y) {
        for(int i = 0; i < underlingform.length; i++) {
            if(underlingform[i].getX() == x && underlingform[i].getY() == y) {
                return true;
            }
        }
        return false;
    }
}

class Role {
    protected Coordinate mycoordinate = new Coordinate(0, 0);
    public void setCoordinate(Coordinate c) {
        mycoordinate.setX(c.getX());
        mycoordinate.setY(c.getY());
    }
    public void getCoordinate(Coordinate c) {
        c.setX(mycoordinate.getX());
        c.setY(mycoordinate.getY());
        //return new Coordinate(mycoordinate.getX(), mycoordinate.getY());
    }
}

class CalabashBrother extends Role {
    private Color myColor;
    private int rank;

    public CalabashBrother() {
        mycoordinate = new Coordinate(0, 0);
    }

    public CalabashBrother(Color c, int r, int x, int y) {
        mycoordinate = new Coordinate(x, y);
        myColor = c;
        rank = r;
    }

    public int getRank() {
        return rank;
    }
}

class Grandpa extends Role {
    Grandpa(int x, int y) {
        mycoordinate = new Coordinate(x, y);
    }
}

class Scorpion extends Role {
    Scorpion(int x, int y) {
        mycoordinate = new Coordinate(x, y);
    }
}

class Underling extends Role {
    Underling(int x, int y) {
        mycoordinate = new Coordinate(x, y);
    }
}

public class State {
    private static final int SIZE = 11;
    private static final int UNDERLING_NUM = 8;
    private static final int CALA_NUM = 7;
    /*private Grandpa g = new Grandpa(5, 0);
    private CalabashBrother calas[] ={new CalabashBrother(Color.RED, 1), new CalabashBrother(Color.ORANGE, 2), new CalabashBrother(Color.YELLOW, 3), 
        new CalabashBrother(Color.GREEN, 4), new CalabashBrother(Color.SYAN, 5), new CalabashBrother(Color.BLUE, 6), new CalabashBrother(Color.PURPLE, 7)};
    private Scorpion scorp = new Scorpion(5, 10);
    private Underling underlings[] = new Underling[UNDERLING_NUM];*/
    //private char curformation[][] = new char[SIZE][SIZE];
    private ArrayList<Role> roles = new ArrayList<Role>();
    private Formation form = new Formation();
    
    State() {
        //roles.add(new Grandpa(5, 0));
        
        roles.add(new CalabashBrother(Color.RED, 1, form.getCalaCoordinate(0).getX(), form.getCalaCoordinate(0).getY()));
        roles.add(new CalabashBrother(Color.ORANGE, 2, form.getCalaCoordinate(1).getX(), form.getCalaCoordinate(1).getY()));
        roles.add(new CalabashBrother(Color.YELLOW, 3, form.getCalaCoordinate(2).getX(), form.getCalaCoordinate(2).getY()));
        roles.add(new CalabashBrother(Color.GREEN, 4, form.getCalaCoordinate(3).getX(), form.getCalaCoordinate(3).getY()));
        roles.add(new CalabashBrother(Color.SYAN, 5, form.getCalaCoordinate(4).getX(), form.getCalaCoordinate(4).getY()));
        roles.add(new CalabashBrother(Color.BLUE, 6, form.getCalaCoordinate(5).getX(), form.getCalaCoordinate(5).getY()));
        roles.add(new CalabashBrother(Color.PURPLE, 7, form.getCalaCoordinate(6).getX(), form.getCalaCoordinate(6).getY()));

        //roles.add(new Scorpion(5, 10));

        for(int i = 0; i < UNDERLING_NUM; i++) {
            roles.add(new Underling(i + 1, SIZE - 1));
        }

        /*for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                curformation[i][j] = ' ';
            }
        }*/

        /*for(int i = 0; i < calas.length; i++) {
            Coordinate c = form.getCalaCoordinate(i);
            calas[i].setCoordinate(c);
            curformation[c.getX()][c.getY()] = Integer.toString(calas[i].getRank()).charAt(0);
        }

        for(int i = 0; i < UNDERLING_NUM; i++) {
            underlings[i] = new Underling();
            Coordinate c = form.getUnderlingCoordinate(i);
            underlings[i].setCoordinate(c);
            curformation[c.getX()][c.getY()] = 'U';
        }*/
    }

    public void start(Interface i) {
        form.setCalabashForm();
        changeCalabashForm(i);
        /*Coordinate c = g.getCoordinate();
        curformation[c.getX()][c.getY()] = 'G';*/
        roles.add(new Grandpa(5, 0));
        try { 
            Thread.sleep(500) ;
        }
        catch (InterruptedException ie) {}
        i.display(this);

        form.setUnderlingForm('0');
        changeUnderlingForm('0', i);
        //c = scorp.getCoordinate();
        roles.add(new Scorpion(5, 10));
        try { 
            Thread.sleep(500) ;
        }
        catch (InterruptedException ie) {}
        //curformation[c.getX()][c.getY()] = 'S';
        i.display(this);
    }

    public void changeCalabashForm(Interface i) {
        form.setCalabashForm();
        
        for(int j = 0, cala_n = 0; cala_n < CALA_NUM; j++) {
            if(roles.get(j).getClass().getName() != "CalabashBrother") {
                continue;
            }

            Coordinate src = new Coordinate(0, 0);
            roles.get(j).getCoordinate(src);
            Coordinate dst = form.getCalaCoordinate(cala_n);
            //System.out.print(src.getX() + "," + src.getY() + "->" + dst.getX() + "," + dst.getY() + "\n");
            Coordinate prev[][] = new Coordinate[SIZE][SIZE];
            for(int m = 0; m < SIZE; m++) {
                for(int n = 0; n < SIZE; n++) {
                    prev[m][n] = new Coordinate(0, 0);
                }
            }

            bfsCalabash(src, dst, prev);

            int srcx = src.getX();
            int srcy = src.getY();
            int dstx = dst.getX();
            int dsty = dst.getY();
            
            ArrayList<Coordinate> route = new ArrayList<Coordinate>();
            route.add(dst);
            while(dstx != srcx || dsty != srcy) {
                Coordinate temp = prev[dstx][dsty];
                route.add(temp);
                dstx = temp.getX();
                dsty = temp.getY();
                //System.out.print("temp: " + x + "," + y + "\n");
            }

            for(int k = route.size() - 1; k >= 0 ; k--) {
                int x = route.get(k).getX();
                int y = route.get(k).getY();
                //curformation[srcx][srcy] = ' ';
                srcx = x;
                srcy = y;

                roles.get(j).setCoordinate(new Coordinate(x, y));
                
                //System.out.print(x + " " + y + "    ");
                //curformation[x][y] = Integer.toString(calas[j].getRank()).charAt(0);
                try { 
                    Thread.sleep(500) ;
                }
                catch (InterruptedException ie) {}
                i.display(this);
            }

            cala_n++;
        }
    }

    public void bfsCalabash(Coordinate src, Coordinate dst, Coordinate prev[][]) {
        boolean visited[][] = new boolean[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                visited[i][j] = false;
            }
        }

        Queue<Coordinate> q = new LinkedList<Coordinate>();
        int x = src.getX();
        int y = src.getY();
        q.add(new Coordinate(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()) {
            Coordinate c = q.poll();
            int cx = c.getX();
            int cy = c.getY();

            if(cx == dst.getX() && cy == dst.getY()) {
                return;
            }

            if(cx - 1 >= 0 && !visited[cx - 1][cy] && getCurFormation(cx - 1, cy) == ' ') {
                q.add(new Coordinate(cx - 1, cy));
                visited[cx - 1][cy] = true;
                prev[cx - 1][cy] = c;
            }
            if(cx + 1 < SIZE && !visited[cx + 1][cy] && getCurFormation(cx + 1, cy) == ' ') {
                q.add(new Coordinate(cx + 1, cy));
                visited[cx + 1][cy] = true;
                prev[cx + 1][cy] = c;
            }
            if(cy - 1 >= 0 && !visited[cx][cy - 1] && getCurFormation(cx, cy - 1) == ' ') {
                q.add(new Coordinate(cx, cy - 1));
                visited[cx][cy - 1] = true;
                prev[cx][cy - 1] = c;
            }
            if(cy + 1 < SIZE && !visited[cx][cy + 1] && getCurFormation(cx, cy + 1) == ' ') {
                q.add(new Coordinate(cx, cy + 1));
                visited[cx][cy + 1] = true;
                prev[cx][cy + 1] = c;
            }

            if(cx - 1 >= 0 && cy - 1 >= 0 && !visited[cx - 1][cy - 1] && getCurFormation(cx - 1, cy - 1) == ' ') {
                q.add(new Coordinate(cx - 1, cy - 1));
                visited[cx - 1][cy - 1] = true;
                prev[cx - 1][cy - 1] = c;
            }
            if(cx - 1 >= 0 && cy + 1 < SIZE && !visited[cx - 1][cy + 1] && getCurFormation(cx - 1, cy + 1) == ' ') {
                q.add(new Coordinate(cx - 1, cy + 1));
                visited[cx - 1][cy + 1] = true;
                prev[cx - 1][cy + 1] = c;
            }
            if(cx + 1 < SIZE && cy - 1 >= 0 && !visited[cx + 1][cy - 1] && getCurFormation(cx + 1, cy - 1) == ' ') {
                q.add(new Coordinate(cx + 1, cy - 1));
                visited[cx + 1][cy - 1] = true;
                prev[cx + 1][cy - 1] = c;
            }
            if(cx + 1 < SIZE && cy + 1 < SIZE && !visited[cx + 1][cy + 1] && getCurFormation(cx + 1, cy + 1) == ' ') {
                q.add(new Coordinate(cx + 1, cy + 1));
                visited[cx + 1][cy + 1] = true;
                prev[cx + 1][cy + 1] = c;
            }
        }
    }

    public void changeUnderlingForm(char ch, Interface i) {
        form.setUnderlingForm(ch);
        boolean finished = false;
        while(!finished) {
            int k = 0;
            for(; k < UNDERLING_NUM; k++) {
                Coordinate c = form.getUnderlingCoordinate(k);
                int targetx = c.getX();
                int targety = c.getY();

                //System.out.println("targetx: " + targetx + " " + "targety: " + targety + "    ");

                if(getCurFormation(targetx, targety) == ' ') {
                    Coordinate prev[][] = new Coordinate[SIZE][SIZE];
                    for(int m = 0; m < SIZE; m++) {
                        for(int n = 0; n < SIZE; n++) {
                            prev[m][n] = new Coordinate(0, 0);
                        }
                    }
                    //ArrayList<ArrayList<Coordinate> > prev;
                    Coordinate tobemoved = new Coordinate(0, 0);
                    boolean success = bfsUnderling(targetx, targety, prev, tobemoved);

                    if(!success) {
                        continue;
                    }

                    int x = tobemoved.getX();
                    int y = tobemoved.getY();

                    //System.out.println(x + " " + y + "    ");
                    //System.out.println(targetx + " " + targety + "    ");

                    int j;
                    Coordinate prevc = new Coordinate(0, 0);
                    for(j = 0; j < UNDERLING_NUM + CALA_NUM + 2; j++) {
                        roles.get(j).getCoordinate(prevc);
                        if(prevc.getX() == tobemoved.getX() && prevc.getY() == tobemoved.getY()) {
                            //underlings[j].setCoordinate(c);
                            break;
                        }
                    }

                    while(x != targetx || y != targety) {
                        //curformation[x][y] = ' ';
                        Coordinate temp = prev[x][y];
                        x = temp.getX();
                        y = temp.getY();
                        //System.out.print(x + " " + y + "    ");
                        roles.get(j).setCoordinate(new Coordinate(x, y));
                        //curformation[x][y] = 'U';
                        try { 
                            Thread.sleep(500) ;
                        }
                        catch (InterruptedException ie) {}
                        i.display(this);
                    }

                    break;
                }
            }
            if(k == UNDERLING_NUM) {
                finished = true;
            }
        }
    }

    public boolean bfsUnderling(int x, int y, Coordinate[][] prev, Coordinate tobemoved) {
        boolean visited[][] = new boolean[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                visited[i][j] = false;
            }
        }

        Queue<Coordinate> q = new LinkedList<Coordinate>();
        q.add(new Coordinate(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()) {
            Coordinate c = q.poll();
            int cx = c.getX();
            int cy = c.getY();

            if(getCurFormation(cx, cy) == 'U') {
                if(!form.isInUnderlingCoordinate(cx, cy)) {
                    //System.out.print(cx + " " + cy + "    ");
                    tobemoved.setX(cx);
                    tobemoved.setY(cy);
                    return true;
                }
                else {
                    continue;
                }
            }

            if(cx - 1 >= 0 && !visited[cx - 1][cy] && (getCurFormation(cx - 1, cy) == ' ' || getCurFormation(cx - 1, cy) == 'U')) {
                q.add(new Coordinate(cx - 1, cy));
                visited[cx - 1][cy] = true;
                prev[cx - 1][cy] = c;
            }
            if(cx + 1 < SIZE && !visited[cx + 1][cy] && (getCurFormation(cx + 1, cy) == ' ' || getCurFormation(cx + 1, cy) == 'U')) {
                q.add(new Coordinate(cx + 1, cy));
                visited[cx + 1][cy] = true;
                prev[cx + 1][cy] = c;
            }
            if(cy - 1 >= 0 && !visited[cx][cy - 1] && (getCurFormation(cx, cy - 1) == ' ' || getCurFormation(cx, cy - 1) == 'U')) {
                q.add(new Coordinate(cx, cy - 1));
                visited[cx][cy - 1] = true;
                prev[cx][cy - 1] = c;
            }
            if(cy + 1 < SIZE && !visited[cx][cy + 1] && (getCurFormation(cx, cy + 1) == ' ' || getCurFormation(cx, cy + 1) == 'U')) {
                q.add(new Coordinate(cx, cy + 1));
                visited[cx][cy + 1] = true;
                prev[cx][cy + 1] = c;
            }

            if(cx - 1 >= 0 && cy - 1 >= 0 && !visited[cx - 1][cy - 1] && (getCurFormation(cx - 1, cy - 1) == ' ' || getCurFormation(cx - 1, cy - 1) == 'U')) {
                q.add(new Coordinate(cx - 1, cy - 1));
                visited[cx - 1][cy - 1] = true;
                prev[cx - 1][cy - 1] = c;
            }
            if(cx - 1 >= 0 && cy + 1 < SIZE && !visited[cx - 1][cy + 1] && (getCurFormation(cx - 1, cy + 1) == ' ' || getCurFormation(cx - 1, cy + 1) == 'U')) {
                q.add(new Coordinate(cx - 1, cy + 1));
                visited[cx - 1][cy + 1] = true;
                prev[cx - 1][cy + 1] = c;
            }
            if(cx + 1 < SIZE && cy - 1 >= 0 && !visited[cx + 1][cy - 1] && (getCurFormation(cx + 1, cy - 1) == ' ' || getCurFormation(cx + 1, cy - 1) == 'U')) {
                q.add(new Coordinate(cx + 1, cy - 1));
                visited[cx + 1][cy - 1] = true;
                prev[cx + 1][cy - 1] = c;
            }
            if(cx + 1 < SIZE && cy + 1 < SIZE && !visited[cx + 1][cy + 1] && (getCurFormation(cx + 1, cy + 1) == ' ' || getCurFormation(cx + 1, cy + 1) == 'U')) {
                q.add(new Coordinate(cx + 1, cy + 1));
                visited[cx + 1][cy + 1] = true;
                prev[cx + 1][cy + 1] = c;
            }
        }

        return false;
    }

    public char getCurFormation(int x, int y) {
        Coordinate c = new Coordinate(0, 0);
        for(Role r : roles) {
            Class<?> role = r.getClass();
            try {
                Method get = role.getMethod("getCoordinate", Coordinate.class);
                get.invoke(r, c);
                if(c.getX() == x && c.getY() == y) {
                    if(role.getName() == "CalabashBrother") {
                        CalabashBrother cala = (CalabashBrother)r;
                        return Integer.toString(cala.getRank()).charAt(0);
                    }
                    else if(role.getName() == "Underling") {
                        return 'U';
                    }
                    else if(role.getName() == "Grandpa") {
                        return 'R';
                    }
                    else {
                        return 'S';
                    }
                }
            }
            catch(Exception e) {
                System.out.println("No such method.");
            }
        }
        return ' ';
        //return curformation[x][y];
    }

    public int getSize() {
        return SIZE;
    }
}
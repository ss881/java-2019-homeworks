package chessman;

import chessboard.Position;
import java.util.Vector;

public class FindPath {
    //find a access path between src and dst
    /*[N+1][M+1],N=3,M=4
     * -1    -1    -1  -1  -1       -1
     * -1     0     0   0   0       -1
     * -1     0     0   -1  0(1,3)  -1
     * -1     0     0   0   0       -1
     * -1    -1    -1  -1  -1       -1
     * */
    Vector<Integer> A;
    Vector<Position> path;
    Vector<Position> direction;

    private int N, M;

    public FindPath(Vector<Integer> A, int N, int M) {
        this.A = A;
        this.N = N;
        this.M = M;
        path = new Vector<Position>();
        direction = new Vector<Position>();
        direction.add(new Position(1, 0));
        direction.add(new Position(-1, 0));
        direction.add(new Position(0, 1));
        direction.add(new Position(0, -1));
    }

    private int getP(Position x) {
        int i = transFromOut(x);
        return A.get(i);
    }

    private int transFromOut(Position index) {
        return (index.getY() + 1) * (M + 2) + index.getX() + 1;
    }

    private Position transToOut(int index) {
        int x = index % (M + 2) - 1;
        int y = index / (M + 2) - 1;
        return new Position(x, y);
    }

    private void setP(Position x, int value) {
        int i = transFromOut(x);
        A.set(i, value);
    }

    public Vector<Position> calculate(Position src, Position dst) {
        Vector<Position> tpath=new Vector<Position>();
        tpath.clear();
        Position ts = new Position(src);
        Position td = new Position(dst);

        Vector<Position> cur = new Vector<Position>();
        cur.clear();
        cur.add(ts);

        setP(ts, 0);
        //System.out.println("here");
        while (true) {
            if (cur.isEmpty())
                return null;
            Position now = cur.get(0);

            Position next = null;
            //System.out.println("search"+now+" around");
            for (Position d : direction) {
                next = now.add(d);
                //System.out.println("search"+next);
                int v = getP(next);
                if (v == 0 && (!next.equal(ts))) {
                    v = getP(next);
                    boolean test = next.equal(ts);
                    setP(next, getP(now) + 1);
                    //System.out.println("       set"+next+(getP(now)+1));
                    cur.add(next);

                }
                if (next.equal(td))
                    break;
            }
            cur.remove(0);
            if (next.equal(td))
                break;
        }
//        setP(ts,0);
        //System.out.println("================");
        Position now = new Position(td);
        tpath.add(td);
        while (true) {
            Position next = null;
            //System.out.println("search"+now+" around");
            for (Position d : direction) {
                next = now.add(d);
                //System.out.println("search"+next);
                int v = getP(next);
                if (v == (getP(now) - 1)) {
                    tpath.add(next);
                    //System.out.println("       add"+next);
                    now = next;
                    break;
                }
            }
            if (next.equal(ts))
                break;
        }

        for (int i=tpath.size()-2;i>=0;i--) {
            path.add(tpath.get(i));
        }

        return path;
    }

    public static void main(String[] args) {
        int N = 3, M = 4;
        int temp[] = {
                -1, -1, -1, -1, -1, -1,
                -1, -1, 0, 0, 0, -1,
                -1, 0, 0, -1, 0, -1,
                -1, 0, 0, 0, 0, -1,
                -1, -1, -1, -1, -1, -1
        };
        Vector<Integer> test = new Vector<Integer>();
        for (int i : temp) {
            test.add(i);
        }
        FindPath fp = new FindPath(test, 3, 4);
        Vector<Position> res = fp.calculate(new Position(0, 0), new Position(3, 2));
        if (res != null) {
            for (Position p : res) {
                System.out.println(p);
            }
        } else
            System.out.println("fail");
    }

}

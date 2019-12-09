import java.util.ArrayList;
import java.util.Random;
import java.util.stream.StreamSupport;

enum DIRECTION {
    NORTH, SOUTH, EAST, WEST;
}

enum BOOK {
    SNAKE, BIRGWINGS, DEFEND, BIRDFLY, FISH, SQUARE, MOON, ARROW
}

class Creature {
    private char name;

    Creature(char n) {
        name = n;
    }

    public char getName() {
        return name;
    }

}

class Pos {
    int row;
    int col;

    Pos() {
        row = -1;
        col = -1;
    }

    Pos(int i, int j) {
        row = i;
        col = j;
    }

    Pos(Pos p) {
        row = p.row;
        col = p.col;
    }
}

class BattleField {
    private Creature[][] field;
    private int N;
    private Pos baseOfEast, baseOfWest;

    BattleField(int n) {
        field = new Creature[n][n];
        N = n;
        baseOfEast = new Pos(n / 2, 1);
        baseOfWest = new Pos(n / 2, n - 2);
    }

    public Pos getBase(DIRECTION dir) {
        if (dir == DIRECTION.EAST) {
            return new Pos(baseOfEast);
        } else if (dir == DIRECTION.WEST) {
            return new Pos(baseOfWest);
        }
        return null;
    }

    public void situation() {
        System.out.println("---- BattleField ----");
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                Creature creature = field[i][j];
                if (creature != null) {
                    System.out.print(creature.getName());
                } else
                    System.out.print("__");
            }
            System.out.print("\n");
        }
        System.out.println("---- ----------- ----");
    }

    public Pos whereIs(Creature anyone) {
        Pos ret = new Pos();
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                Creature creature = field[i][j];
                if (anyone == creature) {
                    ret.row = i;
                    ret.col = j;
                    return ret;
                }
            }
        }
        return ret;
    }

    public Creature whoIs(Pos p) {
        return field[p.row][p.col];
    }

    public void join(Creature creature, int row, int col) {
        if (whereIs(creature).row == -1 && whoIs(new Pos(row, col)) == null) {
            field[row][col] = creature;
        } else
            System.out.println("Place " + creature.getName() + " failed!");
    }

    public void joinCommander(Creature creature, DIRECTION direction) {
        Random random = new Random();
        if (direction == DIRECTION.EAST) {
            join(creature, baseOfEast.row - 2 + random.nextInt(5), baseOfEast.col - 1);
        } else if (direction == DIRECTION.WEST) {
            join(creature, baseOfWest.row - 2 + random.nextInt(5), baseOfWest.col + 1);
        }
    }

    public void retreat(Creature creature) {
        Pos p = whereIs(creature);
        if (p.row != -1) {
            field[p.row][p.col] = null;
        } else
            System.out.println("Remove " + creature.getName() + " failed!");
    }
}

class Strategy {
    private Pos[] position; // default is point down to enemy
    private Pos base;
    private DIRECTION direction = DIRECTION.SOUTH;
    private int len;
    private BOOK bookname;

    Strategy(BOOK name, Pos... pos) {
        bookname = name;
        position = pos;
        len = position.length;
    }

    private Pos getAdjustedPos(Pos src) {
        Pos ret = new Pos();
        if (direction == DIRECTION.SOUTH) return src;
        else if (direction == DIRECTION.WEST) {
            ret.row = src.col;
            ret.col = -src.row;
        } else if (direction == DIRECTION.NORTH) {
            ret.col = -src.col;
            ret.row = -src.row;
        } else if (direction == DIRECTION.EAST) {
            ret.col = src.row;
            ret.row = src.col;
        }
        return ret;
    }

    public void setBase(Pos pos) {
        base = pos;
    }

    public void setDirection(DIRECTION dir) {
        direction = dir;
    }

    public int getLen() {
        return len;
    }

    public Pos getPos(int index) {
        if (0 <= index && index < len) {
            Pos ret = new Pos(), target = getAdjustedPos(position[index]);
            ret.col = target.col + base.col;
            ret.row = target.row + base.row;
            return ret;
        } else return new Pos(-1, -1);
    }
}

class Team {
    ArrayList<Creature> member;
    ArrayList<Strategy> books;
    Creature commander;
    Pos base;
    DIRECTION direction;

    Team() {
        member = new ArrayList<Creature>();
        books = new ArrayList<Strategy>();
    }

    public void shuffleMemberIndex() {
        //this process is changing their index on paper, so there is no position changing.
        Random random = new Random();
        ArrayList<Creature> newList = new ArrayList<Creature>();
        while (member.size() != 0) {
            Creature someone = member.get(random.nextInt(member.size()));
            newList.add(someone);
            member.remove(someone);
        }
        member = newList;
    }
}

class Huluwa extends Creature {
    private int rank;

    Huluwa(char n, int rank) {
        super(n);
        this.rank = rank;
    }

    public boolean isBiggerThan(Huluwa guy) {
        return rank < guy.rank;
    }
}


public class Homework3 {
    public static Strategy findBook(BOOK bookName) {
        if (bookName.equals(BOOK.SNAKE)) {
            return new Strategy(
                    BOOK.SNAKE,
                    new Pos(0, -3),
                    new Pos(0, -2),
                    new Pos(0, -1),
                    new Pos(0, 0),
                    new Pos(0, 1),
                    new Pos(0, 2),
                    new Pos(0, 3),
                    new Pos(0, 4),
                    new Pos(0, -4)
            );
        } else if (bookName.equals(BOOK.BIRGWINGS)) {
            return new Strategy(
                    BOOK.BIRGWINGS,
                    new Pos(0, 0),
                    new Pos(1, 1),
                    new Pos(1, -1),
                    new Pos(2, 1),
                    new Pos(2, -1),
                    new Pos(3, 2),
                    new Pos(3, -2),
                    new Pos(4, 2),
                    new Pos(4, -2)
            );
        } else if (bookName.equals(BOOK.DEFEND)) {
            return new Strategy(
                    BOOK.DEFEND,
                    new Pos(0, 0),
                    new Pos(1, 0),
                    new Pos(1, 1),
                    new Pos(2, 0),
                    new Pos(2, -1),
                    new Pos(3, 0),
                    new Pos(3, 1),
                    new Pos(4, 0),
                    new Pos(4, -1)
            );
        } else if (bookName.equals(BOOK.BIRDFLY)) {
            return new Strategy(
                    BOOK.BIRDFLY,
                    new Pos(2, 0),
                    new Pos(2, 1),
                    new Pos(1, -1),
                    new Pos(3, 2),
                    new Pos(1, -2),
                    new Pos(3, 3),
                    new Pos(0, -3),
                    new Pos(4, 4),
                    new Pos(0, -4),
                    new Pos(4, 5)
            );
        } else if (bookName.equals(BOOK.FISH)) {
            return new Strategy(
                    BOOK.FISH,
                    new Pos(0, 0),
                    new Pos(1, 1),
                    new Pos(1, -1),
                    new Pos(1, 0),
                    new Pos(1, -2),
                    new Pos(1, 2),
                    new Pos(2, -1),
                    new Pos(2, 0),
                    new Pos(2, 1),
                    new Pos(3, 0)
            );
        } else if (bookName.equals(BOOK.SQUARE)) {
            return new Strategy(
                    BOOK.SQUARE,
                    new Pos(0, 0),
                    new Pos(1, -1),
                    new Pos(1, 1),
                    new Pos(2, -2),
                    new Pos(2, 2),
                    new Pos(3, -3),
                    new Pos(3, 3),
                    new Pos(4, -2),
                    new Pos(4, 2),
                    new Pos(5, -1),
                    new Pos(5, 1),
                    new Pos(6, 0)
            );
        } else if (bookName.equals(BOOK.MOON)) {
            return new Strategy(
                    BOOK.MOON,
                    new Pos(0, 0),
                    new Pos(0, 1),
                    new Pos(1, 0),
                    new Pos(1, 1),
                    new Pos(2, 1),
                    new Pos(2, 2),
                    new Pos(3, 2),
                    new Pos(3, 3),
                    new Pos(4, 1),
                    new Pos(4, 2),
                    new Pos(5, 1),
                    new Pos(5, 0)
            );
        } else if (bookName.equals(BOOK.ARROW)) {
            return new Strategy(
                    BOOK.ARROW,
                    new Pos(0, 0),
                    new Pos(1, 0),
                    new Pos(2, 0),
                    new Pos(3, 0),
                    new Pos(4, 0),
                    new Pos(5, 0),
                    new Pos(5, 1),
                    new Pos(5, -1),
                    new Pos(4, 2),
                    new Pos(4, -2)
            );
        }
        return null;
    }

    static public void sortedHuluwas(ArrayList<Creature> huluwas) {
        int length = huluwas.size();
        for (int i = 0; i < length - 1; i += 1) {
            for (int j = 0; j < length - 1 - i; j += 1) {
                Huluwa movingGuy = (Huluwa) huluwas.get(j);
                Huluwa standbyGuy = (Huluwa) huluwas.get(j + 1);
                if (movingGuy.isBiggerThan(standbyGuy)) {
                    huluwas.remove(movingGuy);
                    huluwas.add(j + 1, movingGuy);
                }
            }
        }
    }

    static public Strategy pickBookFrom(ArrayList<Strategy> books){
        Random bookpicker = new Random();
        return books.get(bookpicker.nextInt(books.size()));
    }

    static public void main(String argv[]) {
        // battle field is always there.
        System.out.println("War, war never changes!");
        BattleField battleField = new BattleField(11);

        //the huluwa gang is now founded and get a book, named snake.
        System.out.println("Huluwas! Assemble!");
        Team goodguys = new Team();
        goodguys.member.add(new Huluwa('大', 1));
        goodguys.member.add(new Huluwa('二', 2));
        goodguys.member.add(new Huluwa('三', 3));
        goodguys.member.add(new Huluwa('四', 4));
        goodguys.member.add(new Huluwa('五', 5));
        goodguys.member.add(new Huluwa('六', 6));
        goodguys.member.add(new Huluwa('七', 7));
        goodguys.commander = new Creature('爷');
        goodguys.books.add(findBook(BOOK.SNAKE));

        //as they are from east, the huluwa gang established their base on the east side.
        System.out.println("Guys! We find a new base!");
        goodguys.direction = DIRECTION.EAST;
        goodguys.base = battleField.getBase(goodguys.direction);

        //with the book, huluwas join the battle.
        //firstly, they adjust the strategy book for present situation.
        System.out.println("This strategy book is handy!");
        Strategy huluBook = goodguys.books.get(0);
        huluBook.setBase(goodguys.base);
        huluBook.setDirection(goodguys.direction);

        //then, according to the book, they join the battle respectively.
        System.out.println("Let's join the battle!");
        goodguys.shuffleMemberIndex();
        for (int i = 0; i < goodguys.member.size(); i += 1) {
            Pos ipos = huluBook.getPos(i);
            battleField.join(goodguys.member.get(i), ipos.row, ipos.col);
        }

        //let's see this battle at present
        battleField.situation();

        //huluwas love rainbow, so they change their position
        System.out.println("Does anyone think we need a better positioning?");
        for (int i = 0; i < goodguys.member.size(); i += 1) {
            battleField.retreat(goodguys.member.get(i));
        }
        sortedHuluwas(goodguys.member);
        for (int i = 0; i < goodguys.member.size(); i += 1) {
            Pos ipos = huluBook.getPos(i);
            battleField.join(goodguys.member.get(i), ipos.row, ipos.col);
        }

        battleField.situation();

        //however, at the same time, some bad guys are also trying to harm the innocent villagers.
        System.out.println("We will defeat those plant babys! Ha-ha!");
        Team badguys = new Team();
        badguys.member.add(new Creature('蝎'));
        for (int i = 0; i < 15; i += 1) {
            badguys.member.add(new Creature('兵'));
        }
        badguys.commander = new Creature('蛇');
        badguys.direction = DIRECTION.WEST;
        badguys.base = battleField.getBase(badguys.direction);

        //what's even worse, they find 7 strategy books except the SNAKE one!!!
        System.out.println("Snake? Too weak!");
        badguys.books.add(findBook(BOOK.BIRGWINGS));
        badguys.books.add(findBook(BOOK.BIRDFLY));
        badguys.books.add(findBook(BOOK.DEFEND));
        badguys.books.add(findBook(BOOK.ARROW));
        badguys.books.add(findBook(BOOK.FISH));
        badguys.books.add(findBook(BOOK.SQUARE));
        badguys.books.add(findBook(BOOK.MOON));

        //they choose a book whatever
        System.out.println("I think this book is good!");
        Strategy adbook = pickBookFrom(badguys.books);
        adbook.setDirection(badguys.direction);
        adbook.setBase(badguys.base);

        //and join the battle as well
        System.out.println("Let's make this!");
        for (int i = 0; i < adbook.getLen() && i < badguys.member.size(); i += 1) {
            Pos ipos = adbook.getPos(i);
            battleField.join(badguys.member.get(i), ipos.row, ipos.col);
        }

        //so what's now?
        battleField.situation();

        //commanders are also joined!
        System.out.println("Hey! You are the best! Beat them out of sh**!");
        battleField.joinCommander(goodguys.commander, goodguys.direction);
        battleField.joinCommander(badguys.commander, badguys.direction);

        //the are ready to fight!
        battleField.situation();

        //oh no! Miss Snake thinks the strategy is awful, so all badguys retreats
        System.out.println("Miss Snake: wait, I don't like this strategy, let's change to another.");
        battleField.retreat(badguys.commander);
        for (int i = 0; i < adbook.getLen() && i < badguys.member.size(); i += 1) {
            battleField.retreat(badguys.member.get(i));
        }

        //seeing that bad guys retreats, huluwas want to have a rest too
        battleField.retreat(goodguys.commander);
        for (int i = 0; i < goodguys.member.size(); i += 1) {
            battleField.retreat(goodguys.member.get(i));
        }

        //badguys choose another book
        Strategy newbook;
        do {
            newbook = pickBookFrom(badguys.books);
        }while(newbook == adbook);
        adbook = newbook;
        adbook.setBase(badguys.base);
        adbook.setDirection(badguys.direction);

        //then ready for a real fight
        System.out.println("Let's make this! Seriously!");
        for (int i = 0; i < goodguys.member.size(); i += 1) {
            Pos ipos = huluBook.getPos(i);
            battleField.join(goodguys.member.get(i), ipos.row, ipos.col);
        }

        for (int i = 0; i < adbook.getLen() && i < badguys.member.size(); i += 1) {
            Pos ipos = adbook.getPos(i);
            battleField.join(badguys.member.get(i), ipos.row, ipos.col);
        }

        //commanders are also joined!
        System.out.println("Hey! You are the best! Beat them out of sh**... why do I say this twice?");
        battleField.joinCommander(goodguys.commander, goodguys.direction);
        battleField.joinCommander(badguys.commander, badguys.direction);

        //who shall win?!
        battleField.situation();

        System.out.println("To Be Continue...");
    }
}
import java.util.Comparator;

public class CalabashBrother extends Creature {
    /* Class used to represent Calabash Brothers */
    private static final String[] names = {"RedBro", "OrangeBro", "YellowBro", "GreenBro", "CyanBro", "BlueBro",
            "PurpleBro"};
    private static final String[] colorArray = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple"};
    private int rank; /* Ranging from 0 to 6 */
    private String color;

    public CalabashBrother(int rank) {
        super(names[rank]);
        this.rank = rank;
        color = colorArray[rank];
    }

    public int getRank() {
        return rank;
    }

    public String getColor() {
        return color;
    }
}

class CalabashColorComparator implements Comparator<CalabashBrother> {
    @Override
    public int compare(CalabashBrother brotherOne, CalabashBrother brotherTwo) {
        return brotherOne.getRank() - brotherTwo.getRank();
    }
}

class CalabashRankComparator implements Comparator<CalabashBrother> {
    @Override
    public int compare(CalabashBrother brotherOne, CalabashBrother brotherTwo) {
        return brotherOne.getRank() - brotherTwo.getRank();
    }
}
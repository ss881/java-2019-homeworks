package chessboard;

public class Formation {
    Position datumPoint;
    boolean positive;

    public Formation() {
        datumPoint = new Position();
        positive = true;
    }

    public void setDatumPoint(int x, int y) {
        datumPoint.setX(x);
        datumPoint.setY(y);
    }

    public Position getDatumPoint() {
        return datumPoint;
    }

    final static int maxSizeOfCraneWing = 7;
    final static int displacementCraneWingY[] = {0, -1, 1, 2, -2, -3, 3};
    final static int displacementCraneWingX[] = {0, 1, 1, 2, 2, 3, 3};

    final static int maxSizeOfArrow = 12;
    final static int displacementArrowY[] = {0, -1, 0, 1, 2, 0, -2, -3, 0, 3, 0, 0};
    final static int displacementArrowX[] = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5};

    public static int getMaxSizeOfCraneWing() {
        return maxSizeOfCraneWing;
    }

    public static int getMaxSizeOfArrow() {
        return maxSizeOfArrow;
    }

    public Position getCraneWingI(int i) {
        Position res;
        if (positive)
            res = new Position(datumPoint.getX() + displacementCraneWingX[i], datumPoint.getY() + displacementCraneWingY[i]);
        else
            res = new Position(datumPoint.getX() - displacementCraneWingX[i], datumPoint.getY() + displacementCraneWingY[i]);
        return res;
    }

    public Position getArrowI(int i) {
        Position res ;
        if(positive)
            res= new Position(datumPoint.getX() + displacementArrowX[i], datumPoint.getY() + displacementArrowY[i]);
        else
            res= new Position(datumPoint.getX() - displacementArrowX[i], datumPoint.getY() + displacementArrowY[i]);
        return res;
    }

    public void faceNegative(){positive=true;}
    public void facePositive(){positive=false;}

}

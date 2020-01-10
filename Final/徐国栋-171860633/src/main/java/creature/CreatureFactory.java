package creature;

import javafx.scene.paint.Color;

interface CreatureFactory<T> {
    T create(int rank, Color color);
}

class CalabashBrotherFactory implements CreatureFactory<CalabashBrother> {
    public CalabashBrother create(int rank, Color color) {
        return new CalabashBrother(rank, color);
    }
}

class EvilLoloFactory implements CreatureFactory<EvilLolo> {
    public EvilLolo create(int rank, Color color) {
        return new EvilLolo(rank);
    }
}
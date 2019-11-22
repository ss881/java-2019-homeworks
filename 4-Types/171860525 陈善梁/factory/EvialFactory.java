package factory;

import creature.Evial;

public class EvialFactory extends Factory<Evial> {
    @Override
    public Evial generate() {
        return new Evial();
    }
}

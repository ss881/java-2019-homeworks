package factory;

import creature.GrandPa;

public class GrandPaFactory extends Factory<GrandPa> {
    @Override
    public GrandPa generate() {
        return new GrandPa();
    }
}

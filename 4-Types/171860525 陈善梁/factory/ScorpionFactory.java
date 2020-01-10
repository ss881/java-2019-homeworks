package factory;

import creature.Scorpion;

public class ScorpionFactory extends Factory<Scorpion> {
    @Override
    public Scorpion generate() {
        return new Scorpion();
    }
}

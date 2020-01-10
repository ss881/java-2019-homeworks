package factory;

import creature.Snake;

public class SnakeFactory extends Factory<Snake> {
    @Override
    public Snake generate() {
        return new Snake();
    }
}

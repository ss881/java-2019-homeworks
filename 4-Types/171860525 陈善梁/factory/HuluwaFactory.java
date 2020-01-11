package factory;

import creature.Huluwa;

public class HuluwaFactory extends Factory<Huluwa> {

    @Override
    public Huluwa generate() {
        return new Huluwa();
    }

    public Huluwa generate(int rank,String name,String color){
        return new Huluwa(rank,name,color);
    }
}

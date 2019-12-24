package figure;

import item.Item;

public interface Figure extends Item{
    String typeName = "Figure";

    String getName();

    void setName(String name);
}

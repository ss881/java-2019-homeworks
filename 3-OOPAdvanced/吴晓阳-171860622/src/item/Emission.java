package item;

/*
* 被item自动发射的物件
* 一般通过在item类中扩展接口，不要在单独的item类中扩充此接口（会降低其可扩展性）
 */
public interface Emission {
    Item getEmitter();

    void setEmitter(Item Emitter);
}

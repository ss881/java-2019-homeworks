package sample;
interface Actions
{
    abstract void follow_orders(Identity id,int x,int y);// follow orders by id which lets creature go to some space
    abstract void provoke();// provoke when get order from the enemy
    abstract void dead();
    abstract void get_current_damage();
}
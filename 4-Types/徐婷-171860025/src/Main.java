import creatures.*;
import ground.Ground;

public class Main {
    public static void main(String[]args) {
        HuluwaTeam huluwaTeam = new HuluwaTeam();
        SnakeTeam snakeTeam = new SnakeTeam();
        Ground ground = new Ground(huluwaTeam, snakeTeam);
        huluwaTeam.sort(ground);
        while(true){
            snakeTeam.arrange();
            ground.update();
            ground.printGround();

        }
    }
}

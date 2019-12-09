public class BadTeam {
    private Scorpion scorpion;
    private Evial[]evials;
    private Snake snake;

    public BadTeam(){
        scorpion = new Scorpion();
        evials=scorpion.initialize();
        snake=new Snake();
        snake.setCurrentPosition(4,7);
    }

    public void changeToGoose(){
        System.out.println("\n transform to 雁行:\n--------------------------------------------\n");
        scorpion.changeToGoose(evials);
//        snake.setPreviousPosition(snake.currentPosition.x,snake.currentPosition.y);
        snake.moveTo(4,7);
    }

    public void changeToCrane(){
        System.out.println("\n transform to 鹤翼:\n--------------------------------------------\n");
        scorpion.changeToCrane(evials);
//        snake.setPreviousPosition(snake.currentPosition.x,snake.currentPosition.y);
        snake.moveTo(5,7);
    }

    public void changeToCar(){
        System.out.println("\n transform to 衡轭:\n--------------------------------------------\n");
        scorpion.changeToCar(evials);
//        snake.setPreviousPosition(snake.currentPosition.x,snake.currentPosition.y);
        snake.moveTo(6,5);
    }

    public void changeToFishScale(){
        System.out.println("\n transform to 鱼鳞:\n--------------------------------------------\n");
        scorpion.changeToFishScale(evials);
        snake.moveTo(2,7);
    }

    public void changeToSquare(){
        System.out.println("\n transform to 方:\n--------------------------------------------\n");
        scorpion.changeToSquare(evials);
        snake.moveTo(6,7);
    }

    public void changeToMoon(){
        System.out.println("\n transform to 偃月:\n--------------------------------------------\n");
        scorpion.changeToMoon(evials);
        snake.moveTo(6,4);
    }

    public void changeToArrow(){
        System.out.println("\n transform to 锋矢:\n--------------------------------------------\n");
        scorpion.changeToArrow(evials);
        snake.moveTo(2,7);
    }

    public void changeRandomly(){
        int nextForm=(int)(Math.random()*6)+1;//[1-6]
        switch (nextForm){
            case 1:changeToGoose();break;
            case 2:changeToCar();break;
            case 3:changeToFishScale();break;
            case 4:changeToSquare();break;
            case 5:changeToMoon();break;
            default:changeToArrow();break;
        }

    }

    public Evial[]getEvials(){
        return evials;
    }

    public Scorpion getScorpion(){
        return scorpion;
    }

    public Snake getSnake(){
        return snake;
    }


}

class CalabashBrother extends Creature
{
    private double hp;      //血量
    private String color;   //葫芦娃对应颜色

    public CalabashBrother(String name,String color)
    {
        super(name,true,true);
        hp=100;             //初始满血
        this.color=color;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
    public double getHp() {
        return hp;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public String getColor() {
        return color;
    }
}


public class CalabashBrothers
{
    private CalabashBrother[] cbs;

    //得到一个大小为n,值介于(min,max]之间的int型数组
    public static int[] randomArray(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min+1)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        for(int i=0;i<n;i++)
            result[i]--;
        return result;
    }

    public CalabashBrothers() {
        int[] ranPos = randomArray(0, 7, 7);
        cbs = new CalabashBrother[7];
        for (int i = 0; i < 7; i++) {
            switch (ranPos[i]) {
                case 0: {
                    cbs[i] = new CalabashBrother("1", "RED");
                    break;
                }
                case 1: {
                    cbs[i] = new CalabashBrother("2", "ORANGE");
                    break;
                }
                case 2: {
                    cbs[i] = new CalabashBrother("3", "YELLOW");
                    break;
                }
                case 3: {
                    cbs[i] = new CalabashBrother("4", "GREEN");
                    break;
                }
                case 4: {
                    cbs[i] = new CalabashBrother("5", "CYAN");
                    break;
                }
                case 5: {
                    cbs[i] = new CalabashBrother("6", "BLUE");
                    break;
                }
                case 6: {
                    cbs[i] = new CalabashBrother("7", "PURPLE");
                    break;
                }
            }
        }
    }

    public Creature[] getCalabashBrothers() {
        return cbs;
    }
}


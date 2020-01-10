public class Interface {
    public void printMenu() {
        System.out.println("切换阵法按键：");
        System.out.println("0：鹤翼； 1：雁行；2：冲轭；3：鱼鳞；4：方圆；5：偃月；6：锋矢");
        System.out.println("退出：q");
    }

    public void display(State s) {
        int size = s.getSize();
        String output = new String();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                char ch = s.getCurFormation(i, j);
                /*if(ch == 'U') {
                    System.out.println(i + " " + j);
                }*/
                output += " " + ch;
            }
            output += "\n";
        }
        System.out.print(output);
        printMenu();
    }
}
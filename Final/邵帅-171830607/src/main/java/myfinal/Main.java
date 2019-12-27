package myfinal;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // System.out.println("hello world");
        // Organism brother0 = new Organism(new MyMap(2,3),"大娃");
        // System.out.println(Color.红色.ordinal());
        Brothers brother1 = new Brothers(new MyMap(2, 3), Color.红色);
        Brothers brother2 = new Brothers(new MyMap(3, 3), Color.橙色);
        Brothers brother3 = new Brothers(new MyMap(4, 3), Color.黄色);
        Brothers brother4 = new Brothers(new MyMap(5, 3), Color.绿色);
        Brothers brother5 = new Brothers(new MyMap(6, 3), Color.青色);
        Brothers brother6 = new Brothers(new MyMap(7, 3), Color.蓝色);
        Brothers brother7 = new Brothers(new MyMap(8, 3), Color.紫色);
        Organism grandpa = new Organism(new MyMap(0, 0), "老爷爷");
        // grandpa.cheer();
        //brother1.start();
        List<Organism> badmanlist = new ArrayList<Organism>();
        Monsters scorpion = new Monsters(new MyMap(10, 1), "蝎子精");
        Monsters snake = new Monsters(new MyMap(10, 0), "蛇精");
        badmanlist.add(scorpion);
        badmanlist.add(snake);
        for (int i = 0; i < 5; i++) {
            badmanlist.add(new Monsters(new MyMap(10, i + 2), "小喽啰"));
        }

        List<Organism> godmanlist = new ArrayList<Organism>();
        godmanlist.add(brother1);
        godmanlist.add(brother2);
        godmanlist.add(brother3);
        godmanlist.add(brother4);
        godmanlist.add(brother5);
        godmanlist.add(brother6);
        godmanlist.add(brother7);
        Embattle godmanEmbattle = new Embattle(Deployment.长蛇, godmanlist, 0, 20, 20);

        Embattle badmanEmbattle = new Embattle(Deployment.雁行, badmanlist, 1, 20, 20);
        // godmanEmbattle.printEmbattle();
        godmanEmbattle.exec();
        badmanEmbattle.exec();

        List<Organism> allmanlist = new ArrayList<Organism>();
        allmanlist.addAll(godmanlist);
        allmanlist.addAll(badmanlist);
        Embattle allmanEmbattle = new Embattle(Deployment.雁行, allmanlist, 0, 20, 20); // 虽然这里是雁形阵，但是只要不exec()就不会改变位置

        allmanEmbattle.printEmbattle();
        snake.cheer();
        grandpa.cheer();

        int allmansize = allmanlist.size();
        int godmansize = godmanlist.size();
        int badmansize = badmanlist.size();
        while (allmansize > 8) {
            for (int i = 0; i < allmanlist.size(); i++) {
                // 如果没死
                int flag = 0;
                if (allmanlist.get(i).isdead == 0&&allmanlist.get(i).flag==0) {
                    // 判断是否遇到敌人
                    if (allmanlist.get(i).godorbad(allmanlist.get(i)) == 0)// 好人
                    {
                        for (int j = 0; j < badmanlist.size(); j++) {
                            if ((allmanlist.get(i).Getpos().x + 1 == badmanlist.get(j).Getpos().x)
                                    && (allmanlist.get(i).Getpos().y == badmanlist.get(j).Getpos().y)) {
                                // 相遇 抛硬币决定生死
                                
                                allmanlist.get(i).battle();
                                badmanlist.get(j).battle();
                                flag = 1;
                                Random random = new Random();
                                int faith = random.nextInt(2);
                                if (faith == 0) {
                                    allmanlist.get(i).dead();
                                    allmansize--;
                                    godmansize--;
                                }
                                else {
                                    badmanlist.get(j).dead();
                                    allmansize--;
                                    badmansize--;
                                }
                                break;

                            }
                        }
                    }
                    if(flag==1)
                        continue;
                    if (allmanlist.get(i).isdead == 0&&allmanlist.get(i).flag == 0)
                        allmanlist.get(i).goforward();
 
                    // 判断是否遇到敌人
                    if (allmanlist.get(i).godorbad(allmanlist.get(i)) == 0)// 好人
                    {
                        for (int j = 0; j < badmanlist.size(); j++) {
                            if ((allmanlist.get(i).Getpos().x + 1 == badmanlist.get(j).Getpos().x)
                                    && (allmanlist.get(i).Getpos().y == badmanlist.get(j).Getpos().y)) {
                                // 相遇 抛硬币决定生死
                                allmanlist.get(i).battle();
                                badmanlist.get(j).battle();
                                Random random = new Random();
                                int faith = random.nextInt(2);
                                if (faith == 0) {
                                    allmanlist.get(i).dead();
                                    allmansize--;
                                    godmansize--;
                                }
                                else {
                                    badmanlist.get(j).dead();
                                    allmansize--;
                                    badmansize--;
                                }
                                break;
                            }
                        }
                    }
                    // if(allmanlist.get(i).Getpos().x)
                }
            }
            allmanEmbattle.printEmbattle();
        }

        while(badmansize!=0&&godmansize!=0)
        {
            for(int i=0;i<godmanlist.size();i++)
            {
                if(godmanlist.get(i).isdead==0)//还活着
                {
                    for(int j=0;j<badmanlist.size();j++)
                    {
                    if(badmanlist.get(j).isdead==0) //还活着
                    {
                        godmanlist.get(i).battle();
                        badmanlist.get(j).battle();
                         Random random = new Random();
                                int faith = random.nextInt(2);
                                if (faith == 0) {
                                    godmanlist.get(i).dead();
                                    allmansize--;
                                    godmansize--;
                                }
                                else {
                                    badmanlist.get(j).dead();
                                    allmansize--;
                                    badmansize--;
                                }
                                break;
                    }
                    }
                }
            }
            allmanEmbattle.printEmbattle();
        }
        // badmanEmbattle.changeDeployment(Deployment.鹤翼); //鹤翼阵
        // badmanEmbattle.exec();
        // allmanEmbattle.printEmbattle();
        // snake.cheer();
        // grandpa.cheer();

        // System.out.println(grandpa.godorbad(grandpa));

        // System.out.println(grandpa.godorbad(snake));
        // //godmanEmbattle.printEmbattle();
        // System.out.println(brother1.Getpos().x);
    }
}

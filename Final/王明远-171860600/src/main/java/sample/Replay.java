package  sample;

import javafx.application.Platform;

public class Replay extends Thread{
    public int [][]content;
    public  void get_set(int size,int height)
    {
        content=new int[size][height];
    }
    public SpaceForBattle space;
    int times=1;
    @Override
    public void run() {
        times=1;
        Main.simpleIntegerProperty.set(0);
        while (true) {
            if (MyFiles.read(content, Main.size_of_current_space, Main.size_of_current_space)) {
//                int[][] space = content;
                int size_of_space = Main.size_of_current_space;

                //the following code may meet errors in javafx8
                //though ok in javafx11, we drop following codes
//                Main.graphicsContext.drawImage(new Image("file:src/main/java/sample/resource/background.png"),0,0,Main.max_size_of_stage,Main.max_size_of_stage);
//                ArrayList<Image> images = SpaceForBattle.images;
//                for (int i = 0; i < size_of_space; ++i) {
//                    for (int j = 0; j < size_of_space; ++j) {
//
//                        // System.out.println(1+ " "+j);
//                        {
//                            Image image = null;
//                            int image_size = Main.max_size_of_stage / Main.size_of_current_space;
//                            if (space[i][j] >= 0) {
//                                if (space[i][j] <= 1)
//                                    image = images.get(space[i][j]);
//                                else if (space[i][j] <= 5)
//                                    image = images.get(space[i][j] - 1);
//                                else
//                                    image = images.get(space[i][j] - 6);
//                            } else {
//                                if (space[i][j] == -1)
//                                    image = images.get(12);
//                                else if (space[i][j] >= -5) {
//                                    image = images.get(-1 * space[i][j] + 10);
//                                } else
//                                    image = images.get(-1 * space[i][j] + 5);
//                            }
//                            if(space[i][j]!=0)
//                           Main.graphicsContext.drawImage(image, i * image_size, j * image_size, image_size, image_size);
//                        }
//
//                    }
//
//                }


                for (int i = 0; i < size_of_space; ++i)
                   for (int j = 0; j < size_of_space; ++j) {
                       space.set_space(content[i][j],i,j);
                   }
                    times++;

                Platform.runLater(() -> {
                    Main.simpleIntegerProperty.set(times);
                });
                try {
                    sleep(SpaceForBattle.time_gap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else
            {
                Main.run_flag.set(0);
                return;
            }
        }
    }
}

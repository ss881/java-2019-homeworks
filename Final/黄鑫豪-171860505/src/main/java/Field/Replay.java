package Field;

import javafx.application.Platform;

import java.io.File;
import java.util.Scanner;

public class Replay implements Runnable{
    private Scanner scanner;
    private int[] dtmp = new int[4];
    private TwoDSpace tds;

    Replay(File file, TwoDSpace tds) {
        this.tds = tds;
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (!Thread.interrupted()) {
            boolean keepOn = scanner.hasNextLine();
            if (keepOn) {
                String str = scanner.nextLine();
                String[] arr = str.split(" ");
                for (int i = 0; i < arr.length; ++i) {
                    dtmp[i] = Integer.parseInt(arr[i]);
                }
                tds.doView(dtmp);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tds.drawOneCreature(dtmp[1]);
                    }
                });
            }
            try {
                if (keepOn) {
                    Thread.sleep(50);
                } else {
                    tds.shutRedo();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

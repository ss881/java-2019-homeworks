package control;
import javax.swing.*;

public class Console {
    public static void
    run(final JFrame frame, final int width, final int height){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                frame.setTitle("葫芦娃大战妖精");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setVisible(true);
            }
        });
            
    }
}
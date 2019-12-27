package huluwa;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private BoardPanel boardPanel;
    private FuncPanel funcPanel;

    public MainFrame() {
        // initial members
        int row = 12, col = 12;
        setTitle("葫芦世界");
        setBounds(100, 100, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boardPanel = new BoardPanel(row, col);
        funcPanel = new FuncPanel(boardPanel);

        // set layout, default layout: boarder layout
        setLayout(new BorderLayout());
        add(boardPanel);
        add(BorderLayout.EAST, funcPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

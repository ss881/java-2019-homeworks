package hw3;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private BoardPanel boardPanel;
    private FuncPanel funcPanel;

    public MainFrame() {
        int row = 12, col = 12;
        setTitle("葫芦世界");
        setBounds(100, 100, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boardPanel = new BoardPanel(row, col);
        funcPanel = new FuncPanel(this);
        add(boardPanel);
        add(BorderLayout.EAST, funcPanel);
        setVisible(true);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

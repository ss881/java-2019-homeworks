package hw3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncPanel extends JPanel {
    private BoardPanel boardPanel;
    private JTextArea info;
    private MainFrame mainFrame;

    public FuncPanel(MainFrame mainFrame) {
        super();
        setLayout(new BorderLayout());
        boardPanel = mainFrame.getBoardPanel();
        this.mainFrame = mainFrame;
        info = new JTextArea();
        info.setEnabled(false); // the text area is not allowed to written by users.
        info.setDisabledTextColor(Color.BLACK);
//      info.setRows(40);
//      info.setColumns(20);
//      info.setLineWrap(true); // auto wrap if necessary
        JPanel btnPanel = new JPanel(new GridLayout(1, 3));
        JScrollPane infoPanel = new JScrollPane(info);
        JButton buzhenBtn = new JButton("小喽啰布阵");
        buzhenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.buZhen(info);
            }
        });
        JButton sortHuluwaBtn = new JButton("葫芦娃排序");
        sortHuluwaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("葫芦娃排序".equals(sortHuluwaBtn.getText())) {
                    boardPanel.sortHuluwa();
                    sortHuluwaBtn.setText("打乱葫芦娃");
                } else {
                    boardPanel.shuffleHuluwa();
                    sortHuluwaBtn.setText("葫芦娃排序");
                }
            }
        });
        JButton aboutBtn = new JButton("了解葫芦世界");
        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dlg = new JDialog(mainFrame, "了解葫芦世界", true);
                dlg.setLayout(new BorderLayout());
                dlg.setBounds(500, 500, 500, 185);
//                dlg.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
                JLabel figLabel = new JLabel();
                figLabel.setIcon(new ImageIcon("pics/logo.jpg"));
                dlg.add(figLabel, BorderLayout.WEST);
                JLabel label = new JLabel("<html><body><p align=\"center\">" +
                        "Nanjing University Java Course 3rd Homework<br/>" +
                        "v1.0.0<br/>" +
                        "developed by Chuyougang" +
                        "</p></body></html>"
                ,JLabel.CENTER);
                dlg.add(label);
                dlg.setVisible(true);
            }
        });
        btnPanel.add(buzhenBtn);
        btnPanel.add(sortHuluwaBtn);
        btnPanel.add(aboutBtn);
        add(infoPanel);
        add(btnPanel, BorderLayout.NORTH);
    }
}

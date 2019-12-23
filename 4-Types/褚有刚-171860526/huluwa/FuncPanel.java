package huluwa;

import huluwa.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncPanel extends JPanel {
    private BoardPanel boardPanel;
    private JTextArea info;
    private JButton buzhenBtn;
    private JButton sortHuluwaBtn;
    private JButton aboutBtn;

    public FuncPanel(BoardPanel boardPanel) {
        // initial members
        this.boardPanel = boardPanel;
        info = new JTextArea();
        info.setEnabled(false); // the text area is not allowed to written by users.
        info.setDisabledTextColor(Color.BLACK);
        buzhenBtn = new JButton("小喽啰布阵");
        sortHuluwaBtn = new JButton("葫芦娃排序");
        aboutBtn = new JButton("了解葫芦世界");

        // set layout
        setLayout(new BorderLayout());

        // add event handlers
        buzhenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.buZhen(info);
            }
        });
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

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dlg = new JDialog();
                dlg.setModal(true);
                dlg.setLayout(new BorderLayout());
                dlg.setBounds(500, 500, 500, 185);
//                dlg.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
                JLabel figLabel = new JLabel();
                figLabel.setIcon(new ImageIcon(ImageLoader.getImage("logo")));
                dlg.add(figLabel, BorderLayout.WEST);
                JLabel label = new JLabel("<html><body><p align=\"center\">" +
                        "Nanjing University Java Course 4th Homework<br/>" +
                        "v1.0.1<br/>" +
                        "developed by Chuyougang" +
                        "</p></body></html>"
                ,JLabel.CENTER);
                label.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
                dlg.add(label);
                dlg.setVisible(true);
            }
        });

        JPanel btnPanel = new JPanel(new GridLayout(1, 3));
        JScrollPane infoPanel = new JScrollPane(info);
        btnPanel.add(buzhenBtn);
        btnPanel.add(sortHuluwaBtn);
        btnPanel.add(aboutBtn);
        add(infoPanel);
        add(btnPanel, BorderLayout.NORTH);
    }
}

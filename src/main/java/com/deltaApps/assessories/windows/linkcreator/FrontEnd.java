package com.deltaApps.assessories.windows.linkcreator;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrontEnd extends JFrame {

    private JLabel linkLabel,targetLabel;
    private JTextField linkFolderNameTxt;
    private JComboBox<String> linkTypeParam;

    public FrontEnd(){

        linkLabel = new JLabel("Click to select link location");

        JFileChooser fileChooser = new JFileChooser(this.getTreeLock().toString());
        fileChooser.setDialogType(JFileChooser.DIRECTORIES_ONLY);

        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileChooser.showOpenDialog(FrontEnd.this);
                String dir = String.valueOf(fileChooser.getCurrentDirectory());
                System.out.println(dir);
            }
        });
        add(linkLabel);

        String[] choices = { "Directory Junction", "Symbolic link","Hard link"};
        JComboBox<String> linkTypeParam = new JComboBox<String>(choices);
//        add(linkTypeParam);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(500,400);
        setVisible(true);
        setTitle("Windows link creator");

    }
}

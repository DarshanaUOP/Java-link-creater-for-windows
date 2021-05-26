package com.deltaApps.assessories.windows.linkcreator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class FrontEnd extends JFrame {

    private JLabel linkLabel,linkOutLabel,targetLabel,targetOutLabel;
    private JTextField linkFolderNameTxt;
    private JComboBox<String> linkTypeParam;
    private JButton createButton;

    private JPanel backPanel,linkPanel,targetPanel,processPanel;

    private String linkDirectory;
    private String targetDirectory;
    private String linkFolder;
    private char linkType;

    LinkCreator linkCreator;

    GridBagConstraints gbc = new GridBagConstraints();

    public FrontEnd(){
        // Create file chooser.
        JFileChooser fileChooser = new JFileChooser(this.getTreeLock().toString());
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // JFrame layout settings
        this.setLayout(new GridBagLayout());

        // UI objects

        linkLabel = new JLabel("Click to select link location");
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fileName =  fileChooser.showOpenDialog(FrontEnd.this);

                if (fileName == JFileChooser.APPROVE_OPTION){
                    try {
                        linkDirectory = fileChooser.getSelectedFile().getCanonicalPath();
                        System.out.println("OPEN : " + linkDirectory);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    linkOutLabel.setText(linkDirectory);
                }
            }
        });

        String[] choices = { "Directory Junction", "Symbolic link","Hard link"};
        JComboBox<String> linkTypeParam = new JComboBox<String>(choices);

        linkOutLabel = new JLabel("You are not selected a link directory");
        linkFolderNameTxt = new JTextField("new folder");

        backPanel = new JPanel(new GridBagLayout());

        linkPanel = new JPanel(new BorderLayout());
        Border linkPanelBorder = BorderFactory.createTitledBorder("Link");
        linkPanel.setBorder(linkPanelBorder);
        linkPanel.add(linkLabel,BorderLayout.WEST);
        linkPanel.add(linkTypeParam,BorderLayout.EAST);
        linkPanel.add(linkFolderNameTxt,BorderLayout.CENTER);
        linkPanel.add(linkOutLabel,BorderLayout.SOUTH);

        targetOutLabel = new JLabel("You are not selected a target directory");
        targetLabel = new JLabel("Select the target");
        targetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fileName =  fileChooser.showOpenDialog(FrontEnd.this);

                if (fileName == JFileChooser.APPROVE_OPTION){
                    try {
                        targetDirectory = fileChooser.getSelectedFile().getCanonicalPath();
                        System.out.println("OPEN : " + targetDirectory);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    targetOutLabel.setText(targetDirectory);
                }
            }
        });
        targetPanel = new JPanel(new BorderLayout());
        Border targetPanelBorder = BorderFactory.createTitledBorder("Target");
        targetPanel.setBorder(targetPanelBorder);
        targetPanel.add(targetLabel,BorderLayout.NORTH);
        targetPanel.add(targetOutLabel,BorderLayout.SOUTH);

        createButton = new JButton("Create");
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                linkFolder = linkFolderNameTxt.getText();

                int selectedIndex = linkTypeParam.getSelectedIndex();
                switch (selectedIndex){
                    case 1:
                        linkType = 'h';
                        break;
                    case 2:
                        linkType = 'd';
                        break;
                    default:
                        linkType = 'j';
                }

                linkCreator = new LinkCreator(linkDirectory , linkFolder, linkType , targetDirectory);
            }
        });

        processPanel = new JPanel(new BorderLayout());
        Border processPanelBorder = BorderFactory.createTitledBorder("process");
        processPanel.setBorder(processPanelBorder);
        processPanel.add(createButton);

        // Adding components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backPanel.add(linkPanel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backPanel.add(targetPanel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        backPanel.add(processPanel,gbc);

        add(backPanel);

        // JFrame settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(500,400);
        setVisible(true);
        setTitle("Windows link creator");

    }
}

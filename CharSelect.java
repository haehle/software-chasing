import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CharSelect {
    JFrame window;
    Container con;
    JPanel menu;
    JPanel titlePanel;
    JPanel editPanel;
    static String char1Name = "Character 1";
    static String char2Name = "Character 2";
    static String char3Name = "Character 3";

    public CharSelect(String name1, String name2, String name3){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 100);
        titlePanel.setBackground(Color.black);
        JLabel titleName = new JLabel("Software Chasing: \n A BoilerMaker Saga");
        titleName.setFont(new Font("Times New Roman", Font.PLAIN, 35));;
        titlePanel.add(titleName);
        con.add(titlePanel);

        JButton char1Button = new JButton();
        char1Button.setBounds(270, 250, 200, 60);
        char1Button.setBackground(Color.white);
        char1Button.setText(name1);
        
        con.add(char1Button);

        JButton char1EditButton = new JButton();
        char1EditButton.setBounds(480, 250, 60, 60);
        char1EditButton.setBackground(Color.white);
        char1EditButton.setText("Edit");
        char1EditButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel("char1");
            }
            
        });
        con.add(char1EditButton);

        JButton char2Button = new JButton();
        char2Button.setBounds(270, 330, 200, 60);
        char2Button.setBackground(Color.white);
        char2Button.setText(name2);
        con.add(char2Button);

        JButton char2EditButton = new JButton();
        char2EditButton.setBounds(480, 330, 60, 60);
        char2EditButton.setBackground(Color.white);
        char2EditButton.setText("Edit");
        char2EditButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel("char2");
            }
            
        });
        con.add(char2EditButton);

        JButton char3Button = new JButton();
        char3Button.setBounds(270, 410, 200, 60);
        char3Button.setBackground(Color.white);
        char3Button.setText(name3);
        con.add(char3Button);

        JButton char3EditButton = new JButton();
        char3EditButton.setBounds(480, 410, 60, 60);
        char3EditButton.setBackground(Color.white);
        char3EditButton.setText("Edit");
        char3EditButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel("char3");
            }
            
        });
        con.add(char3EditButton);

    }

    public void editPanel(String character){
        JFrame editFrame = new JFrame();
        Container editCon = new Container();
        editFrame.setSize(800,600);
        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.getContentPane().setBackground(Color.black);
        editFrame.setLayout(null);
        editFrame.setVisible(true);
        editCon = editFrame.getContentPane();

        JPanel editTitlePanel = new JPanel();
        editTitlePanel.setBounds(100, 50, 600, 100);
        editTitlePanel.setBackground(Color.black);
        JLabel editTitleName = new JLabel("Edit Character Name");
        editTitleName.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        editTitlePanel.add(editTitleName);
        editCon.add(editTitlePanel);

        JLabel editTitle = new JLabel("Character Name: ");
        editTitle.setSize(200, 25);
        editTitle.setFont(new Font("Acumin Pro", Font.PLAIN, 18));
        editTitle.setLocation(150, 150);
        editCon.add(editTitle);
        JTextField CharNameText = new JTextField();
        CharNameText.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        CharNameText.setSize(300, 30);
        CharNameText.setLocation(350, 150);
        editCon.add(CharNameText);

        JButton saveButton = new JButton();
        saveButton.setBounds(270, 200, 100, 50);
        saveButton.setBackground(Color.white);
        saveButton.setText("Save");
        saveButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                editFrame.dispose();

                if(character.equals("char1")){
                    char1Name = CharNameText.getText();
                    new CharSelect(char1Name, char2Name, char3Name);
                }else if(character.equals("char2")){
                    char2Name = CharNameText.getText();
                    new CharSelect(char1Name, char2Name, char3Name);
                } else if(character.equals("char3")){
                    char3Name = CharNameText.getText();
                    new CharSelect(char1Name, char2Name, char3Name);
                }

            }
            
        });
        editCon.add(saveButton);

        JButton cancelButton = new JButton();
        cancelButton.setBounds(390, 200, 100, 50);
        cancelButton.setBackground(Color.white);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editFrame.dispose();
            }
            
        });
        editCon.add(cancelButton);
    }

    public static void main(String args[]) {
        new CharSelect(char1Name, char2Name, char3Name);
    }
}
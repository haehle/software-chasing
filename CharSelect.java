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

    public static String username;//Get current users username

    public static Player[] players;
    public static Player currentPlayer;
    public static Profile userProfile;

    public static Player char1;
    public static Player char2;
    public static Player char3;
    
    public static String char1Name = null; 
    public static String char2Name = null; 
    public static String char3Name = null; 

    public CharSelect(Profile profile){
        username = profile.getUsername();
        players = dbConnection.getPlayers(username);
        if(players[0] != null) {
            char1 = players[0];
            char1Name = players[0].getName();
        }

        if(players[1] != null) {
            char2 = players[1];
            char2Name = players[1].getName();
        }

        if(players[2] != null) {
            char3 = players[2];
            char3Name = players[2].getName();
        }

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
        titleName.setForeground(Color.white);
        titleName.setFont(new Font("Times New Roman", Font.PLAIN, 35));;
        titlePanel.add(titleName);
        con.add(titlePanel);

        JButton createCharButton = new JButton();
        createCharButton.setBounds(200, 250, 400, 40);
        createCharButton.setBackground(Color.white);
        createCharButton.setText("Create new Character");
        createCharButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                createCharPanel(profile);
            }
            
        });
        if(char1Name == null | char2Name == null | char3Name == null){
            con.add(createCharButton);
        }

        JButton char1Button = new JButton();
        char1Button.setBounds(270, 300, 200, 60);
        char1Button.setBackground(Color.white);
        char1Button.setText(char1Name);
        char1Button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = char1;
                window.dispose();
                Game.StartGame(profile, currentPlayer);
            }
            
        });
        
        JButton char1EditButton = new JButton();
        char1EditButton.setBounds(480, 300, 60, 60);
        char1EditButton.setBackground(Color.white);
        char1EditButton.setText("Edit");
        char1EditButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel(profile, "char1");
            }
            
        });

        JButton char1DeleteButton = new JButton();
        char1DeleteButton.setBounds(550, 300, 80, 60);
        char1DeleteButton.setBackground(Color.white);
        char1DeleteButton.setText("Delete");
        char1DeleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Produce pop-up that asks user to confirm player deletion
                int result = JOptionPane.showConfirmDialog(window,"Are you sure you want to delete this player?", "Delete Player",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    //Delete player
                    //Util.deletePlayer(username, players[0].getName());
                    //window.dispose();
                    //new CharSelect(profile);
                }else {
                    //Do nothing
                }
            }

        });

        if(char1Name != null){
            con.add(char1Button);
            con.add(char1EditButton);
            con.add(char1DeleteButton);
        }

        JButton char2Button = new JButton();
        char2Button.setBounds(270, 380, 200, 60);
        char2Button.setBackground(Color.white);
        char2Button.setText(char2Name);
        char2Button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = char2;
                window.dispose();
                Game.StartGame(profile, currentPlayer);
            }
            
        });

        JButton char2EditButton = new JButton();
        char2EditButton.setBounds(480, 380, 60, 60);
        char2EditButton.setBackground(Color.white);
        char2EditButton.setText("Edit");
        char2EditButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel(profile, "char2");
            }
            
        });

        JButton char2DeleteButton = new JButton();
        char2DeleteButton.setBounds(550, 380, 80, 60);
        char2DeleteButton.setBackground(Color.white);
        char2DeleteButton.setText("Delete");
        char2DeleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Produce pop-up that asks user to confirm player deletion
                int result = JOptionPane.showConfirmDialog(window,"Are you sure you want to delete this player?", "Delete Player",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    //Delete player
                    //Util.deletePlayer(username, players[0].getName());
                    //window.dispose();
                    //new CharSelect(profile);
                }else {
                    //Do nothing
                }
            }

        });

        if(char2Name != null){
            con.add(char2Button);
            con.add(char2EditButton);
            con.add(char2DeleteButton);
        }

        JButton char3Button = new JButton();
        char3Button.setBounds(270, 460, 200, 60);
        char3Button.setBackground(Color.white);
        char3Button.setText(char3Name);
        char3Button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = char3;
                window.dispose();
            }
            
        });

        JButton char3EditButton = new JButton();
        char3EditButton.setBounds(480, 460, 60, 60);
        char3EditButton.setBackground(Color.white);
        char3EditButton.setText("Edit");
        char3EditButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel(profile, "char3");
            }

        });

        JButton char3DeleteButton = new JButton();
        char3DeleteButton.setBounds(550, 460, 80, 60);
        char3DeleteButton.setBackground(Color.white);
        char3DeleteButton.setText("Delete");
        char3DeleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Produce pop-up that asks user to confirm player deletion
                int result = JOptionPane.showConfirmDialog(window,"Are you sure you want to delete this player?", "Delete Player",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    //Delete player
                    //Util.deletePlayer(username, players[0].getName());
                    //window.dispose();
                    //new CharSelect(profile);
                }else {
                    //Do nothing
                }
            }

        });

        if(char3Name != null){
            con.add(char3Button);
            con.add(char3EditButton);
            con.add(char3DeleteButton);
        }

    }

    public void editPanel(Profile profile, String character){
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

                if(character.equals("char1")){//Save new character name(Hunter)
                    char1Name = CharNameText.getText();
                    Player newPlayer = new Player(char1.getUsername(), char1Name, char1.getType());
                    Util.deletePlayer(char1.getUsername(), char1.getName());
                    dbConnection.addPlayer(newPlayer);
                    new CharSelect(profile);
                }else if(character.equals("char2")){
                    char2Name = CharNameText.getText();
                    Player newPlayer = new Player(char2.getUsername(), char2Name, char2.getType());
                    Util.deletePlayer(char2.getUsername(), char2.getName());
                    dbConnection.addPlayer(newPlayer);
                    new CharSelect(profile);
                } else if(character.equals("char3")){
                    char3Name = CharNameText.getText();
                    Player newPlayer = new Player(char3.getUsername(), char3Name, char3.getType());
                    Util.deletePlayer(char3.getUsername(), char3.getName());
                    dbConnection.addPlayer(newPlayer);
                    new CharSelect(profile);
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
    public void createCharPanel(Profile profile){
        JFrame createFrame = new JFrame();
        Container createCon = new Container();
        createFrame.setSize(800,600);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFrame.getContentPane().setBackground(Color.black);
        createFrame.setLayout(null);
        createFrame.setVisible(true);
        createCon = createFrame.getContentPane();

        JPanel createTitlePanel = new JPanel();
        createTitlePanel.setBounds(100, 50, 600, 100);
        createTitlePanel.setBackground(Color.black);
        JLabel createTitleName = new JLabel("Create New Character");
        createTitleName.setForeground(Color.white);
        createTitleName.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        createTitlePanel.add(createTitleName);
        createCon.add(createTitlePanel);

        JPanel form = new JPanel();
        form.setSize(250,80);
        form.setLocation(250, 200);
        form.setBackground(Color.blue);

        JLabel charNameLabel = new JLabel("Character Name: ");
        charNameLabel.setSize(200, 25);
        charNameLabel.setFont(new Font("Acumin Pro", Font.PLAIN, 18));
        charNameLabel.setLocation(150, 150);
        charNameLabel.setForeground(Color.white);
        createCon.add(charNameLabel);
        JTextField charNameField = new JTextField();
        charNameField.setFont(new Font("Acumin Pro", Font.PLAIN, 18));
        charNameField.setSize(300, 30);
        charNameField.setLocation(350, 150);
        createCon.add(charNameField);

        JLabel typeLabel = new JLabel();
        typeLabel.setText("Please select the type of character!");
        typeLabel.setLocation(150, 200);
        typeLabel.setSize(200, 25);
        typeLabel.setFont(new Font("Acumin Pro", Font.PLAIN, 18));
        typeLabel.setForeground(Color.white);

        ButtonGroup typeGroup = new ButtonGroup();
        JRadioButton type1 = new JRadioButton();
        type1.setText("Type 1");
        type1.setLocation(150, 300);
        JRadioButton type2 = new JRadioButton();
        type2.setText("Type 2");
        type2.setLocation(150, 300);
        JRadioButton type3 = new JRadioButton();
        type3.setText("Type 3");
        type3.setLocation(150, 300);
        typeGroup.add(type1);
        typeGroup.add(type2);
        typeGroup.add(type3);
        
        form.add(typeLabel);
        form.add(type1);
        form.add(type2);
        form.add(type3);
        createCon.add(form);

        JButton createCharButton = new JButton();
        createCharButton.setBounds(270, 400, 100, 50);
        createCharButton.setBackground(Color.white);
        createCharButton.setText("Create");
        createCharButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(charNameField.getText().length() > 0){
                    window.dispose();  
                    Player newPlayer;              
                    if(type1.isSelected()){ //Save new player to database
                        newPlayer = new Player(username, charNameField.getText(), 1);
                        dbConnection.addPlayer(newPlayer);
                    }else if(type2.isSelected()){
                        newPlayer = new Player(username, charNameField.getText(), 2);
                        dbConnection.addPlayer(newPlayer);
                    } else{
                        newPlayer = new Player(username, charNameField.getText(), 3);
                        dbConnection.addPlayer(newPlayer);
                    }
                    if(char1Name == null){
                        char1Name = charNameField.getText();
                        char1 = newPlayer;
                    } else if(char2Name == null){
                        char2Name = charNameField.getText();
                        char2 = newPlayer;
                    } else if(char3Name == null){
                        char3Name = charNameField.getText();
                        char3 = newPlayer;
                    }
                    createFrame.dispose();
                    new CharSelect(profile);

                }
                
            }
            
        });
        createCon.add(createCharButton);

        JButton cancelButton = new JButton();
        cancelButton.setBounds(390, 400, 100, 50);
        cancelButton.setBackground(Color.white);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                createFrame.dispose();
            }
            
        });
        createCon.add(cancelButton);
    }
    public static void GeneratePage(Profile profile) {
        new CharSelect(profile);
    }
}
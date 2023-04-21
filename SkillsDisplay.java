import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class SkillsDisplay extends JFrame implements KeyListener {

    JLabel skillLabel = new JLabel("Current Skills");
    List<Skill> skills;
    List<String> descriptions = new ArrayList<>();
    String selectedValue = "";
    JList<String> list;

    public SkillsDisplay(Player player) {
        super(player.getName() + "'s Skills");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
                dispose();
            }
        });
        setResizable(false);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        //Set the size
        setPreferredSize(new Dimension(500, 300));
        setBackground(Color.decode("#cfb991"));

        //Change skillLabel
        skillLabel.setFont(new Font(skillLabel.getFont().toString(), Font.PLAIN, 25));

        //Add buttons
        /*
        JButton useButton = new JButton("Use item");
        useButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //useButton pressed
                System.out.println("Use item pressed.\n");

                /* Use item - values are hardcoded for now, but would ideally have uses fetched from
                   separate database table later on */
        /*
                if (selectedValue.equals("Coffee"))
                {
                    player.setHp(player.getHp() + 5);
                }
                else if (selectedValue.equals("Energy Drink"))
                {
                    player.setStamina(player.getStamina() + 5);
                }
                else if (selectedValue.equals("Gaming Laptop"))
                {
                    player.setSpeed(player.getSpeed() + 1);
                }

                //Remove item from inventory
                dbConnection.removeInventoryItem(player.getUsername(), player.getName(), selectedValue);
                List<Item> items = player.getInventory().getItems();
                items.remove(list.getSelectedIndex());
                player.setInventory(new Inventory(items));

                //Close inventory
                exitProcedure(player);
                dispose();
            }
        });
        useButton.setEnabled(false);
         */

        //Add description text
        JLabel description = new JLabel();
        description.setLocation(10, 250);
        description.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        String item1 = "";
        if (player.getSkills().size() > 0)
        {
            //Player has one or more skills
            System.out.println("Player has one or more skills");
            skills = player.getSkills();

            DefaultListModel<String> l1 = new DefaultListModel<>();
            for (Skill skill : skills)
            {
                l1.addElement(skill.getName());
                descriptions.add(skill.getDescription());
            }
            list = new JList<>(l1);
            list.setVisibleRowCount(-1);
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            ListSelectionListener listListener = new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting() == false) {

                        if (list.getSelectedIndex() == -1) {
                            //No item selected
                            //useButton.setEnabled(false);

                        } else {
                            //Item selected
                            System.out.println("SELECTED VALUE: " + list.getSelectedValue());
                            selectedValue = list.getSelectedValue();
                            description.setText(descriptions.get(list.getSelectedIndex()));
                            //useButton.setEnabled(true);
                        }
                    }
                }
            };

            //Add listener
            list.addListSelectionListener(listListener);

            //Add components
            add(skillLabel);
            add(scrollPane);
            add(description);
            //add(useButton);
            add(Box.createRigidArea(new Dimension(10, 10)));
        }
        else
        {
            //Player has zero skills whatsoever
            skillLabel.setText("You have no skills at all.");
            add(Box.createRigidArea(new Dimension(10, 10)));
            add(skillLabel);
            System.out.println("Player has zero skills whatsoever");
        }

        skillLabel.addKeyListener(this);
        skillLabel.setFocusable(true);
        pack();
        setVisible(true);
    }

    public static void exitProcedure()
    {
        World.skillsOpen = 0;
    }

    public void keyTyped(KeyEvent input) {
        char key = input.getKeyChar();
        if (key == 'k')
        {
            exitProcedure();
            dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Do nothing
    }
}

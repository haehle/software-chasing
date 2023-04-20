import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class InventoryDisplay extends JFrame implements KeyListener {

    JLabel itemLabel = new JLabel(" ");
    List<Item> items;
    List<String> descriptions = new ArrayList<>();
    String selectedValue = "";
    JList<String> list;

    public InventoryDisplay(Player player) {
        super(player.getName() + "'s Inventory");
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

        //Add buttons
        JButton useButton = new JButton("Use item");
        useButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //useButton pressed
                System.out.println("Use item pressed.\n");

                /* Use item - values are hardcoded for now, but would ideally have uses fetched from
                   separate database table later on */
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

        //Add description text
        JLabel description = new JLabel();
        description.setLocation(10, 250);
        description.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        String item1 = "";
        if (player.getInventory().getItems().size() > 0)
        {
            //Inventory is not empty
            System.out.println("Inventory is not empty");
            items = player.getInventory().getItems();

            DefaultListModel<String> l1 = new DefaultListModel<>();
            for (Item item : items)
            {
                l1.addElement(item.getName());
                descriptions.add(item.getDescription());
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
                            useButton.setEnabled(false);

                        } else {
                            //Item selected
                            System.out.println("SELECTED VALUE: " + list.getSelectedValue());
                            selectedValue = list.getSelectedValue();
                            description.setText(descriptions.get(list.getSelectedIndex()));
                            useButton.setEnabled(true);
                        }
                    }
                }
            };

            //Add listener
            list.addListSelectionListener(listListener);

            //Add components
            add(itemLabel);
            add(scrollPane);
            add(description);
            add(useButton);
            add(Box.createRigidArea(new Dimension(10, 10)));
        }
        else
        {
            //Inventory is empty
            itemLabel.setText("Your inventory is empty.");
            add(Box.createRigidArea(new Dimension(10, 10)));
            add(itemLabel);
            System.out.println("Inventory is empty");
        }

        itemLabel.addKeyListener(this);
        itemLabel.setFocusable(true);
        pack();
        setVisible(true);
    }

    public static void exitProcedure(Player player)
    {
        World.invOpen = 0;
        NPC.updateDisplay(player.getHp(), player.getMaxHP());
    }

    public static void exitProcedure()
    {
        World.invOpen = 0;
    }

    public void keyTyped(KeyEvent input) {
        char key = input.getKeyChar();
        if (key == 'i')
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
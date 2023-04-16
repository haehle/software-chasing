import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class InventoryDisplay extends JFrame implements KeyListener {

    JLabel itemLabel = new JLabel();
    List<Item> items;

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

        //Set the size
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.decode("#cfb991"));

        //Test
        /*
        Inventory inv = new Inventory();
        inv.addItem("sword");
        inv.addItem("shield");
        player.setInventory(inv);
         */

        //STILL NEED TO TEST ALL OF THIS W/ACTUAL DB; MUST HAVE SECURE INTERNET CONNECTION

        String item1 = "";
        if (player.getInventory().getItems().size() > 0)
        {
            //Inventory is not empty
            itemLabel.setText("Inventory items:");
            items = player.getInventory().getItems();

            for (Item item : items)
            {
                JLabel newItemLabel = new JLabel();
                newItemLabel.setText(item.getName());
                add(newItemLabel);
            }
        }
        else
        {
            //Inventory is empty
            itemLabel.setText("Your inventory is empty.");
        }


        itemLabel.setText(item1);

        /*
        if (item1 == "")
        {
            itemLabel.setText("Your inventory is empty.");
        }
        else
        {
            itemLabel.setText(item1);
        }
         */

        itemLabel.addKeyListener(this);
        itemLabel.setFocusable(true);
        add(itemLabel);
        pack();
        setVisible(true);
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
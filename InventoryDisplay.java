import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InventoryDisplay extends JFrame implements KeyListener {

    JLabel closeLabel = new JLabel();
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

        String item1 = "";
        if (player.getInventory().getItems().size() > 0)
        {
            item1 = player.getInventory().getItems().get(0);
        }

        closeLabel.setText(item1);

        if (item1 == "")
        {
            closeLabel.setText("Your inventory is empty.");
        }
        else
        {
            closeLabel.setText(item1);
        }

        closeLabel.addKeyListener(this);
        closeLabel.setFocusable(true);
        add(closeLabel);
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